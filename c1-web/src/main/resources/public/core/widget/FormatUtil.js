sap.ui.define([
    "./CoreUtil",
    "sap/ui/core/format/NumberFormat",
],
function(CoreUtil,NumberFormat) {
	"use strict";

	var theClass={};
	theClass.format=function(dataFormat) {
		var rt = {right:false};
		var metaCol = CoreUtil.getMdColumnByBind(dataFormat);
		switch(metaCol.dbType) {
		case "IDENTITY":
		case "INTEGER":
		case "DECIMAL":
			rt.right=true;
		}
		rt.decimalPlaces = CoreUtil.getDecimalPlaces(metaCol);
		return rt;
	}
	theClass.formatValue=function(dataFormat, dataValue) {
		if(dataValue==null) {
			return null;
		}
		var metaCol = CoreUtil.getMdColumnByBind(dataFormat);
		switch(metaCol.dbType) {
		case "DECIMAL":
			var decimalPlaces = CoreUtil.getDecimalPlaces(metaCol);
			var oFormat = NumberFormat.getFloatInstance({decimals: decimalPlaces});
			return oFormat.format(dataValue);
		}
		return dataValue;
	}
	return theClass;
});


