sap.ui.define([
    "./CoreUtil",
    "sap/ui/core/format/NumberFormat",
    "next/core/controller/ServiceUtil",
],
function(CoreUtil,NumberFormat,ServiceUtil) {
	"use strict";

	var theClass={};
	theClass.format=function(dataFormat) {
		var rt = {right:false};
		var metaCol = CoreUtil.getMdColumnByBind(dataFormat);
		if(!metaCol.linkTo) {			
			switch(metaCol.dbType) {
			case "IDENTITY":
			case "INTEGER":
			case "DECIMAL":
				rt.right=true;
			}
		}
		rt.decimalPlaces = CoreUtil.getDecimalPlaces(metaCol);
		return rt;
	}
	theClass.formatValue=function(dataFormat, dataValue, fnCallback) {
		if(dataValue==null) {
			fnCallback(null);
			return ;
		}
		var metaCol = CoreUtil.getMdColumnByBind(dataFormat);
		
		switch(metaCol.dbType) {
		case "DECIMAL":
			var decimalPlaces = CoreUtil.getDecimalPlaces(metaCol);
			var oFormat = NumberFormat.getFloatInstance({decimals: decimalPlaces});
			var rt =oFormat.format(dataValue);
			fnCallback(rt);
			return;
		}
		
		if(metaCol.linkTo) {
			ServiceUtil.getDescription(metaCol.linkTo, dataValue, fnCallback);
			return ;
		}
		fnCallback(dataValue);
	}
	return theClass;
});


