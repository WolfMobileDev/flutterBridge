## 0.0.1
1. 解决 使用前必须要手动声明一个`MethodChannel`
2. 解决 方法注册的位置被限定死，必须要在 MethodCallHandler的 onMethodCall 中声明，而且没有办法反注册。
3. 解决 Native端的调用和回调必须保证在主线程。
