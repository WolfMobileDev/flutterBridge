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
    //初始化 NativeRouterApi (flutter 调用原生)
    NativeRouterApi();
    // 初始化 BoostFlutterRouterApi (原生调用flutter)
    BridgetFlutterRouterApi();
    super.initState();
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
              onPressed: () {
                CommonParams cp = CommonParams();
                cp.pageName = "hhhh";
                NativeRouterApi().pushNativeRoute(cp);
              },
              child: Text("通过 flutter_bridge 调用方法"),
            ),
          ],
        ),
      ),
    );
  }
}
