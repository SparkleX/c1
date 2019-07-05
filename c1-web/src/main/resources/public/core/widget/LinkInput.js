sap.ui.define([
	"sap/m/Input",
	"next/core/view/CflDialog",
    "sap/ui/model/json/JSONModel",
    "next/core/widget/CoreUtil",
    "next/core/controller/ApiUtils",
    "next/core/widget/FormatUtil",
],
function(BaseClass, CflDialog, JSONModel, CoreUtil,ApiUtils,FormatUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LinkInput", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Data", defaultValue: null, bindable: "bindable" },
		//	dataDesc: { type: "string", group: "Misc", defaultValue: null }
		}
	}});

	theClass.prototype.init = function () {
		BaseClass.prototype.init.call(this);
		
		this.setPlaceholder("Enter ...");
		this.setShowSuggestion(true);
		this.setShowValueHelp(true);
		this.setShowTableSuggestionValueHelp(false);
		this.attachValueHelpRequest(this._onChooseFromList);
		//this.setSuggestionItems("")
	    this.attachSuggestionItemSelected(this.onSuggestionItemSelected);
	    this.attachSuggest(this._onSuggest);
	};
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	var dataFormat = this.getDataFormat();
    	this.metaCol = CoreUtil.getMdColumnByBind(dataFormat);
    	
    	this.bindDataValue({parts: [{path: this.getBindingPath("dataValue"), type:"sap.ui.model.type.Integer"}]});
    	this.buildSuggestions();

    	return rt;
     }
    theClass.prototype.buildSuggestions = function() {
		var oModel = new JSONModel([]);
		this.setModel(oModel,"filter");

    	var metaLinkToTable = CoreUtil.getMdTable(this.metaCol.linkTo);

    	for(var metaLinkToColumn of metaLinkToTable.column) {
	    	var oLabel = new sap.m.Label({text:metaLinkToColumn.description});
	    	var oColumn = new sap.m.Column({
	    		demandPopin:true,
	    		popinDisplay:"Inline",
	    		header:oLabel
	    		});
    		this.addSuggestionColumn(oColumn);
    	}
    	var cells = [];
    	for(var metaLinkToColumn of metaLinkToTable.column) {
    		var oLabel = new sap.m.Label({text:"{filter>"+metaLinkToColumn.id+"}"});
   			cells.push(oLabel);
    	}
    	var oColListItem = new sap.m.ColumnListItem({
    		cells: cells
    	});
    	this.bindSuggestionRows({
    		  path : "filter>/",
    		  template : oColListItem
    	});
    }
	theClass.prototype.setDataValue = function (value) {

		this.setProperty("dataValue", value);
		var dataFormat = this.getDataFormat();
		var table = CoreUtil.getDataBindTable(dataFormat);
		var field = CoreUtil.getDataBindField(dataFormat);
		var metaCol = CoreUtil.getMdColumn(table, field);
		var linkToTable = metaCol.linkTo;
		var that = this;
		var desc = ApiUtils.getDescription(linkToTable, value, function(val){
			//that.setDataDesc(val);
			that.setValue(val);			
		});
		//ApiUtils.finishBatchDesc();
	}
	/*theClass.prototype.getDataValue = function () {
		var str = this.getProperty("dataValue");
		var dataFormat = this.getDataFormat();
		var table = CoreUtil.getDataBindTable(dataFormat);
		var field = CoreUtil.getDataBindField(dataFormat);
		this.metaCol = CoreUtil.getMdColumn(table, field);		
		var value = FormatUtil.fromString(this.metaCol, str);
		return value;
	}*/
	theClass.prototype._onChooseFromList = function (oEvent) {
		var sInputValue = this.getDataValue();
		var dataFormat = this.getDataFormat();
		var table = CoreUtil.getDataBindTable(dataFormat);
		var field = CoreUtil.getDataBindField(dataFormat);
		this.metaCol = CoreUtil.getMdColumn(table, field);
		table = this.metaCol.linkTo;
		this._cflDialog = new CflDialog(this, table);
        this._cflDialog.open(sInputValue);
	};
	theClass.prototype.onSuggestionItemSelected = function (evt) {
		alert(1);
	}
	theClass.prototype._onSuggest = function (evt) {
		var sTerm = evt.getParameter("suggestValue");
		var oModel = new JSONModel();
		var url ="/api/"+this.metaCol.linkTo+"/";//"?id="+sTerm;
		oModel.loadData(url,null,false);
		this.setModel(oModel,"filter");
	}
	return theClass;
});