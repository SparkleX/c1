sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"./FormMode",
	"./ServiceUtil",
    "next/core/controller/RouterUtil",
], function (Controller, MessageToast, Fragment, JSONModel, FormMode, ServiceUtil, RouterUtil) {
	"use strict";

	var BaseDetailController=Controller.extend("app.core.controller.BaseDetailController", {});

	BaseDetailController.prototype.onInit=function() {
			this.objectPageLayout = this.byId("objectPageLayout");
            this.editButton = this.byId("editButton");
            this.newButton = this.byId("newButton");
            this.deleteButton = this.byId("deleteButton");
            this.dataTable = "ORDR";

			var component =  this.getOwnerComponent();
			var oRouter = component.getRouter();
			oRouter.getRoute("detail").attachMatched(function(oEvent) {
			    this.dataId = oEvent.getParameter("arguments").id
			    if(this.dataId==="#")   {
			        this.onInitData();
			    }
				else {
				    this.onLoadData(this.dataId);
				}
			}, this);
	};
	BaseDetailController.prototype.isAddMode = function() {
	    return this.dataId ==="#";
	}
	BaseDetailController.prototype.onLoadData = function(id) {
		var oModel = new JSONModel();
		this.oModel = oModel;
		oModel.loadData(`/api/${this.dataTable}/${id}`);
		this.getOwnerComponent().setModel(oModel);

        this.objectPageLayout.setShowFooter(false);
        this.editButton.setVisible(true);
        this.newButton.setVisible(true);
        this.deleteButton.setVisible(true);
	}
	BaseDetailController.prototype.onInitData = function() {
	    var json = {};
        var oModel = new JSONModel(json);
		this.getOwnerComponent().setModel(oModel);
        this.objectPageLayout.setShowFooter(true);
        this.editButton.setVisible(false);
        this.newButton.setVisible(false);
        this.deleteButton.setVisible(false);

	}
	BaseDetailController.prototype.onNavBack = function(){
		window.history.back();
	}
	BaseDetailController.prototype.onDelete = function(){
		ServiceUtil.delete(this.dataTable, this.dataId);
		MessageToast.show("Successful");
		window.history.back();
	}
	BaseDetailController.prototype.onEdit = function()	{
        this.objectPageLayout.setShowFooter(true);
        this.editButton.setVisible(false);
        this.newButton.setVisible(true);
        this.deleteButton.setVisible(true);
	};
	BaseDetailController.prototype.onCancel = function()	{
        this.objectPageLayout.setShowFooter(false);
	};
	BaseDetailController.prototype.onSave = function()	{
	    this.saveObject();
		this.objectPageLayout.setShowFooter(false);
		MessageToast.show("Successful");
		window.history.back();
		//RouterUtil.navToList(this);
	};
	BaseDetailController.prototype.saveObject = function()	{
		var component =  this.getOwnerComponent();
		var model = component.getModel();
		var data = model.getData();
		console.log(data);
	    if(this.isAddMode()) {
    		ServiceUtil.create(this.dataTable, data);
    	} else {
    	    ServiceUtil.update(this.dataTable, this.dataId, data);
    	}

	}
	BaseDetailController.prototype.onNew = function()	{
	    this.objectPageLayout.setShowFooter(true);
	}
	BaseDetailController.prototype.onTest = function()	{
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel();
		var data = omFormMode.getData();
		//var data = this.oModel.getData();
		console.log(data);
	}
	return BaseDetailController;

});