sap.ui.define([
	"next/core/controller/BaseDetailController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"next/core/controller/ServiceUtil",
], function (BaseClass, MessageToast, Fragment, JSONModel, ServiceUtil) {
	"use strict";

	var theClass =BaseClass.extend("next.app.order.controller.Detail", {
		dataTable:'ORDR'
	});

	theClass.prototype.onInit=function() {
		BaseClass.prototype.onInit.call(this);

	}

	theClass.prototype.onChange=function(evt) {
		var oModel = this.getOwnerComponent().getModel()
		var data = oModel.getData();
		var newData = ServiceUtil.change(data,"ORDR","",0);
		oModel.setData(newData);
		this.getView().invalidate();
	}	
	return theClass;
});