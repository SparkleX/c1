sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"./FormMode",
	"./ServiceUtil"
], function (Controller, MessageToast, Fragment, JSONModel, FormMode, ServiceUtil) {
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
	BaseDetailController.prototype.onPressEdit = function()	{
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel("form");
		var data = omFormMode.getData();
		data.formMode = FormMode.editMode;
		omFormMode.refresh(true);
		var newButton = this.byId("newButton");
		newButton.setVisible(true);
		var id1 = this.byId("id1");
		var id2 = this.byId("id2");
	};
	BaseDetailController.prototype.onPressCancel = function()	{
        this.objectPageLayout.setShowFooter(false);
	};
	BaseDetailController.prototype.onPressSave = function()	{
		this.saveObject();
		//var component =  this.getOwnerComponent();
		//var omFormMode = component.getModel("form");
		//var data = omFormMode.getData();
		//data.formMode = FormMode.viewMode;
		//omFormMode.refresh(true);
		this.objectPageLayout.setShowFooter(false);
	};
	BaseDetailController.prototype.saveObject = function()	{
		var component =  this.getOwnerComponent();
		var model = component.getModel();
		var data = model.getData();
		console.log(data);
		ServiceUtil.create(this.dataTable, data);

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