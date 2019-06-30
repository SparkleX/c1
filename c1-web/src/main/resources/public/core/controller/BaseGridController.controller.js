sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageToast",
	"sap/ui/core/Fragment",
	"sap/ui/model/json/JSONModel",
	"next/core/controller/RouterUtil",
	"next/core/controller/ApiUtils"
], function (Controller, MessageToast, Fragment, JSONModel, RouterUtil, ApiUtils) {
	"use strict";

	var theClass =Controller.extend("next.core.controller.BaseListController", {});
	theClass.prototype.onInit=function() {
	}
	theClass.prototype.onGridAdd = function(evt){
		var table = evt.getSource().getParent().getParent();
		var rowid = table.getSelectedIndices();
		var model = this.getView().getModel();
		var root = model.getData();
		if(root.RDR1===undefined) root.RDR1 = [];
		var data = root.RDR1;
		if (rowid.length==0) {
			data.push({});
		} else if(rowid.length==1) {
			var row =rowid[0];			
			data.splice(row,0,{});
		} else {
			MessageToast.show("more than one line selected");
		}
        model.refresh(true);
	}
	theClass.prototype.onGridDelete = function(evt){
		var table = evt.getSource().getParent().getParent();
		var rowid = table.getSelectedIndices();
		var model = this.getView().getModel();
		var count = 0;
        for(let row of rowid) {
			var data = model.getData().RDR1;
			var removed = data.splice(row-count, 1);
			count++;
        }
        model.refresh(true);
        table.clearSelection();
	}
	return theClass;
});