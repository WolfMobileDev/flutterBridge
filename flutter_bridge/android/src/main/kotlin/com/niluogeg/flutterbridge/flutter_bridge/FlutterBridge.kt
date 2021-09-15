package com.niluogeg.flutterbridge.flutter_bridge

import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class FlutterBridge private constructor() : MethodChannel.MethodCallHandler {


    private val methodList = ArrayList<String>()
    private val methodMap = hashMapOf<String, MethodHandler>()
    private val resultMap = hashMapOf<String, MethodChannel.Result>()


    companion object {
        val instance: FlutterBridge by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FlutterBridge() }
    }


    /**
     * 注册方法
     */
    fun registerHandler(methodName: String, methodHandler: MethodHandler) {
        methodMap[methodName] = methodHandler
    }


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.e("FlutterBridge", "call=${call.method} arguments=${call.arguments}")
        val methodName = call.method ?: ""
        val params = call.arguments as Map<String, Any?>
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
                else -> {
                    result.error("0001", "illegal MethodHandler", null)
                }
            }
        } else {
            result.notImplemented()
        }
    }

//    /**
//     * 方法被调用
//     * @methodName: 方法名
//     * @param:方法入参
//     * @return : 方法返回
//     */
//    internal fun callNative(methodName: String, params: Map<String, Any?>): String {
//        val methodHandle = methodMap[methodName]
//        return if (methodHandle != null) {
//            when (methodHandle) {
//                is MethodHandlerHaveReturn -> {
//                    methodHandle.onMethodCall(params)
//                }
//                is MethodHandlerNoReturn -> {
//                    methodHandle.onMethodCall(params)
//                    "No Return Method Handler"
//                }
//                else -> {
//                    "illegal MethodHandler"
//                }
//            }
//        } else {
//            "can not found target method"
//        }
//    }
//
//    /**
//     * 调用flutter方法
//     * @methodName: 方法名
//     * @param:方法入参
//     * @return : 方法返回
//     */
//    fun callFlutter(
//        methodName: String,
//        params: Map<String, Any?> = HashMap(),
//        callBack: HandleCallBack = DefaultHandleCallBack()
//    ) {
//        val ci = Message.CallInfo()
//        ci.methodName = methodName
//        ci.params = params as Map<Any, Any?>
//        flutterApi.callFlutter(ci) { reply ->
//            if (callBack is HandleCallBackHaveReturn) {
//                callBack.callSuccess(reply?.result ?: "")
//            } else if (callBack is HandleCallBackNoReturn) {
//                callBack.callSuccess()
//            }
//        }
//    }


}