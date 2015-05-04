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
sendSyncMessage = function(msg) {                                                                                                                                                  
  resultStr = extension.internal.sendSyncMessage(JSON.stringify(msg));

  //TODO: return null or undefined.
  return resultStr.lenght > 0 ? JSON.parse(resultStr) : undefined;
};
 
postMessage = function(msg) {                                                                                                                                                  
  extension.postMessage(JSON.stringify(msg));
};

MsgToNative = {
  "invokeMethod": 1,
  "getProperty": 2,
  "setProperty": 3,
  "newInstance": 4,
  "invokeCallback": 5,
  "destory": 6
};

JsStub.prototype = {
  "invokeNative": function(name, args) {
    postMessage({
      cmd: MsgToNative.invokeMethod,
      name: name,
      args: args
    });
  },
  "getNativeProperty": function(name) {
    return sendSyncMessage({
      cmd: MsgToNative.getProperty,
      name: name
    });
  },
  "setNativeProperty": function(name, value) {
    postMessage({
      cmd: MsgToNative.setProperty,
      name: name,
      value: value
    });
  },
  "newInstance": function(args) {
    postMsg({
      cmd: MsgToNative.newInstance,
      value: value
    });
  },
  "invokeCallback": function() {},
  "destory": function() {}
};
 
exports.setMessageListener(function(json){
   var msg = JSON.parse(json);
   if (msg.error) {
     return;
   }
   switch (msg.cmd) {
     case "invokeMethod":
       break;
     case "getProperty_ret":
       break;
     case "setProperty_ret":
       break;
     case "newInstance_ret":
       break;
     default:
   }
 });
 
 exports.defineProperty = function defineProperty(obj, prop, value, isWritable) {
 if (!isWritable) {
   Object.defineProperty(object, prop, {
    "configurable": false,
    "enumerable": true,
    "writable": false,
    "value": value
  });
  } else {
    Object.defineProperty(object, prop, {
    "configurable": false,
    "enumerable": true,
    "get": function() {return getNativePropety(prop);},
    "set": function() {setNativeProperty(prop, value);};
    })
  }
 }
 
 exports.defineReadOnlyProperty = function(object, key, value) {
  Object.defineProperty(object, key, {
    configurable: false,
    writable: false,
    value: value
  }); 
 };
