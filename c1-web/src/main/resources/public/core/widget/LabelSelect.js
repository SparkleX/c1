sap.ui.define([
	"./BaseLabelControl",
	"./Select",
	"sap/m/Label",
	"next/core/widget/CoreUtil"
],
function(BaseClass, Select, Label, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelSelect", { 
		metadata: {
			aggregations: {
				"_input" : {type : "next.core.widget.Select", multiple : false}
		    },			
		}
	});

    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
		var oInput = new Select({width:"100%", 
			selectedKey:{parts: [{path: this.getBindingPath("dataValue")}] },
			dataFormat: this.getDataFormat()
		});
		this.setAggregation("_input", oInput);
    	return rt;
     }	  

	return theClass;
});