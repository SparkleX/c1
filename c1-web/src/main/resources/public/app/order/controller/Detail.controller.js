sap.ui.define([
	"next/core/controller/BaseDetailController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel"
], function (BaseDetailController, MessageToast, Fragment, JSONModel) {
	"use strict";

	var Detail =BaseDetailController.extend("next.app.order.controller.Detail", {});

	Detail.prototype.onInit=function() {
		BaseDetailController.prototype.onInit.call(this);

		var oModel = new JSONModel();
		oModel.loadData("mock/bpCurrency.json",null,false);	
		this.getOwnerComponent().setModel(oModel, "bpCurrency");
	}

	Detail.prototype.onChangeFName=function(oEvent) {
		console.log("change");
	}
	return Detail;
});