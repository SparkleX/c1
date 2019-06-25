sap.ui.define([
	"sap/m/Input",
	"next/core/view/CflDialog",
    "sap/ui/model/json/JSONModel"
],
function(Input, CflDialog, JSONModel) {
	"use strict";
	var theClass = Input.extend("next.core.widget.LinkInput", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			data: { type: "string", group: "Misc", defaultValue: null }
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
		this.setProperty("data", value);
		Input.prototype.setValue.call(this, value);
	};	
	theClass.prototype.setData = function (value) {
		this.setValue(value);
	};	
	theClass.prototype.getData = function () {
		return this.getValue();
	};	
	// Choose from list dialog
	theClass.prototype._onChooseFromList = function (oEvent) {
		var sInputValue = this.getData();
		this._cflDialog = new CflDialog(this);
        this._cflDialog.open(sInputValue);
	};
	theClass.prototype._handleValueHelpClose = function (evt) {
        var oSelectedItem = evt.getParameter("selectedItem");
        if (oSelectedItem) {
            var productInput = this.byId(this.inputId),
                oText = this.byId('selectedKey'),
                sDescription = oSelectedItem.getDescription();

            productInput.setSelectedKey(sDescription);
            oText.setText(sDescription);
        }
        //evt.getSource().getBinding("items").filter([]);
        this.setValue("1");
    }	
	theClass.prototype.suggestionItemSelected = function (event) {
	    alert('suggestionItemSelected');
	};		
	return theClass;
});