sap.ui.define([
	"sap/ui/core/Control",
	"sap/ui/core/Renderer",
	"sap/m/InputRenderer",
	"sap/m/LabelRenderer",	
], function (Control, Renderer, InputRenderer, LabelRenderer) {
	"use strict";
	var theClass = {};//Renderer.extend(InputRenderer);
	//theClass.labelRender = new LabelRenderer();
	
	theClass.render=function(oRm, oElement){
		oRm.renderControl(oElement.getAggregation("_label"));
		oRm.renderControl(oElement.getAggregation("_input"));		
	}
	return theClass;
});