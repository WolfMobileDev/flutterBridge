package com.niluogeg.flutterbridge.flutter_bridge

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

/** FlutterBridgePlugin */
class FlutterBridgePlugin : FlutterPlugin {
    companion object {
        const val CHANNEL_NAME = "flutterBridge/core"
    }

    private lateinit var applicationContext: Context
    private lateinit var flutterBridge: FlutterBridge

    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        applicationContext = flutterPluginBinding.applicationContext
        flutterBridge = FlutterBridge.instance
        flutterBridge.context = applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, CHANNEL_NAME)
        channel.setMethodCallHandler(flutterBridge)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }


}
