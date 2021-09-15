package com.niluogeg.flutterbridge.flutter_bridge_example

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridge
import com.niluogeg.flutterbridge.flutter_bridge.MethodHandler
import com.niluogeg.flutterbridge.flutter_bridge.MethodHandlerHaveReturn
import com.niluogeg.flutterbridge.flutter_bridge.MethodHandlerNoReturn
import io.flutter.embedding.android.FlutterActivity


class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FlutterBridge.instance.registerHandler("getSDKVersion",object : MethodHandlerHaveReturn {
            override fun onMethodCall(params: Map<String, Any?>): String {
                return Build.VERSION.SDK_INT.toString()
            }
        })


        FlutterBridge.instance.registerHandler("startEnterActivity",object : MethodHandlerHaveReturn {
            override fun onMethodCall(params: Map<String, Any?>): String {
                val pageName = (params.get("pageName") as String?) ?: ""
                var intent = Intent(applicationContext, Class.forName(pageName))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                applicationContext.startActivity(intent)
                return "native 收到 flutter 的调用"
            }
        })

        FlutterBridge.instance.registerHandler("callNativeNoReturn",object : MethodHandlerNoReturn {
            override fun onMethodCall(params: Map<String, Any?>) {
                Log.e("MainActivity","method callNativeNoReturn 被调用了")
            }
        })


    }


}
