sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment"
], function (Controller, MessageToast, Fragment) {
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

	   // var src = evt.getSource();
       // var id = evt.getSource().getTitle()
        //MessageToast.show("Pressed : " + evt.getSource().getTitle());
        var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
        oRouter.navTo("detail",{
            id: id
        });
	}

	return theClass;

});