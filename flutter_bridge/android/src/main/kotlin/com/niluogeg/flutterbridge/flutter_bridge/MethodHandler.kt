package com.niluogeg.flutterbridge.flutter_bridge


interface MethodHandler {
}

interface MethodHandlerHaveReturnAsync : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>,result:MethodResult)
}

interface MethodHandlerHaveReturn : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>): String
}

interface MethodHandlerNoReturn : MethodHandler {
    fun onMethodCall(params: Map<String, Any?>)
}