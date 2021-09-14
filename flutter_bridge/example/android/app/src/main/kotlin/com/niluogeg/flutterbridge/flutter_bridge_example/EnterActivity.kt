package com.niluogeg.flutterbridge.flutter_bridge_example

import android.os.Bundle
import android.util.Log
import android.view.View
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridge
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridgePlugin
import com.niluogeg.flutterbridge.flutter_bridge.HandleCallBack
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class EnterActivity : FlutterActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.btn).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "nativeCallFlutter",
                hashMapOf("aaa" to "bbbb"),
                object : HandleCallBack {
                    override fun callSuccess(result: String) {
                        Log.e("MainActivity", "原生 调用 flutter 返回值 cpResult=$result")
                    }
                })

        }
    }

    /**
     * 通过 FlutterEngine获取到 FlutterBridgePlugin （flutter bridge 的 插件）
     */
    fun getPlugin(engine: FlutterEngine?): FlutterBridgePlugin? {
        if (engine != null) {
            try {
                val pluginClass =
                    Class.forName("com.niluogeg.flutterbridge.flutter_bridge.FlutterBridgePlugin") as Class<out FlutterBridgePlugin?>
                return engine.plugins[pluginClass] as FlutterBridgePlugin?
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
        return null
    }
}