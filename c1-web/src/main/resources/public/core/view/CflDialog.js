sap.ui.define([
	"sap/ui/base/ManagedObject",
	"sap/ui/core/mvc/Controller",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
    'sap/ui/model/Filter'
], function (ManagedObject, Controller, Fragment, JSONModel, Filter) {
	"use strict";
	var theClass = Controller.extend("next.core.view.CflDialog", {
		constructor : function (oView) {
			this._oView = oView;
		}		
	});

	theClass.prototype.exit = function () {
		delete this._oView;
	}

	theClass.prototype.open = function (inputId) {
		var oView = this._oView;

		this._oDialog = sap.ui.xmlfragment("next.core.view.CflDialog", this);
	/*	Fragment.load({
			id: "cfl",
			name: "next.core.view.CflDialog",
			controller: this
		}).then(function (oDialog) {*/
        oView.addDependent(this._oDialog);
        var oModelList = new JSONModel();
        oModelList.loadData("/api/OCRD/");
        /*oModelList.attachRequestCompleted(function() {
            //console.log(oModelList.getData());
        });*/
        this._oDialog.setModel(oModelList, "cfl");
        this._oDialog.open();
		//});
	};
	theClass.prototype._handleSearch = function(oEvent) {
		var sValue = oEvent.getParameter("value");
		var oFilter = new Filter({
		  path: "bpCode",
		  operator: sap.ui.model.FilterOperator.Contains,
		  value1: sValue});
		var oBinding = oEvent.getSource().getBinding("items");
		oBinding.filter([oFilter]);
	}		
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
       // this.setValue("1");
    }	

	return theClass;

});