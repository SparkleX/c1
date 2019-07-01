sap.ui.define([ 
	"sap/ui/core/mvc/Controller", 
	"sap/ui/model/json/JSONModel",
	"sap/ui/core/Fragment",
	"next/app/controller/Dialog",
], function(Controller, JSONModel, Fragment,Dialog) {
	"use strict";
	var theClass = Controller.extend("next.app.controller.App", {

	});

	theClass.prototype.onInit = function() {
		this.data = {
			a : 1,
			b : 2
		};
		var oModel = new JSONModel(this.data);
		this.getView().setModel(oModel);
	}
	theClass.prototype.onTest = function() {
		var oView = this.getView();
		Fragment.load({
			id : "dialog",
			name : "next.app.view.Dialog",
			//controller : this
			controller : new Dialog()
		}).then(function(oDialog) {
			oDialog.setModel(oView.getModel());
			oDialog.open();
		});
	}
	return theClass;

});
