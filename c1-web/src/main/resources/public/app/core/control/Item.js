sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Label",
	"sap/m/Input"	
], function (Control, Label, Input) {
	"use strict";
	return Control.extend("app.core.control.Item", {
		metadata : {
			properties : {
				b1bind: 	{type : "string", defaultValue : null},
				text: 	{type : "string", defaultValue : null},
				value: 	{type : "string", defaultValue : null}
			},
			aggregations: {
				"_label" : {type : "sap.m.Label", multiple : false, visibility: "hidden"},
				"_input" : {type : "sap.m.Input", multiple : false, visibility: "hidden"}
			   
			}
		},
		init : function () {
			var that = this;
			this.setAggregation("_label", new sap.m.Label({}));
			this.setAggregation("_input", new sap.m.Input({}));
		},
		exit : function() {
		},
		setValue : function(sValue){
			this.setProperty("value", sValue, true);
			this.getAggregation("_input").setValue(sValue);
		},
		setText : function(sValue){
			this.setProperty("text", sValue, true);
			this.getAggregation("_label").setText(sValue);
		}
	});
});