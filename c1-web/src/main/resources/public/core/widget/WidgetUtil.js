sap.ui.define([
],
function(JSONModel, jQuery) {
	"use strict";

	var theClass={};
	theClass.getRootView=function(widget) {
		var parent = widget;
		for(;;) {
			var parent = parent.getParent() 
			if(parent instanceof sap.ui.core.mvc.View) {
				return parent;
			}	
		}
	}
	
	theClass.scan=function(view, applyFunction) {
		console.debug(view.getId());
		if(view.getId()==="__input0"){
			console.debug(view.getId());	
		}
		applyFunction(view);

		if(view instanceof sap.uxap.BlockBase) {
			var content = view._getSelectedViewContent()
			this.scan(content, applyFunction);
			return;
		}
		if(view instanceof sap.ui.layout.form.SimpleForm) {
			var content = view.getContent()
			for(let formContent of content ) {
				this.scan(formContent, applyFunction);
			}
			return;
		}		
		var aggrs = view.getMetadata().getAllAggregations();
		for(name in aggrs ) {
			var  c = view.getAggregation(name);
			if(c==null) {
				continue;
			}
			if(c.length==0) {
				continue;
			}
			if(Array.isArray(c)) {
				for(let o of c) {
					this.scan(o, applyFunction);
				}
			}
			else {
				this.scan(c, applyFunction);
			}
		}		
	}
	theClass.editableAddMode=function(o) {
		var value = true;
		if(o.getEditableAddMode) {
			value = o.getEditableAddMode();
		}
		if(o.setEditable) {
			o.setEditable(value);
		}
	}	
	theClass.editableEditMode=function(o) {
		var value = true;
		if(o.getEditableEditMode) {
			value = o.getEditableEditMode();
		}
		if(o.setEditable) {
			o.setEditable(value);
		}
	}	
	theClass.editableFalse=function(o) {
		console.debug(o);
		if(o.setEditable) {
			o.setEditable(false);
		}
	}
	theClass.editableTrue=function(o) {
		console.debug(o);
		if(o.setEditable) {
			o.setEditable(true);
		}
	}
	return theClass;
});