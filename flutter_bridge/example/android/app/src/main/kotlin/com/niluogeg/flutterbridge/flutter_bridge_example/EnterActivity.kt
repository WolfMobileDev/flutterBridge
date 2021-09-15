package com.niluogeg.flutterbridge.flutter_bridge_example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.niluogeg.flutterbridge.flutter_bridge.*
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class EnterActivity : FlutterActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)

        findViewById<View>(R.id.btn).setOnClickListener {
//            FlutterBridge.instance.callFlutter(
//                "getFlutterVersion",
//                callBack = object : HandleCallBackHaveReturn {
//                    override fun callSuccess(result: String) {
//                        tv.text = "FlutterVersion=$result"
//                    }
//                })

        }

        findViewById<View>(R.id.btn_noreturn).setOnClickListener {
//            FlutterBridge.instance.callFlutter(
//                "callFlutterNoReturn",
//                callBack = object : HandleCallBackNoReturn {
//                    override fun callSuccess() {
//                        tv.text = "callFlutterNoReturn 被调用了"
//                    }
//                })

        }
    }

}