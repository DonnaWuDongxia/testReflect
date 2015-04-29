/*
Interfaces:
invokeNative
invokeCallback
defineProperty
*/

//This will be a native module, extension js stub can requireNative("JsStub") to get all the interfaces

/*
 * This is the js wrapper
 * Should be used as
 * requireNative("JsStub")
 */
 
 JsStub = function () {
 }
 JsStub.create = function(namespace, base, isConstructor) {
 
 /*TODO: do we need namespace?, c++ created the related object*/
 
 }
 
 JsStub.prototype = {
 "invokeNative": function(fName) {
   msg = {};
   msg
   extension.postMessage(msg);
  },
  "invokeCallback": function() {},
  "destory": function() {}
 };
 
 exports.defineProperty = function(obj, prop, value, isReadOnly) {
   var desc = {
     "configurable": false,
     "enumerable": true,
     "get": function() { return this.properties[prop]}
   };
   if(!isReadOnly) {
     desc.set = function(v) {
       this.invokeNative('.' + prop, v);
       this.properties[prop] = v;
     }
   }
   if (!obj.properties) obj.properties = {};
   obj.properties[prop] = value;
   Object.defineProperty(obj, prop, desc);
 }
 
 exports.defineReadOnlyProperty = function(object, key, value) {
  Object.defineProperty(object, key, {
    configurable: false,
    writable: false,
    value: value
  }); 
 };
 
 
 exports.setMessageListener(function(json){
   var msg = JSON.parse(json);
   if (msg.error) {
     return;
   }
   switch (msg.cmd) {
     case "":
       break;
     default:
   }
 });