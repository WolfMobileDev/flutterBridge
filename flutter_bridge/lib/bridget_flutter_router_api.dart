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
  void pushRoute(CommonParams arg) {
    print('BridgetFlutterRouterApi pushRoute arg =${arg.toString()}');
  }
}
