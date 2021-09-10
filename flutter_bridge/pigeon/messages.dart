import 'dart:collection';

import 'package:pigeon/pigeon.dart';

class CommonParams {
  String pageName;
  String uniqueId;
  Map<String, Object> arguments;
  bool opaque;
  String key;
}

class EventParam{

}

class StackInfo {
  List<String> containers;
  Map<String, List<Map<String, Object>>> routes;
}

@HostApi()
abstract class NativeRouterApi {
  void pushNativeRoute(CommonParams param);
  void saveStackToHost(StackInfo stack);
}

@FlutterApi()
abstract class FlutterRouterApi {
  void pushRoute(CommonParams param);
  void popRoute(CommonParams param);
}



//D:\soft\flutter\flutter_2.0.3\flutter\bin\flutter pub run pigeon --input pigeon/messages.dart
void configurePigeon(PigeonOptions opts) {
  opts.dartOut = 'lib/messages.dart';
  opts.objcHeaderOut = 'ios/Classes/messages.h';
  opts.objcSourceOut = 'ios/Classes/messages.m';
  opts.objcOptions.prefix = 'FB';
  opts.javaOut =
      'android/src/main/kotlin/com/niluogeg/flutterbridge/flutter_bridge/Message.java';
  opts.javaOptions.package="com.niluogeg.flutterbridge.flutter_bridge";
}
