package com.niluogeg.flutterbridge.flutter_bridge_example

import android.os.Bundle
import android.util.Log
import android.view.View
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridge
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridgePlugin
import com.niluogeg.flutterbridge.flutter_bridge.Message
import com.niluogeg.flutterbridge.flutter_bridge.MethodHandle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.plugins.FlutterPlugin

import io.flutter.embedding.engine.FlutterEngine


class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FlutterBridge.instance.registerHandler("flutterCallNative",object : MethodHandle {
            override fun onMethodCall(params: Map<String, Any?>): String {
                Log.e("flutterCallNative", "native 收到 flutter 的调用 params${params.toString()}")
                return "native 收到 flutter 的调用"
            }
        })


        FlutterBridge.instance.registerHandler("flutterCallNative2",object : MethodHandle {
            override fun onMethodCall(params: Map<String, Any?>): String {
                Log.e("flutterCallNative2", "native 收到 flutter 的调用 params${params.toString()}")
                return "native 收到 flutter 的调用"
            }
        })

    }


}
