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
			data: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
	theClass.prototype.init = function () {
		Input.prototype.init.call(this);
		this.setTextAlign(TextAlign.Right);
		//this.setTextFormatter(oFormat);
		this.setMaxLength(8);

	};
	theClass.prototype.setValue = function (value) {

		var bind = this.getDataFormat();
		var oColumn = CoreUtil.getMdColumnByBind(bind);
		var decimalPlaces = CoreUtil.getDecimalPlaces(oColumn);

		var oFormat = NumberFormat.getFloatInstance({decimals: decimalPlaces});
		var formattedVal = null;
		if(value!=null) {
			formattedVal = oFormat.format(value);
		}
		this.setProperty("data", value);
		Input.prototype.setValue.call(this, formattedVal);
	};	
	theClass.prototype.setData = function (value) {

		this.setValue(value);
	};	
	theClass.prototype.getData = function () {
		return this.getValue();
	};	
	return theClass;
});