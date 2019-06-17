package com.example.kakao_flutter_sdk

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class KakaoFlutterSdkPlugin(private val registrar: Registrar): MethodCallHandler {
  companion object {
    lateinit var redirectUri: String
    lateinit var redirectUriResult: Result
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      println("register")
      val channel = MethodChannel(registrar.messenger(), "kakao_flutter_sdk")
      channel.setMethodCallHandler(KakaoFlutterSdkPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when {
        call.method == "getPlatformVersion" -> result.success("Android ${android.os.Build.VERSION.RELEASE}")
        call.method == "getOrigin" -> result.success(Utility.getKeyHash(registrar.activeContext()))
        call.method == "getKaHeader" -> result.success(Utility.getKAHeader(registrar.activeContext()))
        call.method == "launchWithBrowserTab" -> {
          val args = call.arguments as Map<*, *>
          val uri = args["url"] as String
          redirectUri = args["redirect_uri"] as String
          redirectUriResult = result
          AuthCodeCustomTabsActivity.startWithUrl(registrar.activity(), uri)
        }
        else -> result.notImplemented()
    }
  }
}