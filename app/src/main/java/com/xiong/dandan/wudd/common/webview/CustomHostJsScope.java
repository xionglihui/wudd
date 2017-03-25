package com.xiong.dandan.wudd.common.webview;

import android.content.Intent;
import android.webkit.WebView;

import com.xiong.dandan.wudd.ui.login.ui.LoginActivity;

/**
 * js方法注入(必须在注入类中定义为public static且第一个参数接收WebView)
 * android.testA()
 * Created by xionglh on 2017/3/23.
 */
public class CustomHostJsScope {

    public static void testA(WebView webView) {
        CustomWebViewActivity baseActivity = (CustomWebViewActivity) webView.getContext();
        baseActivity.startActivity(new Intent(baseActivity, LoginActivity.class));
    }



}
