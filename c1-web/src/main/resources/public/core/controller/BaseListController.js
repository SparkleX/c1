sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"next/core/controller/RouterUtil",
	"next/core/controller/ServiceUtil"
], function (Controller, MessageToast, Fragment, JSONModel, RouterUtil, ServiceUtil) {
	"use strict";

	var theClass =Controller.extend("next.core.controller.BaseListController", {});
	theClass.prototype.onInit=function() {
	    this.dataTable = "ORDR";
	    var that = this;
	    var oView = this.getView();
		oView.addEventDelegate({
		  onAfterShow: function(evt){
			that.refresh();
		  }
		}, oView);
	}

    theClass.prototype.onTestClick = function () {
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail");
	}
	theClass.prototype.onListItemPress = function (evt) {
        var oRow = evt.getParameter("row");
		var oItem = evt.getParameter("item");
		var id = this.getView().getModel("list").getProperty("id", oRow.oBindingContexts.list);

        RouterUtil.navTo(this, id);
	}
	theClass.prototype.refresh = function () {
        var oModelList = new JSONModel();
        oModelList.loadData("/api/"+this.dataTable+"/");
        oModelList.attachRequestCompleted(function() {
        });
        this.getOwnerComponent().setModel(oModelList, "list");
        oModelList.refresh(true);
	}
    theClass.prototype.onAdd = function (evt) {
        RouterUtil.navToNew(this);
    }
    theClass.prototype.onDelete = function (evt) {
        var tb = this.byId("listTable");
        var rowid = tb.getSelectedIndices();

        for(let row of rowid) {
        	var id = tb.getRows()[row].getCells()[0].getText();
        	ServiceUtil.delete(this.dataTable, id);
        }
        MessageToast.show("Successful");
        this.refresh();
    }
	return theClass;

});