sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"next/core/controller/RouterUtil"
], function (Controller, MessageToast, Fragment, RouterUtil) {
	"use strict";

	var theClass =Controller.extend("next.core.controller.BaseListController", {});

    theClass.prototype.onTestClick = function () {
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.navTo("detail");
	}
	theClass.prototype.onListItemPress = function (evt) {
        var oRow = evt.getParameter("row");
		var oItem = evt.getParameter("item");
		var id = this.getView().getModel("list").getProperty("id", oRow.oBindingContexts.list);

        RouterUtil.navTo(this, id);
	}
    theClass.prototype.onAdd = function (evt) {
        RouterUtil.navToNew(this);
    }
	return theClass;

});