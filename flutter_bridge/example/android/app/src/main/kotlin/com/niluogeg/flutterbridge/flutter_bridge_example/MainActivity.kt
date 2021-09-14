package com.niluogeg.flutterbridge.flutter_bridge_example

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridge
import com.niluogeg.flutterbridge.flutter_bridge.MethodHandler
import io.flutter.embedding.android.FlutterActivity


class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FlutterBridge.instance.registerHandler("getSDKVersion",object : MethodHandler {
            override fun onMethodCall(params: Map<String, Any?>): String {
                return Build.VERSION.SDK_INT.toString()
            }
        })


        FlutterBridge.instance.registerHandler("startEnterActivity",object : MethodHandler {
            override fun onMethodCall(params: Map<String, Any?>): String {
                val pageName = (params.get("pageName") as String?) ?: ""
                var intent = Intent(applicationContext, Class.forName(pageName))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                applicationContext.startActivity(intent)
                return "native 收到 flutter 的调用"
            }
        })

    }


}
