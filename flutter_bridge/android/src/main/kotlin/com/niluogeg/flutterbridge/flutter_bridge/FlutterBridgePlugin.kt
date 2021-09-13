package com.niluogeg.flutterbridge.flutter_bridge

import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterBridgePlugin */
class FlutterBridgePlugin: FlutterPlugin, Message.NativeRouterApi {
  lateinit var flutterApi : Message.FlutterRouterApi

  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Message.NativeRouterApi.setup(flutterPluginBinding.binaryMessenger,this)
    flutterApi=  Message.FlutterRouterApi(flutterPluginBinding.binaryMessenger)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

  }

  override fun pushNativeRoute(arg: Message.CommonParams?): Message.CommonParams {

    Log.e("FlutterBridgePlugin","pushNativeRoute=${arg.toString()}")
    val cp = Message.CommonParams();
    cp.pageName = "FlutterBridgePlugin pushNativeRoute";
    return  cp
  }


  override fun saveStackToHost(arg: Message.StackInfo?) {
    Log.e("FlutterBridgePlugin","saveStackToHost=${arg.toString()}")
  }

}
