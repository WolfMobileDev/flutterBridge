package com.niluogeg.flutterbridge.flutter_bridge

interface MethodHandler {
    fun onMethodCall(params:Map<String, Any?>):String
}