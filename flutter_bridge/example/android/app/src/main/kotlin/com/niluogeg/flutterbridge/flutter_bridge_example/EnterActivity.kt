package com.niluogeg.flutterbridge.flutter_bridge_example

import android.os.Bundle
import android.view.View
import com.niluogeg.flutterbridge.flutter_bridge.FlutterBridgePlugin
import com.niluogeg.flutterbridge.flutter_bridge.Message
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class EnterActivity : FlutterActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        findViewById<View>(R.id.btn).setOnClickListener {

            val fb = getPlugin(flutterEngine);
            val params = Message.CallInfo()
            params.methodName = "flutterMethod"
//            fb?.flutterApi?.call(
//                params
//            ) { Log.e("MainActivity", "调用flutter 方法回调 ${it.result}") }
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