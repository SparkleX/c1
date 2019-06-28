sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"./FormMode",
	"./ServiceUtil",
    "next/core/controller/RouterUtil",
    "next/core/widget/WidgetUtil",
], function (Controller, MessageToast, Fragment, JSONModel, FormMode, ServiceUtil, RouterUtil, WidgetUtil) {
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
			    	this.formMode = FormMode.addMode;
			        this.onInitData();
			    }
				else {
					this.formMode = FormMode.viewMode;
				    this.onLoadData(this.dataId);
				}
			}, this);
	};
	BaseDetailController.prototype.isAddMode = function() {
	    return this.dataId ==="#";
	}
	BaseDetailController.prototype.onLoadData = function(id) {
		this.dataId = id;
		var oModel = new JSONModel();
		this.oModel = oModel;
		oModel.loadData(`/api/${this.dataTable}/${id}`);
		this.getOwnerComponent().setModel(oModel);
		this.onRefreshUiStatus();
	}
	BaseDetailController.prototype.onInitData = function() {
	    var json = {};
        var oModel = new JSONModel(json);
		this.getOwnerComponent().setModel(oModel);
		this.onRefreshUiStatus();
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
		this.formMode=FormMode.editMode;
		this.onRefreshUiStatus();
	};
	BaseDetailController.prototype.onCancel = function()	{
		this.formMode=FormMode.viewMode;
        this.onRefreshUiStatus();
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
		this.formMode=FormMode.addMode;
        this.onRefreshUiStatus();
	}


	BaseDetailController.prototype.onTest = function()	{
		var component =  this.getOwnerComponent();
		var omFormMode = component.getModel();
		var data = omFormMode.getData();
		//var data = this.oModel.getData();
		console.log(data);
	}
	BaseDetailController.prototype.onNext = function()	{
		var that = this;
		var id = ServiceUtil.getNext(this.dataTable, this.dataId, function(id){
			that.onLoadData(id);
		}, function(){
			MessageToast.show("Last Records");
		});
		
	}
	BaseDetailController.prototype.onPrev = function()	{
		var that = this;
		var id = ServiceUtil.getPrev(this.dataTable, this.dataId, function(id){
			that.onLoadData(id);
		}, function(){
			MessageToast.show("First Records");
		});
	}	
	BaseDetailController.prototype.onRefresh = function()	{
		this.onLoadData(this.dataId);
	}	
	BaseDetailController.prototype.onRefreshUiStatus = function()	{
		if(this.formMode === FormMode.addMode) {
	        this.objectPageLayout.setShowFooter(true);
	        this.editButton.setVisible(false);
	        this.newButton.setVisible(false);
	        this.deleteButton.setVisible(false);
	        this.setOrigStatus();	        
		}
		if(this.formMode === FormMode.viewMode) {
	        this.objectPageLayout.setShowFooter(false);
	        this.editButton.setVisible(true);
	        this.newButton.setVisible(true);
	        this.deleteButton.setVisible(true);
	        this.setReadonlyStatus();
		}
		if(this.formMode === FormMode.editMode) {
	        this.objectPageLayout.setShowFooter(true);
	        this.editButton.setVisible(false);
	        this.newButton.setVisible(true);
	        this.deleteButton.setVisible(true);
	        this.setOrigStatus();
		}		
	}
	BaseDetailController.prototype.setOrigStatus = function()	{
		WidgetUtil.scan(this.getView(), WidgetUtil.editableTrue);
	}
	BaseDetailController.prototype.setReadonlyStatus = function()	{
		WidgetUtil.scan(this.getView(), WidgetUtil.editableFalse);
	}	
	return BaseDetailController;

});