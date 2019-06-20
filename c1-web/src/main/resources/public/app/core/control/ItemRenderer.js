sap.ui.define([
	"sap/ui/core/Control",
	'sap/ui/core/Renderer'
], function (Control, Renderer) {
	"use strict";
	var renderer = {};//;Renderer.extend(InputBaseRenderer);
	
	renderer.render = function(oRenderManager, oItem) {
	  // oSearchField.updateAllParts(); // called depending on your 'sync' approach
	  oRenderManager.write("<div>"); 
	  //oRenderManager.writeControlData(oItem);
	  //oRenderManager.addClass("SearchField"); 
	  //oRenderManager.writeClasses();
	  //oRenderManager.write(">");
	  oRenderManager.renderControl(oItem.getAggregation("_label"));
	  oRenderManager.renderControl(oItem.getAggregation("_input"));	  
	  oRenderManager.write("</div>");
	};	

	return renderer;
}, true);