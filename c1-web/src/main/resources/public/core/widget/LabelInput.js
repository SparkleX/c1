sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Input",
	"sap/m/Label",
	"next/core/widget/CoreUtil"
],
function(BaseClass, Input, Label, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelInput", { 
		metadata: {
			interfaces : ["sap.ui.core.IFormContent","sap.ui.core.Label"],
			properties: {
				label: { type: "string", group: "Misc", defaultValue: "" },
				dataFormat: { type: "string", group: "Misc", defaultValue: null },
				dataValue: { type: "string", group: "Misc", defaultValue: null }
			},
			aggregations: {
				"_label" : {type : "sap.m.Label", multiple : false},
				"_input" : {type : "sap.m.Input", multiple : false}
		    },			
		}
	});
	theClass.prototype.init = function () {
		BaseClass.prototype.init.call(this);
		var that = this;
		var oInput = new sap.m.Input({type:"Text",width:"100%"});
		this.setAggregation("_input", oInput);
		var oLabel = new sap.m.Label({width:"100%"});
		this.setAggregation("_label", oLabel);		
	}
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	var bindInfo = this.getBindingInfo("dataValue");
    	var oInput = this.getAggregation("_input")
    	oInput.bindValue({parts: [{path: this.getBindingPath("dataValue")}] });
    	var oLabel = this.getAggregation("_label")
    	oLabel.setText(this.getLabel());
    	return rt;
     }	  
    theClass.prototype.setAlternativeLabelFor = function() {
    	
    }

	return theClass;
});