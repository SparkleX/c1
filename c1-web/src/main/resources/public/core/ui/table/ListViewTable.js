sap.ui.define([
	"sap/ui/table/Table",
	"next/core/widget/WidgetUtil",
	"next/core/widget/FormatText"	
], function (Table, WidgetUtil, FormatText) {
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
    theClass.prototype.applySettings = function(mSettings, oScope) {
    	var rt = Table.prototype.applySettings.call(this, mSettings, oScope);
    	for(let col of this.getDefaultColumns()){
    		var dataBind = col.getDataBind();
    		var text = col.getText();
       	 	this.addColumn(new sap.ui.table.Column({
       		    label: new sap.m.Label({text: text}),
       		    template: new FormatText({
       		    			dataValue:"{list>"+dataBind+"}",
       		    			dataFormat:this.getDataTable()+"."+dataBind
       		    			})
       		  }));    		
    	}    	
    	var oActionItem = new sap.ui.table.RowActionItem({type:"Navigation", press:this.onPress});
    	var oAction = new sap.ui.table.RowAction({items:[oActionItem]})
    	this.setRowActionTemplate(oAction);
  	
    	return rt;
     }     
    theClass.prototype.onBeforeRendering = function() {
    	Table.prototype.onBeforeRendering.call(this);
    }
    theClass.prototype.onPress = function(evt) {
    	var view = WidgetUtil.getRootView(this);
    	view.getController().onListItemPress(evt);
    }

	return theClass;
});