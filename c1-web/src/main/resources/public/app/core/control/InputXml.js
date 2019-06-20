sap.ui.define([
    'sap/ui/core/XMLComposite'], 
    function( XMLComposite ) {
    "use strict";
    var InputXml = XMLComposite.extend("app.core.control.InputXml", {
        metadata: {
            properties: {
                text: { type: "string", defaultValue: "Default Text"}
            }
        }
    });
    return InputXml;
}, /* bExport= */true);