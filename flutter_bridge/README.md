
## 为什么会开发这个框架

在flutter和Native混合开发的过程中我们难免会遇到flutter和Native相互通信的场景，这时我们就会用到flutter提供的 `Flutter Platform Channel`能力。大部分情况我们使用的都是 `MethodChannel`，一般我们会这样是用。

##### flutter中使用methodchannel
```
  //声明 methodchannel
  static const MethodChannel LUXURY_METHOD_CHANNEL = MethodChannel('flutter_native_channel');


    //注册方法监听
    MethodChannelHolder.LUXURY_METHOD_CHANNEL.setMethodCallHandler((MethodCall methodCall) async {
     switch (methodCall.method) {
      case "methodA": 
        ......;
        break;
      case "methodB": 
        ......;
        break;
    }
    });
    
    //调用 方法
 var result = await MethodChannelHolder.LUXURY_METHOD_CHANNEL.invokeMethod(MethodHolder.COMMON_GOTO_HOME, {"isAll": path.isNotEmpty? "0": isAll});
```

##### 原生中使用
```
    //声明 methodchannel
    val LUXURY_METHOD_CHANNEL = MethodChannel(dartExecutor,"flutter_native_channel")
    
    //注册方法监听
    MethodChannelHolder.LUXURY_METHOD_CHANNEL.setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
            when {
                "methodA" -> {
                   .....
                }
                "methodB" -> {
                    .....
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
        
    //调用 方法
    MethodChannelHolder.LUXURY_METHOD_CHANNEL.invokeMethod("nativeCall/bbb", hashMapOf("cc" to "dd"),object :MethodChannel.Result{
                override fun notImplemented() {
                    LogUtil.e("原生 调用 flutter 收到 回调 notImplemented")
                }

                override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                    LogUtil.e("原生 调用 flutter 收到 回调 error = $errorMessage")
                }

                override fun success(result: Any?) {
                    LogUtil.e("原生 调用 flutter 收到 回调 success result=${result}")
                }

            })

```

那么我们直接使用`MethodChannel`会有哪些不方便或者问题呢？
1. 使用前必须要手动声明一个`MethodChannel`，这也太麻烦了。（手动狗头）
2. 方法注册的位置被限定死，必须要在 MethodCallHandler的 onMethodCall 中声明，而且没有办法反注册。
3. Native端的调用和回调必须保证在主线程。

基于这些问题于是就有了`flutter_bridge`.

## 如何封装
#### 1. 解决“必须要手动声明一个`MethodChannel`问题”
我们会在框架初始化的时候会自动创建一个`MethodChannel`，这样我们就不用自己创建`MethodChannel`了，也不用想channel的命名了，哈哈哈
```
//flutter
class FlutterBridge {
  static const String CHANNEL_NAME = "flutterBridge/core";
  MethodChannel _channel = new MethodChannel(CHANNEL_NAME);

  FlutterBridge._() {
    _channel.setMethodCallHandler(onMethodCall);
  }
}

//native
fun initChannel(messenger: BinaryMessenger) {
    channel = MethodChannel(messenger, CHANNEL_NAME)
    channel.setMethodCallHandler(this)
}

```
#### 2. 解决“方法注册的位置被限定死，而且没有办法反注册”
我们注册方法时是将方法名称和方法体存放到一个map中，当有方法被调用时会从map中找到对应方法体并执行，反注册则是从map中移除该方法。
```
//flutter---
var _methodMap = HashMap<String, MethodHandler>();

// 注册方法
void registerHandler(String methodName, MethodHandler methodHandle) {
_methodMap[methodName] = methodHandle;
}

// 反注册方法
MethodHandler unregisterHandler(String methodName) {
return _methodMap.remove(methodName);
}
  
//native---
private val methodMap = hashMapOf<String, MethodHandler>()

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
```
#### 3. 解决“Native端的调用和回调必须保证在主线程”
我们会在调用和回调时判断当前线程，如果非主线程，则切换到主线程在进行调用或回调。这样开发者就不用担心线程切换问题了
```
//调用 
    fun <R> callFlutter(
        methodName: String,
        params: Map<String, Any?> = HashMap(),
        callBack: HandleCallBack<R> = DefaultHandleCallBack<R>()
    ) {

        if (HandlerUtils.isMainThread()) {
            callFlutterInner(methodName, params, callBack)
        } else {
            HandlerUtils.getMainHandler().post {
                callFlutterInner(methodName, params, callBack)
            }
        }

    }
//回调
    fun success(result: Any?) {
        if (HandlerUtils.isMainThread()) {
            resultOrigin.success(result)
        } else {
            HandlerUtils.getMainHandler().post {
                resultOrigin.success(result)
            }
        }
    }

```

## 使用（更多使用可以前往[github](https://github.com/NiLuogege/flutterBridge)）
#### flutter
```
//方法注册
FlutterBridge.instance.registerHandler("getFlutterMap", (params) {
      print('getFlutterMap ${params.toString()}');
      return {
        "aa": "bb",
        "cc": 1,
        "dd": [1, 2, 3],
        "ee": true
      };
    });

 //方法调用
 MaterialButton(
      onPressed: () async {
        var map = await FlutterBridge.instance.callNative<Map>("callNativeReturnMap", params: {
          "aa": "bb",
          "cc": 1,
          "dd": [1, 2, 3],
          "ee": true
        });
        result = map.toString();
        setState(() {});
      },
      child: Text("调用原生方法-带入参-返回值是map"),
    )

```

#### 原生
```
//方法注册
FlutterBridge.instance.registerHandler("callNativeReturnMap",
    object : MethodHandlerHaveReturn<Map<String, Any>> {
        override fun onMethodCall(params: Map<String, Any?>): Map<String, Any> {

            Log.e("flutterBridge","callNativeReturnMap params =${params.toString()}")

            return hashMapOf(
                "aa" to "bb",
                "cc" to 1,
                "dd" to arrayListOf(1, 2, 3),
                "ee" to true
            )
        }
    })
//方法调用
 findViewById<View>(R.id.btn_map).setOnClickListener {
    FlutterBridge.instance.callFlutter(
        "getFlutterMap",
        params = hashMapOf(
            "aa" to "bb",
            "cc" to 1,
            "dd" to arrayListOf(1, 2, 3),
            "ee" to true
        ),
        callBack = object : HandleCallBack<Map<String, Any?>> {
            override fun callSuccess(result: Map<String, Any?>?) {
                tv.text = "调用flutter方法-带入参-返回Map result=${result.toString()}"
            }

        })

}

```
## 操作演示
![flutter-native.gif](https://upload-images.jianshu.io/upload_images/3571184-75e12c94db91aa2a.gif?imageMogr2/auto-orient/strip)

![native-flutter.gif](https://upload-images.jianshu.io/upload_images/3571184-155af77293f5f3cd.gif?imageMogr2/auto-orient/strip)

## [GitHub地址](https://github.com/NiLuogege/flutterBridge)

## 参考
- [深入理解Flutter Platform Channel](https://developer.aliyun.com/article/630105)
