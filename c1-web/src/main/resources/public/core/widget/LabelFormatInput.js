sap.ui.define([
	"sap/ui/core/Control",
	"./FmtInput",
	"sap/m/Label",
	"next/core/widget/CoreUtil"
],
function(BaseClass, FmtInput, Label, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelFormatInput", { 
		metadata: {
			interfaces : ["sap.ui.core.IFormContent","sap.ui.core.Label"],
			properties: {
				label: { type: "string", group: "Misc", defaultValue: "" },
				dataFormat: { type: "string", group: "Misc", defaultValue: null },
				dataValue: { type: "string", group: "Misc", defaultValue: null }
			},
			aggregations: {
				"_label" : {type : "sap.m.Label", multiple : false},
				"_input" : {type : "next.core.widget.FmtInput", multiple : false}
		    },			
		}
	});
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	
		var oInput = new FmtInput({
			type:"Text",
			width:"100%",
			dataValue: {parts: [{path: this.getBindingPath("dataValue")}]},
			dataFormat: this.getDataFormat(),
				});
		this.setAggregation("_input", oInput);
		
		var oLabel = new Label({
			width:"100%",
			text:this.getLabel()
				});
		this.setAggregation("_label", oLabel);		
    	
    	return rt;
     }	  
	return theClass;
});