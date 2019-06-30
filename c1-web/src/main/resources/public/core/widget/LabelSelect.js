sap.ui.define([
	"sap/ui/core/Control",
	"./Select",
	"sap/m/Label",
	"next/core/widget/CoreUtil"
],
function(BaseClass, Select, Label, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelSelect", { 
		metadata: {
			interfaces : ["sap.ui.core.IFormContent","sap.ui.core.Label"],
			properties: {
				label: { type: "string", group: "Misc", defaultValue: "" },
				dataFormat: { type: "string", group: "Misc", defaultValue: null },
				dataValue: { type: "string", group: "Misc", defaultValue: null }
			},
			aggregations: {
				"_label" : {type : "sap.m.Label", multiple : false},
				"_input" : {type : "next.core.widget.Select", multiple : false}
		    },			
		}
	});
	theClass.prototype.init = function () {
		BaseClass.prototype.init.call(this);		
	}
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
		var oInput = new Select({width:"100%", 
			selectedKey:{parts: [{path: this.getBindingPath("dataValue")}] },
			dataFormat: this.getDataFormat()
		});
		this.setAggregation("_input", oInput);
		var oLabel = new Label({width:"100%"});
		this.setAggregation("_label", oLabel);
    	oLabel.setText(this.getLabel());
    	return rt;
     }	  

	return theClass;
});