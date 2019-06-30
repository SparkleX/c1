sap.ui.define([
	"sap/m/Text",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"next/core/widget/CoreUtil",
	"./FormatUtil"
],
function(Text, TextAlign,NumberFormat, CoreUtil, FormatUtil) {
	"use strict";
	var theClass = Text.extend("next.core.widget.FormatText", { 
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
	theClass.prototype.setText = function (value) {
		//var formattedVal = this.formatValue(value);
		//this.setProperty("dataValue", value);
		Text.prototype.setText.call(this, value);
	}
	theClass.prototype.getText = function () {
		return Text.prototype.getText.call(this);
	};	
	theClass.prototype.setDataValue = function (value) {

		this.setText(value);
		this.setProperty("dataValue", value);
	};	
	theClass.prototype.getDataValue = function () {
		return this.getText();
	};	
	return theClass;
});