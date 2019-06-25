sap.ui.define([
	"sap/m/Input",
	"next/core/view/CflDialog",
    "sap/ui/model/json/JSONModel",
    "next/core/widget/CoreUtil"
],
function(Input, CflDialog, JSONModel, CoreUtil) {
	"use strict";
	var theClass = Input.extend("next.core.widget.LinkInput", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
	theClass.prototype.init = function () {
		Input.prototype.init.call(this);
		
		this.setPlaceholder("Enter Product ...");
		this.setShowSuggestion(true);
		this.setShowValueHelp(true);
		this.attachValueHelpRequest(this._onChooseFromList);
		//this.setSuggestionItems("")
	    this.attachSuggestionItemSelected(this.suggestionItemSelected);
	};
	theClass.prototype.setValue = function (value) {
		this.setProperty("dataValue", value);
		Input.prototype.setValue.call(this, value);
	};	
	theClass.prototype.setDataValue = function (value) {
		this.setValue(value);
	};	
	theClass.prototype.getDataValue = function () {
		return this.getValue();
	};	
	theClass.prototype._onChooseFromList = function (oEvent) {
		var sInputValue = this.getDataValue();
		var dataFormat = this.getDataFormat();
		var table = CoreUtil.getDataBindTable(dataFormat);
		var field = CoreUtil.getDataBindField(dataFormat);
		var metaCol = CoreUtil.getMdColumn(table, field);
		table = metaCol.linkTo;
		this._cflDialog = new CflDialog(this, table);
        this._cflDialog.open(sInputValue);
	};
	return theClass;
});