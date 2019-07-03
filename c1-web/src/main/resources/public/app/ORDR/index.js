sap.ui.define([
	"sap/ui/core/ComponentContainer"
], function (ComponentContainer) {
	"use strict";

	new ComponentContainer({
		name: "next.app.ORDR",
		settings : {
			id : "order"
		},
		async: true
	}).placeAt("content");
});