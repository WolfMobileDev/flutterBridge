import 'package:flutter_bridge/messages.dart';

class BridgetFlutterRouterApi extends FlutterRouterApi {
  factory BridgetFlutterRouterApi() {
    if (_instance == null) {
      _instance = BridgetFlutterRouterApi._();
      FlutterRouterApi.setup(_instance);
    }
    return _instance;
  }

  BridgetFlutterRouterApi._();

  static BridgetFlutterRouterApi _instance;


  ResultInfo registerHandler(CallInfo arg) {
    print('BridgetFlutterRouterApi registerHandler arg =${arg.toString()}');
    var cp = ResultInfo();
    cp.result = "BridgetFlutterRouterApi registerHandler";
    return cp;
  }

  @override
  ResultInfo call(CallInfo arg) {
    print('BridgetFlutterRouterApi send arg =${arg.toString()}');
    var cp = ResultInfo();
    cp.result = "BridgetFlutterRouterApi send";
    return cp;
  }
}
