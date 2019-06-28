sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Select",
	"next/core/widget/CoreUtil"
], function (Control, Select, CoreUtil) {
	"use strict";
	var theClass =  Select.extend("next.core.widget.Select", {
		metadata : {
			properties : {
				dataFormat:  {type: "string", group: "Behavior"}
			}
		}
	});

	theClass.prototype.init = function () {
		Select.prototype.init.call(this);


	};

    theClass.prototype.onBeforeRendering = function() {
        Select.prototype.onBeforeRendering.call(this);
        this.removeAllItems();
		var bind = this.getDataFormat();
		var oColumn = CoreUtil.getMdColumnByBind(bind);
		for(var v in oColumn.validValue)	{
			var oValidValue = oColumn.validValue[v];
			var item =  new sap.ui.core.ListItem({key:oValidValue.id,text:oValidValue.value});
			this.addItem(item);
		}
    }
	/*theClass.prototype.getDataFormat = function () {
		return this.getProperty("dataFormat");
	};*/

	return theClass;
});