import 'dart:async';

import 'package:flutter/services.dart';

import 'messages.dart';

abstract class MethodHandle {
  String onMethodCall(Map<String, Object> params);
}

class FlutterBridge {
  FlutterBridge._();

  static final FlutterBridge _instance = FlutterBridge._();

  static FlutterBridge get instance {
    return _instance;
  }

  var _methodMap = Map<String, MethodHandle>();

  /// 注册方法
  void registerHandler(String methodName, MethodHandle methodHandle) {
    _methodMap[methodName] = methodHandle;
  }

  Future<String> call(String methodName, Map<String, Object> params) async {
    CallInfo cp = CallInfo();
    cp.methodName = methodName;
    cp.params = params;
    ResultInfo ri = await NativeRouterApi().call(cp);
    return ri.result;
  }
}
