// Autogenerated from Pigeon (v0.1.23), do not edit directly.
// See also: https://pub.dev/packages/pigeon
#import <Foundation/Foundation.h>
@protocol FlutterBinaryMessenger;
@class FlutterError;
@class FlutterStandardTypedData;

NS_ASSUME_NONNULL_BEGIN

@class FBResultInfo;
@class FBCallInfo;

@interface FBResultInfo : NSObject
@property(nonatomic, copy, nullable) NSString * result;
@end

@interface FBCallInfo : NSObject
@property(nonatomic, copy, nullable) NSString * methodName;
@property(nonatomic, strong, nullable) NSDictionary * params;
@end

@interface FBFlutterRouterApi : NSObject
- (instancetype)initWithBinaryMessenger:(id<FlutterBinaryMessenger>)binaryMessenger;
- (void)callFlutter:(FBCallInfo*)input completion:(void(^)(FBResultInfo*, NSError* _Nullable))completion;
@end
@protocol FBNativeRouterApi
-(nullable FBResultInfo *)callNative:(FBCallInfo*)input error:(FlutterError *_Nullable *_Nonnull)error;
@end

extern void FBNativeRouterApiSetup(id<FlutterBinaryMessenger> binaryMessenger, id<FBNativeRouterApi> _Nullable api);

NS_ASSUME_NONNULL_END
