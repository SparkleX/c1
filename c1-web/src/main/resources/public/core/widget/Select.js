sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Select",
	"next/core/widget/CoreUtil"
], function (Control, Select, CoreUtil) {
	"use strict";
	var theClass =  Select.extend("next.core.widget.Select", {
		metadata : {
			properties : {
				dataValue:  {type: "string", group: "Behavior"},
				dataFormat:  {type: "string", group: "Behavior"},
			}
		}
	});

	theClass.prototype.init = function () {
		Select.prototype.init.call(this);


	};

	theClass.prototype.applySettings = function(mSettings, oScope) {
        Select.prototype.applySettings.call(this, mSettings, oScope);
        this.removeAllItems();
		var bind = this.getDataFormat();
		var oColumn = CoreUtil.getMdColumnByBind(bind);
		for(var v in oColumn.validValue)	{
			var oValidValue = oColumn.validValue[v];
			var item =  new sap.ui.core.ListItem({key:oValidValue.id,text:oValidValue.value});
			this.addItem(item);
		}
    }
	theClass.prototype.setValue = function (value) {
		var rt = Select.prototype.setValue.call(this, value);
		this.setProperty("dataValue", value);
		return rt;
	};
	
	theClass.prototype.setDataValue = function (value) {
		var rt = this.setProperty("dataValue", value);
		Select.prototype.setValue.call(this, value);
		return rt;
	};

	return theClass;
});