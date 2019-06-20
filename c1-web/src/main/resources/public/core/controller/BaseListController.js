sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment"
], function (Controller, MessageToast, Fragment) {
	"use strict";

	return Controller.extend("next.core.controller.BaseListController", {
		onTestClick: function () {
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail");
		},
		onListItemPress : function (evt) {
			var id = evt.getSource().getTitle()
			//MessageToast.show("Pressed : " + evt.getSource().getTitle());
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail",{
                id: id
            });
		}
	});

});