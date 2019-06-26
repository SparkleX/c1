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
        jQuery.ajax({
            url: `/api/${table}/${id}`,
            async: false,
            method : 'put',
            data : data,
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

	return theClass;
});