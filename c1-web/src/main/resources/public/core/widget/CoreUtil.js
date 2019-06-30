sap.ui.define([
    "sap/ui/model/json/JSONModel",
    "sap/ui/thirdparty/jquery"
],
function(JSONModel, jQuery) {
	"use strict";

	var theClass={};
	theClass.metadata = null;
	theClass.oadm = null;
	theClass.decimals = {};
	theClass.init=function() {
	    this.initMetadata();
	    this.initOADM();
    }
	theClass.initMetadata=function() {
		jQuery.ajax({
            url: "/api/metadata/",
            async: false,
            success : function(data) {
                theClass.metadata = data;
				for(var table in theClass.metadata) {
					var oTable = theClass.metadata[table];
					oTable.columnMap = {};
					for(var colIndex in oTable.column) {
						var oColumn = oTable.column[colIndex];
						oTable.columnMap[oColumn.id] = oColumn;
					}
				}
            }
        }).fail(function (result,result1,result2) {
			alert('fail');
        })
	}
	theClass.initOADM=function() {
		jQuery.ajax({
            url: "/api/OADM/",
            async: false,
            success : function(data) {
                theClass.oadm = data[0];
                theClass.decimals.MEASURE = theClass.oadm.measureDec
                theClass.decimals.PERCENT = theClass.oadm.percentDec;
                theClass.decimals.QUANTITY = theClass.oadm.qtyDec;
                theClass.decimals.RATE = theClass.oadm.rateDec;
                theClass.decimals.SUM = theClass.oadm.sumDec;
            }
        }).fail(function (result,result1,result2) {
			alert('fail');
        })
	}

    theClass.getDataBindTable=function(bind){
        var str = bind.split(".")
        var table = str[0];
        return table;
    }
    theClass.getDataBindField=function(bind){
        var str = bind.split(".")
        var val = str[1];
        return val;
    }
	theClass.getDecimalPlaces = function(column) {
		if(column.editType==='NONE') {
			return null;
		}
	   	var rt = this.decimals[column.editType];
	   	return rt;
	}
    theClass.getMdColumnByBind=function(bind){
        var str = bind.split(".")
        var table = str[0];
        var column = str[1];
        return this.getMdColumn(table, column);
    }
	theClass.getMdColumn=function(table, column) {
	    var rt = this.metadata[table].columnMap[column];
	    if(rt===undefined) {
	    	alert(`${table}.${column} not exists`);
	    }
        return rt;
	}
	theClass.getMdTable=function(table) {
	    var rt = this.metadata[table];
        return rt;
	}
	return theClass;
});