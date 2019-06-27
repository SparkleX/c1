sap.ui.define([
	"sap/ui/table/Table"
], function (Table
	) {
	"use strict";
	var theClass =  Table.extend("next.core.ui.table.ListViewTable", {
		metadata : {
			properties : {
				dataTable:  {type: "string", group: "Behavior", defaultValue: null},
				allowAdd:  {type: "boolean", group: "Behavior", defaultValue: true},
				
			},
			aggregations : {
				defaultColumns: {type: "next.core.ui.table.DefaultColumn", singularName : "defaultColumn", multiple : true},
			}
		}
	});
    theClass.prototype.init = function() {
    	Table.prototype.init.call(this);
     }
    theClass.prototype.onBeforeRendering = function() {

    	
    	Table.prototype.onBeforeRendering.call(this);

    }

	return theClass;
});