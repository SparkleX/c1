sap.ui.define([
],
function() {
	"use strict";

	var theClass = {}
	theClass.getMetadata=function(column) {
        var rt = { validValues:[{value:"Y", desc:"Yes"}, {value:"N", desc:"No"}]};
        return rt;
	}

	return theClass;
});