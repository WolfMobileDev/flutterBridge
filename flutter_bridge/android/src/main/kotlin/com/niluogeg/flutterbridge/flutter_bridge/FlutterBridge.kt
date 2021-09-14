package com.niluogeg.flutterbridge.flutter_bridge

class FlutterBridge private constructor() {


    lateinit var flutterApi: Message.FlutterRouterApi

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
    internal fun callNative(methodName: String, params: Map<String, Any?>): String {
        val methodHandle = methodMap[methodName]
        return methodHandle?.onMethodCall(params) ?: "can not found target method"
    }

    /**
     * 调用flutter方法
     * @methodName: 方法名
     * @param:方法入参
     * @return : 方法返回
     */
    fun callFlutter(
        methodName: String,
        params: Map<String, Any?> = HashMap(),
        callBack: HandleCallBack = DefaultHandleCallBack()
    ) {
        val ci = Message.CallInfo()
        ci.methodName = methodName
        ci.params = params as Map<Any, Any?>
        flutterApi.callFlutter(ci) { reply ->
            callBack.callSuccess(reply?.result ?: "")
        }
    }

}