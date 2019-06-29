sap.ui.define([
	"sap/m/Link",
	"sap/ui/core/TextAlign",
	"sap/ui/core/format/NumberFormat",
	"next/core/widget/CoreUtil",
	"./FormatUtil",
	"sap/ui/model/json/JSONModel",
],
function(BaseClass, TextAlign,NumberFormat, CoreUtil, FormatUtil, JSONModel) {
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
    	this.metaCol = CoreUtil.getMdColumnByBind(dataFormat);
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
		//this.setProperty("dataValue", value);
		BaseClass.prototype.setText.call(this, formattedVal);
	}
	theClass.prototype.getText = function () {
		return BaseClass.prototype.getText.call(this);
	}	
	theClass.prototype.setDataValue = function (value) {

		this.setText(value);
		this.setProperty("dataValue", value);
	}	


	theClass.prototype.onPress= function (evt) {
		var table = this.metaCol.linkTo;
		if (!this._oQuickView) {
			
			this._oQuickView = sap.ui.xmlfragment("next.share.quick."+table, this);
			//this.getView().addDependent(this._oQuickView);
		}
		var oModel = new JSONModel();
		var url ="/api/"+table+"/"+this.getDataValue();
		oModel.loadData(url,null,false)
		this._oQuickView.setModel(oModel);

		var oButton = evt.getSource();
		jQuery.sap.delayedCall(0, this, function () {
			this._oQuickView.openBy(oButton);
		});

	}	

	return theClass;
});