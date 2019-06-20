sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Select",
	"next/core/widget/CoreUtil"
], function (Control, Select, CoreUtil) {
	"use strict";
	var theClass =  Select.extend("next.core.widget.Select", {
		metadata : {
			properties : {
				bind:  {type: "string", group: "Behavior",  defaultValue:null}
			}
		}
	});

	theClass.prototype.init = function () {
		Select.prototype.init.call(this);

		var column = this.getBind();
		var metaCol = CoreUtil.getMetadata(column);
		for(var v in metaCol.validValues)	{
			var oValidValue = metaCol.validValues[v];
			var item =  new sap.ui.core.ListItem({key:oValidValue.value,text:oValidValue.desc});
			this.addItem(item);
		}
	};

	theClass.prototype.getBind = function () {
		return this.getProperty("bind");
	};

	return theClass;
});