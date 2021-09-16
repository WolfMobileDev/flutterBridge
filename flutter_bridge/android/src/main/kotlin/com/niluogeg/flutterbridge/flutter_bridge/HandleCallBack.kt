package com.niluogeg.flutterbridge.flutter_bridge

import android.widget.Toast


interface HandleCallBack<R> {
    fun callSuccess(result: R?)
    fun callError(errorMessage: String = "", errorCode: String = "", errorDetails: Any = "") {

    }

    fun notImplemented() {
        Toast.makeText(FlutterBridge.instance.context, "flutter没有找到对应方法", Toast.LENGTH_LONG).show()
    }
}


class DefaultHandleCallBack<R> : HandleCallBack<R> {
    override fun callSuccess(result: R?) {
    }


}