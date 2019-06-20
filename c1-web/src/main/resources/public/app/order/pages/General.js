sap.ui.define(['sap/uxap/BlockBase'], function (BlockBase) {
	"use strict";

	var GeneralPage =  BlockBase.extend("next.app.pages.General", {
		metadata: {
		}
	});
	GeneralPage.prototype.onInit=function() {
		BlockBase.prototype.onInit.call(this);
	}
	GeneralPage.prototype.onChangeBpCurrency=function(oEvent) {
		console.log("change");
	}
	GeneralPage.prototype.onChangeFName=function(oEvent) {
		console.log("change");
	}
	return GeneralPage;
}, true);
