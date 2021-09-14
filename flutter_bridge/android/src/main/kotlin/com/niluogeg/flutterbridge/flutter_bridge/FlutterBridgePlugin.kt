package com.niluogeg.flutterbridge.flutter_bridge

import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin

/** FlutterBridgePlugin */
class FlutterBridgePlugin : FlutterPlugin, Message.NativeRouterApi {
    private lateinit var flutterApi: Message.FlutterRouterApi
    private lateinit var applicationContext: Context
    private lateinit var flutterBridge: FlutterBridge
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        applicationContext = flutterPluginBinding.applicationContext
        flutterApi = Message.FlutterRouterApi(flutterPluginBinding.binaryMessenger)
        flutterBridge = FlutterBridge.instance
        flutterBridge.flutterApi=flutterApi
        Message.NativeRouterApi.setup(flutterPluginBinding.binaryMessenger, this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }


    override fun callNative(callInfo: Message.CallInfo?): Message.ResultInfo {
        val methodMame = callInfo?.methodName ?: ""
        val params = (callInfo?.params ?: HashMap<String, Any?>()) as Map<String, Any?>
        val result = flutterBridge.callNative(methodMame, params)
        val ri = Message.ResultInfo()
        ri.result = result
        return ri
    }


}
