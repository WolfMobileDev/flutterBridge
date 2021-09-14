import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_bridge/bridget_flutter_router_api.dart';
import 'package:flutter_bridge/flutter_bridge.dart';
import 'package:flutter_bridge/messages.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    _registMethod();

    super.initState();
  }


  _registMethod(){
    FlutterBridge.instance.registerHandler("nativeCallFlutter",(params)  {
      print('flutter 收到 原生 的调用 params${params.toString()}');
      return"flutter 收到 native 的调用";
    });

    FlutterBridge.instance.registerHandler("nativeCallFlutter2",(params)  {
      print('flutter 收到 原生的调用 params${params.toString()}');
      return "flutter 收到 native 的调用";
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            MaterialButton(
              onPressed: () async {
                var result = await FlutterBridge.instance.callNative("flutterCallNative",
                    {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
                print('flutter 调用原生 返回值 cpResult=$result');
              },
              child: Text("通过 flutter_bridge 调用方法 flutterCallNative"),
            ),
            MaterialButton(
              onPressed: () async {
                var result = await FlutterBridge.instance.callNative("startEnterActivity", {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
                print('flutter 调用原生 返回值 cpResult=$result');
              },
              child: Text("startEnterActivity"),
            ),
          ],
        ),
      ),
    );
  }
}
