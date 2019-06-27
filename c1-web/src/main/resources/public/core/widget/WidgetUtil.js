sap.ui.define([
],
function(JSONModel, jQuery) {
	"use strict";

	var theClass={};
	theClass.getRootView=function(widget) {
		var parent = widget;
		for(;;) {
			var parent = parent.getParent() 
			if(parent instanceof sap.ui.core.mvc.View) {
				return parent;
			}	
		}
		 
	}

	return theClass;
});