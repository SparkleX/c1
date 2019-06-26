sap.ui.define(['sap/uxap/BlockBase'], function (BlockBase) {
	"use strict";

	var theClass = BlockBase.extend("next.app.pages.Lines", {
		metadata: {
			/* no additional views provided */
		}
	});
	theClass.prototype.onGridAdd = function(evt) {
	}
	theClass.prototype.onGridDelete = function(evt) {
	}

	return theClass;
}, true);
