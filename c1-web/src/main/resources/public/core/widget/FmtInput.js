sap.ui.define([
	"sap/m/Input",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat"
],
function(Input, TextAlign,NumberFormat) {
	"use strict";
	var theClass = Input.extend("next.core.widget.FmtInput", { 
	metadata: {
		properties: {
			bind: { type: "string", group: "Misc", defaultValue: null },
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
		var oFormat = NumberFormat.getFloatInstance({decimals: 5});
		var val = oFormat.format(value);
		this.setProperty("data", value);
		Input.prototype.setValue.call(this, val);
	};	
	theClass.prototype.setData = function (value) {

		this.setValue(value);
	};	
	theClass.prototype.getData = function () {
		return this.getValue();
	};	
	return theClass;
});