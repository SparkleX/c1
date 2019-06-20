sap.ui.define([
	"sap/ui/core/ComponentContainer"
], function (ComponentContainer) {
	"use strict";

	new ComponentContainer({
		name: "next.app.order",
		settings : {
			id : "order"
		},
		async: true
	}).placeAt("content");
});