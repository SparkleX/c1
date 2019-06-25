sap.ui.define([
	"sap/m/Input",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"next/core/widget/CoreUtil"
],
function(Input, TextAlign,NumberFormat, CoreUtil) {
	"use strict";
	var theClass = Input.extend("next.core.widget.FmtInput", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
	theClass.prototype.init = function () {
		Input.prototype.init.call(this);
		this.setTextAlign(TextAlign.Right);
		//this.setTextFormatter(oFormat);
		this.setMaxLength(8);

	};
	theClass.prototype.formatValue = function (value) {
		var bind = this.getDataFormat();
		var oColumn = CoreUtil.getMdColumnByBind(bind);
		var decimalPlaces = CoreUtil.getDecimalPlaces(oColumn);

		var oFormat = NumberFormat.getFloatInstance({decimals: decimalPlaces});
		var formattedVal = null;
		if(value!=null) {
			formattedVal = oFormat.format(value);
		}
		
		return formattedVal;
	}
	theClass.prototype.setValue = function (value) {

		var formattedVal = this.formatValue(value);
		this.setProperty("dataValue", value);
		Input.prototype.setValue.call(this, formattedVal);
	}
	theClass.prototype.getValue = function () {
		return Input.prototype.getValue.call(this);
	};	
	theClass.prototype.setDataValue = function (value) {

		this.setValue(value);
		this.setProperty("dataValue", value);
	};	
	theClass.prototype.getDataValue = function () {
		return this.getValue();
	};	
	return theClass;
});