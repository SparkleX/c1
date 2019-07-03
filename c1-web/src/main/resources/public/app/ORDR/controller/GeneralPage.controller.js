sap.ui.define([
	"sap/ui/core/mvc/Controller"
], function (BaseClass) {
	"use strict";

	var theClass =BaseClass.extend("next.app.controller.GeneralPage", {
	});

	theClass.prototype.onInnerDummy =function (oEvent) {
		this.oParentBlock.fireDummy(oEvent.getParameters());
	}
	theClass.prototype.onClick =function (oEvent) {
		alert('a');
	}
	return theClass;
});