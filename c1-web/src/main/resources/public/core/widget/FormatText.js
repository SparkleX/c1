sap.ui.define([
	"sap/m/Text",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"c1/core/util/CoreUtil",
	"./FormatUtil"
],
function(Text, TextAlign,NumberFormat, CoreUtil, FormatUtil) {
	"use strict";
	var theClass = Text.extend("c1.core.widget.FormatText", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = Text.prototype.applySettings.call(this, mSettings, oScope);    	
    	var dataFormat = this.getDataFormat(); 
    	var format = FormatUtil.format(dataFormat);
    	if(format.right){
    		this.setTextAlign(TextAlign.Right);
    	}
		this.setWidth("100%");		
    	return rt;
     }	
	theClass.prototype.formatValue = function (value, fn) {
		var dataFormat = this.getDataFormat(); 
		FormatUtil.formatValue(dataFormat, value, fn);
	}
	
	theClass.prototype.setDataValue = function (value) {
		this.setProperty("dataValue", value);
		var that = this;
		var formattedVal = this.formatValue(value, function(val){
			that.setText(val);	
		});		
		
	}
	
	return theClass;
});