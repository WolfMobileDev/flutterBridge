package com.niluogeg.flutterbridge.flutter_bridge

import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterBridgePlugin */
class FlutterBridgePlugin : FlutterPlugin, Message.NativeRouterApi {
    lateinit var flutterApi: Message.FlutterRouterApi
    lateinit var applicationContext: android.content.Context
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        applicationContext = flutterPluginBinding.applicationContext
        Message.NativeRouterApi.setup(flutterPluginBinding.binaryMessenger, this)
        flutterApi = Message.FlutterRouterApi(flutterPluginBinding.binaryMessenger)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }

    override fun send(arg: Message.CallInfo?): Message.ResultInfo {
        Log.e("FlutterBridgePlugin", "send=${arg?.params.toString()}")
        val pageName = (arg?.params?.get("pageName") as String?) ?: ""

        Log.e("FlutterBridgePlugin", "pageName=${pageName}")

        var intent = Intent(applicationContext, Class.forName(pageName))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        applicationContext.startActivity(intent)
        val cp = Message.ResultInfo();
        cp.result = "FlutterBridgePlugin send";
        return cp
    }

    override fun registerHandler(arg: Message.CallInfo?): Message.ResultInfo {
        Log.e("FlutterBridgePlugin", "registerHandler=${arg.toString()}")
        val cp = Message.ResultInfo();
        cp.result = "FlutterBridgePlugin registerHandler";
        return cp
    }

    override fun pushNativeRoute(arg: Message.CallInfo?): Message.ResultInfo {

        Log.e("FlutterBridgePlugin", "pushNativeRoute=${arg.toString()}")
        val cp = Message.ResultInfo();
        cp.result = "FlutterBridgePlugin pushNativeRoute";
        return cp
    }


    override fun saveStackToHost(arg: Message.StackInfo?) {
        Log.e("FlutterBridgePlugin", "saveStackToHost=${arg.toString()}")
    }

}
