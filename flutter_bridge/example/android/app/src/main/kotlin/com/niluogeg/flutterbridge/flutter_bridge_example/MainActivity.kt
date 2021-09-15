package com.niluogeg.flutterbridge.flutter_bridge_example

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.niluogeg.flutterbridge.flutter_bridge.*
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel


class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FlutterBridge.instance.registerHandler("getSDKVersion", object : MethodHandlerHaveReturn {
            override fun onMethodCall(params: Map<String, Any?>): String {
                return Build.VERSION.SDK_INT.toString()
            }
        })


        FlutterBridge.instance.registerHandler("startEnterActivity",
            object : MethodHandlerHaveReturn {
                override fun onMethodCall(params: Map<String, Any?>): String {
                    val pageName = (params.get("pageName") as String?) ?: ""
                    var intent = Intent(applicationContext, Class.forName(pageName))
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    applicationContext.startActivity(intent)
                    return "native 收到 flutter 的调用"
                }
            })

        FlutterBridge.instance.registerHandler("callNativeNoReturn",
            object : MethodHandlerNoReturn {
                override fun onMethodCall(params: Map<String, Any?>) {
                    Log.e("MainActivity", "method callNativeNoReturn 被调用了")
                }
            })


        FlutterBridge.instance.registerHandler("requestHttp", object :
            MethodHandlerHaveReturnAsync {
            override fun onMethodCall(params: Map<String, Any?>, result: MethodResult) {

                Log.e("MainActivity", "requestHttp start")

                Thread(Runnable {

                    HttpConnectionUtil().postRequsetAsync(
                        "https://bff.eshetang.com/dashboard/appVersion",
                        HashMap()
                    ) { str ->
                        Log.e("MainActivity", "currentThrad=${Thread.currentThread().name}")
                        result.success(str)
                        Log.e("MainActivity", "requestHttp end result=$str")
                    }
                }).start()

            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        FlutterBridge.instance.unRegisterHandler("startEnterActivity")
    }
}
