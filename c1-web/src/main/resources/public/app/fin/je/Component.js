sap.ui.define([
	"sap/ui/core/UIComponent",
	"sap/ui/model/json/JSONModel"
], function (UIComponent, JSONModel) {
	"use strict";
	return UIComponent.extend("app.fin.je.Component", {
		metadata : {
			manifest: "json"
		},

		init : function () {
			UIComponent.prototype.init.apply(this, arguments);
			this.getRouter().initialize();
			var oModelList = new JSONModel();
			oModelList.loadData("mock/list.json",null,false);	
			this.setModel(oModelList, "list");				
		}
	});

}, /*export*/ true);