sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"./FormMode"
], function (Controller, MessageToast, Fragment, JSONModel, FormMode) {
	"use strict";

	var BaseDetailController=Controller.extend("app.core.controller.BaseDetailController", {});

	BaseDetailController.prototype.onInit=function() {
			var component =  this.getOwnerComponent();
			var oRouter = component.getRouter();
			oRouter.getRoute("detail").attachMatched(function(oEvent) {
				this.onLoad(oEvent.getParameter("arguments").id);
			}, this);
			
			var data = {editable:false, formMode: FormMode.viewMode}
			var omFormMode = new JSONModel(data);
			component.setModel(omFormMode, "form");

	};
	BaseDetailController.prototype.onLoad = function(id) {
		var oModel = new JSONModel();
		oModel.loadData("/api/ORDR/"+id);	
		this.getOwnerComponent().setModel(oModel);
		this.getView().setModel(oModel);
	};
	BaseDetailController.prototype.onNavBack = function()
	{
		window.history.back();
	};
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
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel("form");
		var data = omFormMode.getData();
		data.formMode = FormMode.viewMode;
		omFormMode.refresh(true);
	};
	BaseDetailController.prototype.onPressSave = function()	{
		this.saveObject();
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel("form");
		var data = omFormMode.getData();
		data.formMode = FormMode.viewMode;
		omFormMode.refresh(true);
	};
	BaseDetailController.prototype.saveObject = function()	{
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel();
		var data = omFormMode.getData();
		console.log(data);
		
	}
	return BaseDetailController;

});