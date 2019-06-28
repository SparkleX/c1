sap.ui.define([
	"next/core/controller/BaseListController",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment"
], function (Controller, MessageToast, Fragment) {
	"use strict";

	var theClass = Controller.extend("next.app.controller.List", {
		dataTable : "OCRD"
	});
	
    
	return theClass;

});