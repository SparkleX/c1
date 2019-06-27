sap.ui.define([
    "sap/ui/thirdparty/jquery"
], function (jquery) {
	"use strict";
	var theClass = {}

	theClass.create = function (table, data) {
        jQuery.ajax({
            url: `/api/${table}/`,
            async: false,
            method : 'post',
            data : data,
            success : function(data) {
            }
        });
	}
	theClass.update = function (table, id, data) {
	    var json = JSON.stringify(data)
        jQuery.ajax({
            url: `/api/${table}/${id}`,
            async: false,
            method : 'put',
            contentType :'application/json',
            data : json,
            success : function(data) {

            }
        });
	}
	theClass.delete = function (table, id) {
        jQuery.ajax({
            url: `/api/${table}/${id}`,
            async: false,
            method : 'delete',
            success : function(data) {

            }
        });
	}
	theClass.getNext = function (table, id) {
		var rt;
        jQuery.ajax({
            url: `/api/${table}/${id}/.next`,
            async: false,
            method : 'get',
            success : function(data) {
            	rt = data;
            }
        });
        return rt;
	}
	theClass.getPrev = function (table, id) {
		var rt;
        jQuery.ajax({
            url: `/api/${table}/${id}/.prev`,
            async: false,
            method : 'get',
            success : function(data) {
            	rt = data;
            }
        });
        return rt;
	}	
	return theClass;
});