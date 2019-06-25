sap.ui.define([
	"sap/ui/base/ManagedObject",
	"sap/ui/core/mvc/Controller",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
    'sap/ui/model/Filter'
], function (ManagedObject, Controller, Fragment, JSONModel, Filter) {
	"use strict";
	var theClass = Controller.extend("next.core.view.CflDialog", {
		constructor : function (oView, table) {
			this._oView = oView;
			this._table = table;
		}		
	});

	theClass.prototype.exit = function () {
		delete this._oView;
	}

	theClass.prototype.open = function (inputId) {
		var oView = this._oView;

		this._oDialog = sap.ui.xmlfragment("next.core.view.CflDialog", this);
        oView.addDependent(this._oDialog);
        var oModelList = new JSONModel();

        oModelList.loadData("/api/"+this._table+"/");
        this._oDialog.setModel(oModelList, "cfl");
        this._oDialog.open();
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
	theClass.prototype._onClose = function (evt) {
    }	
	theClass.prototype._onConfirm = function (evt) {
        var oSelectedItem = evt.getParameter("selectedItem");
        var item = oSelectedItem.getTitle();
        this._oView.setData(item);
    }
	return theClass;
});