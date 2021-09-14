import 'dart:async';

import 'package:flutter/services.dart';

import 'messages.dart';

typedef MethodHandler = String Function(Map<dynamic, dynamic> params);

class FlutterBridge extends FlutterRouterApi {
  FlutterBridge._();

  static final FlutterBridge _instance = FlutterBridge._();

  static FlutterBridge get instance {
    FlutterRouterApi.setup(_instance);
    return _instance;
  }


  var _methodMap = Map<String, MethodHandler>();

  /// 注册方法
  void registerHandler(String methodName, MethodHandler methodHandle) {
    _methodMap[methodName] = methodHandle;
  }

  Future<String> callNative(String methodName, Map<String, Object> params) async {
    CallInfo cp = CallInfo();
    cp.methodName = methodName;
    cp.params = params;
    ResultInfo ri = await NativeRouterApi().callNative(cp);
    return ri.result;
  }

  @override
  ResultInfo callFlutter(CallInfo callInfo)  {
    String methodName = callInfo.methodName;
    Map<dynamic, dynamic> params = callInfo.params;
    MethodHandler methodHandler = _methodMap[methodName];
    String result =  methodHandler(params);
    ResultInfo ri = ResultInfo();
    ri.result = result;
    return ri;
  }
}
