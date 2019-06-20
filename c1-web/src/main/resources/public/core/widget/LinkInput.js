sap.ui.define([
	"sap/m/Input"
],
function(Input) {
	"use strict";
	var theClass = Input.extend("next.core.widget.LinkInput", { 
	metadata: {
		properties: {
			bind: { type: "string", group: "Misc", defaultValue: null },
			data: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
	theClass.prototype.init = function () {
		Input.prototype.init.call(this);
		this.setPlaceholder("Enter Product ...");
		this.setShowSuggestion(true);
		this.setShowValueHelp(true);
		this.attachValueHelpRequest(this.handleValueHelp);
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
	theClass.prototype.handleValueHelp = function (oEvent) {
        var sInputValue = oEvent.getSource().getValue();

        this.inputId = oEvent.getSource().getId();
        // create value help dialog
        if (!this._valueHelpDialog) {
            this._valueHelpDialog = sap.ui.xmlfragment(
                "next.core.view.Dialog",
                this
            );
            //this.getView().addDependent(this._valueHelpDialog);
        }

        // create a filter for the binding
       /* this._valueHelpDialog.getBinding("items").filter([new Filter(
            "Name",
            sap.ui.model.FilterOperator.Contains, sInputValue
        )]);*/

        // open value help dialog filtered by the input value
        this._valueHelpDialog.open(sInputValue);
    
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