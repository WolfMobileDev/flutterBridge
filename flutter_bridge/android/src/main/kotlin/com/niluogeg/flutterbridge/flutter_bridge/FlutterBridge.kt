package com.niluogeg.flutterbridge.flutter_bridge

class FlutterBridge private constructor() {


    private val methodList = ArrayList<String>()
    private val methodMap = hashMapOf<String, MethodHandler>()


    companion object {
        val instance: FlutterBridge by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FlutterBridge() }
    }


    /**
     * 注册方法
     */
    fun registerHandler(methodName: String, methodHandler: MethodHandler) {
        methodMap[methodName] = methodHandler
    }


    /**
     * 方法被调用
     * @methodName: 方法名
     * @param:方法入参
     * @return : 方法返回
     */
    fun call(methodName: String, params: Map<String, Any?>): String {
        val methodHandle = methodMap[methodName]
        return methodHandle?.onMethodCall(params) ?: ""
    }

}