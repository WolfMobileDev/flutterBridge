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

  @override
  void popRoute(CallInfo arg) {
    print('BridgetFlutterRouterApi popRoute arg =${arg.toString()}');
  }

  @override
  ResultInfo pushRoute(CallInfo arg) {
    print('BridgetFlutterRouterApi popRoute arg =${arg.toString()}');
    var cp = ResultInfo();
    cp.result = "BridgetFlutterRouterApi pushRoute";
    return cp;
  }

  @override
  ResultInfo registerHandler(CallInfo arg) {
    print('BridgetFlutterRouterApi registerHandler arg =${arg.toString()}');
    var cp = ResultInfo();
    cp.result = "BridgetFlutterRouterApi registerHandler";
    return cp;
  }

  @override
  ResultInfo send(CallInfo arg) {
    print('BridgetFlutterRouterApi send arg =${arg.toString()}');
    var cp = ResultInfo();
    cp.result = "BridgetFlutterRouterApi send";
    return cp;
  }
}
