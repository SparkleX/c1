sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Input",
	"sap/m/Label",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"next/core/widget/CoreUtil"
],
function(BaseClass, Input, Label, TextAlign,NumberFormat, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelInput", { 
		metadata: {
			interfaces : ["sap.ui.core.IFormContent"],
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
		  this.setAggregation("_input", new sap.m.Input({type:"Text"}));
		  this.setAggregation("_label", new sap.m.Label());		
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

	return theClass;
});