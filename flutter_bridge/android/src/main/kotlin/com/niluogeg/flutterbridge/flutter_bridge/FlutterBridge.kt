package com.niluogeg.flutterbridge.flutter_bridge

import android.content.Context
import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class FlutterBridge private constructor() : MethodChannel.MethodCallHandler {

    lateinit var context: Context //applicationContext

    private val methodMap = hashMapOf<String, MethodHandler>()


    companion object {
        val instance: FlutterBridge by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FlutterBridge() }
        const val ERROR_CODE_ILLEGAL_METHODHANDLER = "0001"
        const val ERROR_CODE_ERROR = "0002" // 调用报错
    }


    /**
     * 注册方法
     */
    fun registerHandler(methodName: String, methodHandler: MethodHandler) {
        methodMap[methodName] = methodHandler
    }

    /**
     * 反 注册方法
     */
    fun unRegisterHandler(methodName: String): MethodHandler? {
        return methodMap.remove(methodName)
    }


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        try {
            Log.e("FlutterBridge", "call=${call.method} arguments=${call.arguments}")
            val methodName = call.method ?: ""
            val params =
                if (call.arguments == null) HashMap()
                else call.arguments as Map<String, Any?>
            val methodHandle = methodMap[methodName]
            if (methodHandle != null) {
                when (methodHandle) {
                    is MethodHandlerHaveReturn -> {
                        result.success(methodHandle.onMethodCall(params))
                    }
                    is MethodHandlerNoReturn -> {
                        methodHandle.onMethodCall(params)
                        result.success("No Return Method Handler")
                    }
                    is MethodHandlerHaveReturnAsync -> {
                        methodHandle.onMethodCall(params, MethodResult(result))
                    }
                    else -> {
                        result.error(
                            ERROR_CODE_ILLEGAL_METHODHANDLER,
                            "illegal MethodHandler",
                            null
                        )
                    }
                }
            } else {
                result.notImplemented()
            }
        } catch (e: Exception) {
            result.error(ERROR_CODE_ERROR, e.message, e)
        }
    }
}