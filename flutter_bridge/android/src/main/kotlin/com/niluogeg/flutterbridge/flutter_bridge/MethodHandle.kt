package com.niluogeg.flutterbridge.flutter_bridge

interface MethodHandle {
    fun onMethodCall(params:Map<String, Any?>):String
}