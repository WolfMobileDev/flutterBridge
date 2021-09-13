import 'dart:collection';
import 'dart:ffi';

import 'package:pigeon/pigeon.dart';

class CallInfo {
  String methodName;
  Map<String, Object> params;
}

class ResultInfo {
  String result;
}

class StackInfo {
  List<String> containers;
  Map<String, List<Map<String, Object>>> routes;
}

@HostApi()
abstract class NativeRouterApi {
  ResultInfo send(CallInfo callInfo);

  ResultInfo registerHandler(CallInfo callInfo);

  ResultInfo pushNativeRoute(CallInfo param);

  void saveStackToHost(StackInfo stack);
}

@FlutterApi()
abstract class FlutterRouterApi {
  ResultInfo send(CallInfo callInfo);

  ResultInfo registerHandler(CallInfo callInfo);


  ResultInfo pushRoute(CallInfo param);

  void popRoute(CallInfo param);
}

//D:\soft\flutter\flutter_2.0.3\flutter\bin\flutter pub run pigeon --input pigeon/messages.dart
void configurePigeon(PigeonOptions opts) {
  opts.dartOut = 'lib/messages.dart';
  opts.objcHeaderOut = 'ios/Classes/messages.h';
  opts.objcSourceOut = 'ios/Classes/messages.m';
  opts.objcOptions.prefix = 'FB';
  opts.javaOut = 'android/src/main/kotlin/com/niluogeg/flutterbridge/flutter_bridge/Message.java';
  opts.javaOptions.package = "com.niluogeg.flutterbridge.flutter_bridge";
}
