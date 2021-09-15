package com.niluogeg.flutterbridge.flutter_bridge

import io.flutter.plugin.common.MethodChannel

interface MethodHandler {
}

interface MethodHandlerHaveReturnAsync : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>,result: MethodChannel.Result)
}

interface MethodHandlerHaveReturn : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>): String
}

interface MethodHandlerNoReturn : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>)
}