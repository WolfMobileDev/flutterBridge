import 'package:flutter/material.dart';
import 'package:flutter_bridge/flutter_bridge.dart';

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

  _registMethod() {
    FlutterBridge.instance.registerHandler("getFlutterVersion", (params) {
      return _getVersion();
    });

    FlutterBridge.instance.registerHandler("nativeCallFlutter2", (params) {
      print('flutter 收到 原生的调用 params${params.toString()}');
      return "flutter 收到 native 的调用";
    });
  }

  _getVersion(){
    return "1.0.0";
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
                var result = await FlutterBridge.instance.callNative("startEnterActivity",
                    params: {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
                print('flutter 调用原生 返回值 cpResult=$result');
              },
              child: Text("startEnterActivity-带入参"),
            ),
            MaterialButton(
              onPressed: () async {
                var result = await FlutterBridge.instance.callNative("getSDKVersion");
                print('sdk version =$result');
              },
              child: Text("getSDKVersion-不带入参"),
            ),
          ],
        ),
      ),
    );
  }
}
