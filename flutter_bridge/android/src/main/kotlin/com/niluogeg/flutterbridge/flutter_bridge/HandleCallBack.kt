package com.niluogeg.flutterbridge.flutter_bridge

interface HandleCallBack {
    fun callSuccess(result: String)
}

class  DefaultHandleCallBack : HandleCallBack{
    override fun callSuccess(result: String) {

    }

}