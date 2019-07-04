sap.ui.define([
	"sap/m/Input",
	"sap/ui/core/TextAlign",
	"next/core/widget/CoreUtil",
	"next/core/widget/FormatUtil"
],
function(BaseClass, TextAlign, CoreUtil, FormatUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.FmtInput", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
	
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);    	
    	var dataFormat = this.getDataFormat(); 
    	this.metaCol = CoreUtil.getMdColumnByBind(dataFormat);
    	var format = FormatUtil.format(dataFormat);
    	if(format.right){
    		this.setTextAlign(TextAlign.Right);
    	}
		this.setWidth("100%");	
		this.setMaxLength(format.editSize);
    	return rt;
     }	
	theClass.prototype.setValue = function (str) {
		BaseClass.prototype.setValue.call(this, str);
		
		var value = FormatUtil.fromString(this.metaCol, str);
		this.setProperty("dataValue", value);
		
	}
	theClass.prototype.setDataValue = function (value) {
		this.setProperty("dataValue", value);
		var str = FormatUtil.toString1(this.metaCol, value);
		BaseClass.prototype.setValue.call(this, str);		
	};	
	theClass.prototype.setEditable = function (value) {
		BaseClass.prototype.setEditable.call(this, value);		
	};	
	return theClass;
});