package com.xiong.dandan.wudd.common.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;

import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseTitleBarActivity;
import com.xiong.dandan.wudd.view.CustomWebView;

import butterknife.BindView;


/**
 * Webview显示
 * Created by xionglh on 2015/8/27.
 */
@SuppressLint("SetJavaScriptEnabled")
public class CustomWebViewActivity extends BaseTitleBarActivity {

    /**
     * 标题
     */
    public static final String WEB_SHOW_URL_TAG = "WEB_SHOW_URL_TAG";
    /**
     * 页面加载的URL
     */
    public static final String WEB_SHOW_TITLE_TAG = "WEB_SHOW_TITLE_TAG";

    @BindView(R.id.wb_products_loding)
    CustomWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
    }


    @Override
    protected void initViews() {
        super.initViews();
        showLeftButton();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String title = getIntent().getStringExtra(WEB_SHOW_TITLE_TAG);
        setTitle(title);
        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        mWebView.removeJavascriptInterface("searchBoxJavaBredge_");
        mWebView.loadUrl(loadingUrl());
    }

    private String loadingUrl() {
        String roolUrl = getIntent().getStringExtra(WEB_SHOW_URL_TAG);
        if (roolUrl.contains("?")) {
            roolUrl = roolUrl + "&";
        } else {
            roolUrl = roolUrl + "?";
        }
        roolUrl = roolUrl + "source=android";
        return roolUrl;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();

        }
    }

}
