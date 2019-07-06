sap.ui.define([
	"sap/ui/table/Table",
	"next/core/widget/WidgetUtil",
	"next/core/widget/FormatText",
	"next/core/widget/FormatLink",
	"next/core/widget/CoreUtil",
	"next/core/controller/ApiUtils",
], function (Table, WidgetUtil, FormatText, FormatLink, CoreUtil,ApiUtils) {
	"use strict";
	var theClass =  Table.extend("next.core.ui.table.ListViewTable", {
		metadata : {
			properties : {
				dataTable:  {type: "string", group: "Behavior", defaultValue: null},
				allowAdd:  {type: "boolean", group: "Behavior", defaultValue: true},
				
			}
		}
	});
    theClass.prototype.init = function() {
    	Table.prototype.init.call(this);
     }
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = Table.prototype.applySettings.call(this, mSettings, oScope);
    	var table = this.getDataTable();
    	var metaTable = CoreUtil.getMdTable(table);
    	var listColumns = ApiUtils.listView(table);

    	for(let col of listColumns.column){
    		var metaCol = metaTable.columnMap[col];
    		var text = metaCol.description;
    		var template = null;
    		if(metaCol.linkTo) {
	    		template = new FormatLink({
		    			dataValue:"{list>"+metaCol.id+"}",
		    			dataFormat:this.getDataTable()+"."+metaCol.id
		    			});
    		}else {
        		template = new FormatText({
	    			dataValue:"{list>"+metaCol.id+"}",
	    			dataFormat:this.getDataTable()+"."+metaCol.id
	    			});
    		}
    		
       	 	this.addColumn(new sap.ui.table.Column({
       		    label: new sap.m.Label({text: text}),
       		    template: template
       		    }
       		  ));    		
    	}    	
    	var oActionItem = new sap.ui.table.RowActionItem({type:"Navigation", press:this.onPress});
    	var oAction = new sap.ui.table.RowAction({items:[oActionItem]})
    	this.setRowActionTemplate(oAction);
  	
    	return rt;
     }     
    /*theClass.prototype.onBeforeRendering = function() {
    	Table.prototype.onBeforeRendering.call(this);
    }*/
    theClass.prototype.onPress = function(evt) {
    	var view = WidgetUtil.getRootView(this);
    	view.getController().onListItemPress(evt);
    }

	return theClass;
});