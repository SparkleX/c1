sap.ui.define([
	"sap/ui/core/Control",
	"sap/m/Input",
	"sap/ui/model/type/Boolean"
], function (Control, Input, TypeBoolean) {
	"use strict";
	var CrudInput =  Input.extend("app.core.control.Input", {
		metadata : {
			properties : {
				//editable:  {type: "boolean", group: "Behavior",  defaultValue:"{= ${form>/formMode} ==='editMode'}"}
			}
		}
	});

	CrudInput.prototype.init = function () {
		//this.metadata.properties.editable = {type: "boolean", group: "Behavior",  defaultValue:"{= ${form>/formMode} ==='editMode'}"};
		Input.prototype.init.call(this);
		//this.editable = "{= ${form>/formMode} ==='editMode'}";
	//	var aa = this.getPropertyDefaults();
		/*var editable = this.getProperty("editable");
		if(editable==null){
			this.bindProperty("editable", {
				path: "{= ${form>/formMode} ==='editMode'}",
				type: new TypeBoolean()
			});
			//this.bindProperty("editable", "= ${form>/formMode} ==='editMode'");
		}*/
	};/*,
		setEditable : function(sValue){
			this.setProperty("editable", sValue, true);
		}*/

	return CrudInput;
});