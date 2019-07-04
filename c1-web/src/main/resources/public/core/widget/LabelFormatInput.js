sap.ui.define([
	"./BaseLabelControl",
	"./FmtInput",
	"sap/m/Label",
	"next/core/widget/CoreUtil"
],
function(BaseClass, FmtInput, Label, CoreUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.LabelFormatInput", { 
		metadata: {
			aggregations: {
				"_input" : {type : "next.core.widget.FmtInput", multiple : false},
		    },	
			events: {
				dataChange:{
					parameters: {
						value: {
							type: "string"
						}
					}
				}
			}		
		}
	});
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	
		var oInput = new FmtInput({
			type:"Text",
			width:"100%",
			dataValue: {parts: [{path: this.getBindingPath("dataValue")}]},
			dataFormat: this.getDataFormat(),
			editableAddMode: this.getEditableAddMode(),
			editableEditMode: this.getEditableEditMode(),
			dataChange: this.onDataChange
				});

		this.setAggregation("_input", oInput);
    	return rt;
    }	
    theClass.prototype.onDataChange = function(evt) {
    	var source = evt.getSource();
    	var that = source.getParent();
    	var params = evt.getParameters();
    	return that.fireDataChange(params);
     }    
	return theClass;
});