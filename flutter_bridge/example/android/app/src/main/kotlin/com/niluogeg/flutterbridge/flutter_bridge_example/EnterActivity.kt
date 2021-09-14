package com.niluogeg.flutterbridge.flutter_bridge_example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridge
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridgePlugin
import com.niluogeg.flutterbridge.flutter_bridge.HandleCallBack
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class EnterActivity : FlutterActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)

        findViewById<View>(R.id.btn).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "getFlutterVersion",
                callBack = object : HandleCallBack {
                    override fun callSuccess(result: String) {
                        tv.text = "FlutterVersion=$result"
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