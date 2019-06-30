sap.ui.define([
	"sap/m/InputRenderer",
	"sap/m/LabelRenderer",	
], function (InputRenderer, LabelRenderer) {
	"use strict";
	var theClass = {};
	
	theClass.render=function(oRm, oElement){
		oRm.renderControl(oElement.getAggregation("_label"));
		oRm.renderControl(oElement.getAggregation("_input"));	
	}
	return theClass;
});