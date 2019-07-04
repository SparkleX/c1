sap.ui.define([
	"sap/ui/table/Table"
], function (BaseClass) {
	"use strict";
	var theClass =  BaseClass.extend("next.core.widget.Grid", {
		metadata : {
			properties : {

			}
		}
	});
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    }

	return theClass;
});