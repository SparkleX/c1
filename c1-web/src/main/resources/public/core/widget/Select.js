sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Select",
	"next/core/widget/CoreUtil"
], function (Control, BaseClass, CoreUtil) {
	"use strict";
	var theClass =  BaseClass.extend("next.core.widget.Select", {
		metadata : {
			properties : {
				dataValue:  {type: "string", group: "Behavior"},
				dataFormat:  {type: "string", group: "Behavior"},
			},
			events: {
				dataChange:{
					parameters: {
						selectedItem: {
							type: "sap.ui.core.Item"
						}
					}
				}
			}
		}
	});

	theClass.prototype.init = function () {
		BaseClass.prototype.init.call(this);


	};

	theClass.prototype.applySettings = function(mSettings, oScope) {
		BaseClass.prototype.applySettings.call(this, mSettings, oScope);
		this.attachChange(this.onChange);
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
		var rt = BaseClass.prototype.setValue.call(this, value);
		this.setProperty("dataValue", value);
		return rt;
	};
	
	theClass.prototype.setDataValue = function (value) {
		var rt = this.setProperty("dataValue", value);
		BaseClass.prototype.setValue.call(this, value);
		return rt;
	};
	theClass.prototype.onChange = function (evt) {
		var params = evt.getParameters()
		return this.fireDataChange(params);
	};	
	return theClass;
});