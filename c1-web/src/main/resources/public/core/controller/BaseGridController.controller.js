sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"next/core/controller/RouterUtil",
	"next/core/controller/ServiceUtil"
], function (Controller, MessageToast, Fragment, JSONModel, RouterUtil, ServiceUtil) {
	"use strict";

	var theClass =Controller.extend("next.core.controller.BaseListController", {});
	theClass.prototype.onInit=function() {
	}
	theClass.prototype.onGridAdd = function(evt){
	    alert('a');
	}
	theClass.prototype.onGridDelete = function(evt){
		var table = evt.getSource().getParent().getParent();
		var rowid = table.getSelectedIndices();
		var model = this.getView().getModel()
        for(let row of rowid) {
			var data = model.getData().rdr1;
			var removed = data.splice(row, 1);
        }
        model.refresh(true);
	}
	return theClass;
});