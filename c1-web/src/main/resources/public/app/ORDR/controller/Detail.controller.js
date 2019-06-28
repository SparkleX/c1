sap.ui.define([
	"next/core/controller/BaseDetailController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel"
], function (BaseDetailController, MessageToast, Fragment, JSONModel) {
	"use strict";

	var Detail =BaseDetailController.extend("next.app.order.controller.Detail", {
		dataTable:'ORDR'
	});

	Detail.prototype.onInit=function() {
		BaseDetailController.prototype.onInit.call(this);

	}

	Detail.prototype.onChangeFName=function(oEvent) {
		console.log("change");
	}
	return Detail;
});