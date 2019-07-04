sap.ui.define([
	"next/core/controller/BaseDetailController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"next/core/controller/ApiUtils",
], function (BaseClass, MessageToast, Fragment, JSONModel, ApiUtils) {
	"use strict";

	var theClass =BaseClass.extend("next.app.controller.Detail", {
		dataTable:'ORDR'
	});

	theClass.prototype.onInit=function() {
		BaseClass.prototype.onInit.call(this);

	}
	theClass.prototype.onDummy=function (evt) {
		alert('a');
	}
	theClass.prototype.onChange=function(evt) {

		var oView = this.getView();
		var oView2 = this.getView("idView");
		alert('a');
		/*var oModel = this.getOwnerComponent().getModel()
		var data = oModel.getData();
		var newData = ApiUtils.change(data,"ORDR","",0);
		oModel.setData(newData);
		this.getView().invalidate();*/
	}
	theClass.prototype.onAddressChange=function(item) {
		alert('a');
	}	
	theClass.prototype.onCalcTotal=function(evt) {
		var oModel = this.getView().getModel()
		var data = oModel.getData();
		var newData = ApiUtils.change(data,"ORDR","",0);
		oModel.setData(newData);
		this.getView().invalidate();
	}	
	
	return theClass;
});