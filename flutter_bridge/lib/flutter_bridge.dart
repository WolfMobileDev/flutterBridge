import 'dart:async';

import 'package:flutter/services.dart';

typedef MethodHandlerHaveReturn = String Function(Map<String, dynamic> params);
typedef MethodHandlerNoReturn = void Function(Map<String, dynamic> params);

class FlutterBridge{
  static const String CHANNEL_NAME = "flutterBridge/core";
  MethodChannel _channel = new MethodChannel(CHANNEL_NAME);


  // @override
  // ResultInfo callFlutter(CallInfo callInfo) {
  //   String methodName = callInfo.methodName;
  //   Map<String, dynamic> params = Map<String, dynamic>.from(callInfo.params);
  //   dynamic methodHandler = _methodMap[methodName];
  //   if (methodHandler is MethodHandlerHaveReturn) {
  //     String result = methodHandler(params);
  //     ResultInfo ri = ResultInfo();
  //     ri.result = result;
  //     return ri;
  //   } else if (methodHandler is MethodHandlerNoReturn) {
  //     ResultInfo ri = ResultInfo();
  //     return ri;
  //   }
  // }

  FlutterBridge._(){
    _channel.setMethodCallHandler(onMethodCall);
  }

  static final FlutterBridge _instance = FlutterBridge._();

  static FlutterBridge get instance {
    return _instance;
  }

  MethodChannel getChannel(){
    return _channel;
  }

  var _methodMap = Map<String, dynamic>();

  // /// 注册方法
  // void registerHandlerHaveReturn(String methodName, MethodHandlerHaveReturn methodHandle) {
  //   _methodMap[methodName] = methodHandle;
  // }
  //
  // void registerHandlerNoReturn(String methodName, MethodHandlerNoReturn methodHandle) {
  //   _methodMap[methodName] = methodHandle;
  // }
  //
  // Future<String> callNativeHaveReturn(String methodName, {Map<String, Object> params}) async {
  //   CallInfo cp = CallInfo();
  //   cp.methodName = methodName;
  //   cp.params = params;
  //   ResultInfo ri = await NativeRouterApi().callNative(cp);
  //   return ri.result;
  // }
  //
  // callNativeNoReturn(String methodName, {Map<String, Object> params}) async {
  //   CallInfo cp = CallInfo();
  //   cp.methodName = methodName;
  //   cp.params = params;
  //   await NativeRouterApi().callNative(cp);
  // }

  Future<dynamic> onMethodCall(MethodCall call){
    print('method=${call.method} arguments=${call.arguments}');
    return Future.value("");
  }
}
