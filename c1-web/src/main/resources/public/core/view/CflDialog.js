sap.ui.define([
	"sap/ui/base/ManagedObject",
	"sap/ui/core/mvc/Controller",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
    'sap/ui/model/Filter',
    "next/core/widget/CoreUtil"
], function (ManagedObject, Controller, Fragment, JSONModel, Filter, CoreUtil) {
	"use strict";
	var theClass = Controller.extend("next.core.view.CflDialog", {
		constructor : function (oView, table) {
			this._oView = oView;
			this._table = table;
			this._metaTable = CoreUtil.getMdTable(table);
		}		
	});

	theClass.prototype.exit = function () {
		delete this._oView;
	}

	theClass.prototype.open = function (inputId) {
		var oView = this._oView;

		this._oDialog = sap.ui.xmlfragment("next.core.view.CflDialog", this);
		//this._oDialog = new sap.ui.core.Fragment("next.core.view.CflDialog", this);
        oView.addDependent(this._oDialog);
        var oModelList = new JSONModel();

        oModelList.loadData("/api/"+this._table+"/");
        this._oDialog.setModel(oModelList, "cfl");
        
		var oItemTemplate = new sap.m.StandardListItem({
				title: "{cfl>id}",
				description: "{cfl>"+this._metaTable.descColumn+"}",
				type: "Active"
			})
        this._oDialog.bindAggregation("items", "cfl>/", oItemTemplate);
        this._oDialog.open();
	};
	theClass.prototype._onSearch = function(oEvent) {
		var sValue = oEvent.getParameter("value");
		var oFilter = new Filter({
		  path: this._metaTable.descColumn,
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
        this._oView.setDataValue(item);
    }
	return theClass;
});