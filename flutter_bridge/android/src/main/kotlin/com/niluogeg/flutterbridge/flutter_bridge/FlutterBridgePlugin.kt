package com.niluogeg.flutterbridge.flutter_bridge

import Message.NativeRouterApi.setup
import android.util.Log
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FlutterBridgePlugin */
class FlutterBridgePlugin: FlutterPlugin, MethodCallHandler, Message.NativeRouterApi {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Message.NativeRouterApi.setup(flutterPluginBinding.binaryMessenger,this)
  }

  override fun pushNativeRoute(arg: Message.CommonParams?) {
    Log.e("FlutterBridgePlugin","pushNativeRoute=${arg.toString()}")
  }

  override fun saveStackToHost(arg: Message.StackInfo?) {
    Log.e("FlutterBridgePlugin","saveStackToHost=${arg.toString()}")
  }

}
