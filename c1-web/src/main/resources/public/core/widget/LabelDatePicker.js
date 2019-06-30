sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/DatePicker",
	"sap/m/Label",
],
function(BaseClass, DatePicker, Label) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelDatePicker", { 
		metadata: {
			interfaces : ["sap.ui.core.IFormContent"],
			properties: {
				label: { type: "string", group: "Misc", defaultValue: "" },
				dataFormat: { type: "string", group: "Misc", defaultValue: null },
				dataValue: { type: "string", group: "Misc", defaultValue: null }
			},
			aggregations: {
				"_label" : {type : "sap.m.Label", multiple : false},
				"_input" : {type : "sap.m.DatePicker", multiple : false}
		    },			
		}
	});
	theClass.prototype.init = function () {
		BaseClass.prototype.init.call(this);
		 var that = this;
		  this.setAggregation("_input", new sap.m.DatePicker());
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