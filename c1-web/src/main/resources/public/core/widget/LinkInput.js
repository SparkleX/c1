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
		this.attachValueHelpRequest(this._onChooseFromList);
		//this.setSuggestionItems("")
	   // this.attachSuggestionItemSelected(this.suggestionItemSelected);
	};
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	//var oBinding = this.getBindingInfo("dataValue");
    	//oBinding.parts[0].type = sap.ui.model.type.Integer;
    	this.bindDataValue({parts: [{path: this.getBindingPath("dataValue"), type:"sap.ui.model.type.Integer"}]});
    	return rt;
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
	return theClass;
});