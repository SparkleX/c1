sap.ui.define([
	"sap/m/Link",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"next/core/widget/CoreUtil",
	"./FormatUtil"
],
function(BaseClass, TextAlign,NumberFormat, CoreUtil, FormatUtil) {
	"use strict";
	var theClass = BaseClass.extend("next.core.widget.FormatLink", { 
	metadata: {
		properties: {
			dataFormat: { type: "string", group: "Misc", defaultValue: null },
			dataValue: { type: "string", group: "Misc", defaultValue: null }
		}
	}});
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = BaseClass.prototype.applySettings.call(this, mSettings, oScope);
    	
    	var dataFormat = this.getDataFormat(); 
    	var format = FormatUtil.format(dataFormat);
    	if(format.right){
    		this.setTextAlign(TextAlign.Right);
    	}
		//this.setWidth("100%");
		this.attachPress(this.onPress);
    	return rt;
     }	
	theClass.prototype.formatValue = function (value) {
		var dataFormat = this.getDataFormat(); 
		return FormatUtil.formatValue(dataFormat, value);
	}
	theClass.prototype.setText = function (value) {
		var formattedVal = this.formatValue(value);
		this.setProperty("dataValue", value);
		BaseClass.prototype.setText.call(this, formattedVal);
	}
	theClass.prototype.getText = function () {
		return BaseClass.prototype.getText.call(this);
	}	
	theClass.prototype.setDataValue = function (value) {

		this.setText(value);
		this.setProperty("dataValue", value);
	}	
	theClass.prototype.getDataValue = function () {
		return this.getText();
	}	

    theClass.prototype.openQuickView= function (oEvent, oModel) {
		this.createPopover();

		this._oQuickView.setModel(oModel);

		// delay because addDependent will do a async rerendering and the actionSheet will immediately close without it.
		var oButton = oEvent.getSource();
		jQuery.sap.delayedCall(0, this, function () {
			this._oQuickView.openBy(oButton);
		});
	};

	theClass.prototype.onPress= function (evt) {
		this.openQuickView(evt, null);
	};

	theClass.prototype.createPopover= function() {

		if (!this._oQuickView) {
			this._oQuickView = sap.ui.xmlfragment("next.share.quick.OCRD", this);
			//this.getView().addDependent(this._oQuickView);
		}
	};	
	return theClass;
});