package com.niluogeg.flutterbridge.flutter_bridge

interface HandleCallBack {
}

interface HandleCallBackHaveReturn : HandleCallBack {
    fun callSuccess(result: String)
}

interface HandleCallBackNoReturn : HandleCallBack {
    fun callSuccess()
}


class DefaultHandleCallBack : HandleCallBackNoReturn {
    override fun callSuccess() {

    }

}