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
	    var that = this;
	    var oView = this.getView();
		oView.addEventDelegate({
		  onAfterShow: function(evt){
			that.refresh();
		  },
		  onAfterRendering: function(evt){
			ServiceUtil.finishBatchDesc();
		  },
		}, oView);
	}

    theClass.prototype.onTestClick = function () {
			//var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			//oRouter.navTo("detail");


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
        oModelList.refresh();
        this.getView().invalidate();
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
    };
    
    theClass.prototype.openQuickView= function (oEvent, oModel) {
		this.createPopover();

		this._oQuickView.setModel(oModel);

		// delay because addDependent will do a async rerendering and the actionSheet will immediately close without it.
		var oButton = oEvent.getSource();
		jQuery.sap.delayedCall(0, this, function () {
			this._oQuickView.openBy(oButton);
		});
	};

	theClass.prototype.onQuickView= function (oEvent) {
		this.openQuickView(oEvent, this.oModel);
	};

	theClass.prototype.createPopover= function() {
		if (!this._oQuickView) {
			this._oQuickView = sap.ui.xmlfragment("next.share.quick.OCRD", this);
			this.getView().addDependent(this._oQuickView);
		}
	};
	return theClass;

});