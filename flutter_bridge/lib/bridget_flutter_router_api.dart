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
  void popRoute(CommonParams arg) {
    print('BridgetFlutterRouterApi popRoute arg =${arg.toString()}');
  }

  @override
  CommonParams pushRoute(CommonParams arg) {
    print('BridgetFlutterRouterApi popRoute arg =${arg.toString()}');
    var cp = CommonParams();
    cp.pageName = "BridgetFlutterRouterApi pushRoute";
    return cp;
  }
}
