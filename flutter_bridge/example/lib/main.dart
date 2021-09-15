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
    // FlutterBridge.instance.registerHandlerHaveReturn("getFlutterVersion", (params) {
    //   return _getVersion();
    // });
    //
    // FlutterBridge.instance.registerHandlerNoReturn("callFlutterNoReturn", (params) {
    //   print('callFlutterNoReturn flutter 收到 原生的调用 params${params.toString()}');
    // });
  }

  _getVersion() {
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
                // var result = await FlutterBridge.instance.callNativeHaveReturn("startEnterActivity",
                //     params: {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
                // print('flutter 调用原生 返回值 cpResult=$result');

                FlutterBridge.instance.getChannel().invokeMethod("startEnterActivity",{"aa" : "bb"});

              },
              child: Text("startEnterActivity-带入参"),
            ),
            MaterialButton(
              onPressed: () async {
                // var result = await FlutterBridge.instance.callNativeHaveReturn("getSDKVersion");
                // print('sdk version =$result');
              },
              child: Text("getSDKVersion-不带入参"),
            ),
            MaterialButton(
              onPressed: () {
                // FlutterBridge.instance.callNativeNoReturn("callNativeNoReturn");
              },
              child: Text("调用没用返回值的方法"),
            ),

            MaterialButton(
              onPressed: () async {
                // var result = await FlutterBridge.instance.callNativeHaveReturn("requestHttp");
                // print('requestHttp resutl =$result');
              },
              child: Text("requestHttp"),
            ),
          ],
        ),
      ),
    );
  }
}
