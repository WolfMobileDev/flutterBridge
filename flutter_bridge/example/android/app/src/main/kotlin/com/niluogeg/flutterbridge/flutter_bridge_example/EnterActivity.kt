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
            FlutterBridge.instance.callFlutter(
                "getFlutterVersion",
                callBack = object : HandleCallBack<String> {
                    override fun callSuccess(result: String?) {
                        tv.text = "FlutterVersion=$result"
                    }

                })

        }

        findViewById<View>(R.id.btn_map).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "getFlutterMap",
                params = hashMapOf("aa" to "bb"),
                callBack = object : HandleCallBack<Map<String, Any?>> {
                    override fun callSuccess(result: Map<String, Any?>?) {
                        tv.text = "getFlutterMap 被调用了 result=${result.toString()}"
                    }

                })

        }

        findViewById<View>(R.id.btn_noreturn).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "callFlutterNoReturn",
                params = hashMapOf("aa" to "bb"),
                callBack = object : HandleCallBack<Void> {
                    override fun callSuccess(result: Void?) {

                        tv.text = "callFlutterNoReturn 被调用了 result=${result?.toString()}"
                    }


                })

        }

        findViewById<View>(R.id.btn_notImplemented).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "methodNotImplemented",
                params = hashMapOf("aa" to "bb"),
                callBack = object : HandleCallBack<Map<String, Any?>> {
                    override fun callSuccess(result: Map<String, Any?>?) {
                        tv.text = "callFlutterNoReturn 被调用了 result=${result.toString()}"
                    }

                })

        }


        findViewById<View>(R.id.btn_testError).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "testError",
                callBack = object : HandleCallBack<Any> {
                    override fun callSuccess(result: Any?) {
                        tv.text = "testError 被调用了 result=${result?.toString()}"
                    }

                })

        }

        findViewById<View>(R.id.btn_testError2).setOnClickListener {
            FlutterBridge.instance.callFlutter(
                "testError2",
                callBack = object : HandleCallBack<Any> {
                    override fun callSuccess(result: Any?) {
                        tv.text = "testError2 被调用了 result=${result?.toString()}"
                    }

                })

        }
    }

}