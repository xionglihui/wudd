package com.xiong.dandan.wudd.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xiong.dandan.wudd.ui.login.modle.LoginModleImp;

import java.util.Stack;

/**
 *
 * Activity管理
 * Created by xionglh on 2017/2/21.
 */
public class ActivityPageManager {

    private static Stack<Activity> activityStack;
    private static ActivityPageManager instance;


    private ActivityPageManager() {

    }


    public static ActivityPageManager getInstance() {
        if (instance == null) {
            instance = new ActivityPageManager();
        }
        return instance;
    }

    /**
     *添加
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }


    /**
     *  移除
     */
    public void removeActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(activity);
    }

    /**
     * 当前activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /***
     * 关闭activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }
    /***
     * 关闭activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /***
     * 关闭activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /***
     * 关闭所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (null != activity) {
                activity.finish();
            }
        }
        activityStack.clear();

    }

    /***
     * 解除view对activity的引用
     * @param view
     */
    public static void unbindReferences(View view) {
        try {
            if (view != null) {
                view.destroyDrawingCache();
                unbindViewReferences(view);
                if (view instanceof ViewGroup){
                    unbindViewGroupReferences((ViewGroup) view);
                }
            }
        } catch (Throwable e) {

        }
    }

    private static void unbindViewGroupReferences(ViewGroup viewGroup) {
        int nrOfChildren = viewGroup.getChildCount();
        for (int i = 0; i < nrOfChildren; i++) {
            View view = viewGroup.getChildAt(i);
            unbindViewReferences(view);
            if (view instanceof ViewGroup)
                unbindViewGroupReferences((ViewGroup) view);
        }
        try {
            viewGroup.removeAllViews();
        } catch (Throwable mayHappen) {
        }
    }

    @SuppressWarnings("deprecation")
    private static void unbindViewReferences(View view) {

        try {
            view.setOnClickListener(null);
            view.setOnCreateContextMenuListener(null);
            view.setOnFocusChangeListener(null);
            view.setOnKeyListener(null);
            view.setOnLongClickListener(null);
            view.setOnClickListener(null);
        } catch (Throwable mayHappen) {
            //todo
        }
        Drawable d = view.getBackground();
        if (d != null){
            d.setCallback(null);
        }

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            d = imageView.getDrawable();
            if (d != null){
                d.setCallback(null);
            }
            imageView.setImageDrawable(null);
            imageView.setBackgroundDrawable(null);
        }

        if (view instanceof WebView) {
            WebView webview = (WebView) view;
            webview.stopLoading();
            webview.clearFormData();
            webview.clearDisappearingChildren();
            webview.setWebChromeClient(null);
            webview.setWebViewClient(null);
            webview.destroyDrawingCache();
            webview.destroy();
            webview = null;
        }


        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            try {
                listView.removeAllViewsInLayout();
            } catch (Throwable mayHappen) {
            }
            ((ListView) view).destroyDrawingCache();
        }
        if(view instanceof  RecyclerView){
            RecyclerView  recyclerView=(RecyclerView)view;
            try {
                recyclerView.removeAllViewsInLayout();
            } catch (Throwable mayHappen) {
            }
            ((RecyclerView) view).destroyDrawingCache();
        }
    }

    /**
     * 退出程序
     * @param context
     */
    public void exit(Context context) {
        exit(context, true);
    }

    /**
     * 退出程序
     * @param context
     * @param isClearCache
     */
    @SuppressWarnings("deprecation")
    public void exit(Context context, boolean isClearCache) {
        try {
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
