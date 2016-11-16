package com.xiong.dandan.wudd.ui.common;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseTitleBarActivity;
import com.xiong.dandan.wudd.libs.utils.VersionUtil;


/**
 * Webview显示
 * Created by xionglh on 2015/8/27.
 */
@SuppressLint({"HandlerLeak", "NewApi"})
public class CommonWebViewActivity extends BaseTitleBarActivity {


    /**
     * 标题**
     */
    public static final String WEB_SHOW_URL_TAG = "WEB_SHOW_URL_TAG";
    /**
     * 页面加载的URL*
     */
    public static final String WEB_SHOW_TITLE_TAG = "WEB_SHOW_TITLE_TAG";


    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        initView();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        showLeftButton();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String mUrl = getIntent().getStringExtra(WEB_SHOW_URL_TAG);
        String title = getIntent().getStringExtra(WEB_SHOW_TITLE_TAG);

        setTitle(title);

        mWebView = (WebView) findViewById(R.id.wb_products_loding);
        WebSettings setting = mWebView.getSettings();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebchromeclient);
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        // 全屏显示
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        if (VersionUtil.sdkVersion17())
            mWebView.addJavascriptInterface(webAddJsObject, "android");
        mWebView.loadUrl(mUrl);
    }
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mProgressDialog != null)
                mProgressDialog.dismiss();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (mProgressDialog != null)
                mProgressDialog.show();

        }
    };


    private Handler mHandler = new Handler();
    public Object webAddJsObject = new Object() {


        @JavascriptInterface
        public void test() {//
            mHandler.post(new Runnable() {
                public void run() {

                }
            });
        }
    };

    private WebChromeClient mWebchromeclient = new WebChromeClient() {
        public boolean onJsAlert(WebView view, String url, String message,
                                 JsResult result) {
            AppAplicition.genInstance().showToast(message);

            result.confirm();
            return true;
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }
}
