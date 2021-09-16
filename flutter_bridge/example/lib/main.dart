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
    FlutterBridge.instance.openLog(true);
    _registMethod();

    super.initState();
  }

  _registMethod() {
    FlutterBridge.instance.registerHandler("getFlutterVersion", (params) {
      return _getVersion();
    });

    FlutterBridge.instance.registerHandler("getFlutterMap", (params) {
      return {"aa": "bb", "cc": "dd"};
    });

    FlutterBridge.instance.registerHandler("callFlutterNoReturn", (params) {
      print('callFlutterNoReturn flutter 收到 原生的调用 params${params.toString()}');
    });
  }

  _getVersion() {
    return "1.0.0";
  }

  String result = "";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('result=$result'),
        ),
        body: Column(
          children: [
            MaterialButton(
              onPressed: () async {
                await FlutterBridge.instance.callNative<Map>("startEnterActivity",
                    params: {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
              },
              child: Text("打开原生页面"),
            ),
            MaterialButton(
              onPressed: () async {
                var map = await FlutterBridge.instance.callNative<Map>("callNativeReturnMap",
                    params: {"pageName": "com.niluogeg.flutterbridge.flutter_bridge_example.EnterActivity"});
                result = map.toString();
                setState(() {});
              },
              child: Text("调用原生方法-带入参-返回值是map"),
            ),
            MaterialButton(
              onPressed: () async {
                String str = await FlutterBridge.instance.callNative<String>("getSDKVersion");
                result = str;
                setState(() {});
              },
              child: Text("调用原生方法-不带入参-返回值是String"),
            ),
            MaterialButton(
              onPressed: () {
                FlutterBridge.instance.callNative("callNativeNoReturn");
                result = "没有返回值";
                setState(() {});
              },
              child: Text("调用原生方法-没有返回值"),
            ),
            MaterialButton(
              onPressed: () async {
                result = await FlutterBridge.instance.getChannel().invokeMethod("requestHttp");
                setState(() {});
              },
              child: Text("调用原生方法-异步"),
            ),
          ],
        ),
      ),
    );
  }
}
