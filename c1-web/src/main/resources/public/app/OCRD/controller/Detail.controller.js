sap.ui.define([
	"next/core/controller/BaseDetailController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel"
], function (BaseDetailController, MessageToast, Fragment, JSONModel) {
	"use strict";
	var theClass =BaseDetailController.extend("next.app.order.controller.Detail", {});

	theClass.prototype.onInit=function() {
		BaseDetailController.prototype.onInit.call(this);

	}
	return theClass;
});