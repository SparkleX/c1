sap.ui.define([
	"sap/ui/core/UIComponent",
	"sap/ui/model/json/JSONModel",
	"next/core/widget/CoreUtil"
], function (UIComponent, JSONModel, CoreUtil) {
	"use strict";
	return UIComponent.extend("next.app.CINF.Component", {
		metadata : {
			manifest: "json"
		},
		init : function () {
			UIComponent.prototype.init.apply(this, arguments);
			CoreUtil.init();
			this.getRouter().initialize();
		}
	});

}, /*export*/ true);