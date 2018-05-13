package com.xiong.dandan.utilslibrary.common;

import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;
import android.widget.PopupWindow;

/**
 * popWidow弹出框
 *
 * @author xionglh
 * @version MyPopWindow.java 2014年12月5日下午2:50:34 v1.0.0 xionglh
 */
public class MyPopWindow {
    private PopupWindow popupWindow;

    private int showLocal = 4;
    private OnTouchListener mPopupWindowOnTouchListener;


    /**
     * @param showLocal 默认显示在下边 0显示在左边 1显示在右边 2显示在上边5显示在屏中
     */
    public MyPopWindow(int showLocal) {
        this.mPopupWindowOnTouchListener = popupWindowOnTouchListener;
        this.showLocal = showLocal;
    }

    public MyPopWindow() {
        this.mPopupWindowOnTouchListener = popupWindowOnTouchListener;
    }

    /**
     * deprecated
     *
     * @param view       加载的视图View
     * @param setPopView 触发的View
     * @param wight      弹出框的宽度
     * @param hegith     弹出框的高度
     */
    public void loadPopWindow(View view, View setPopView, int wight, int hegith) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(view, wight,
                    LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            setShowLocald(showLocal, setPopView);
            popupWindow.getContentView().setOnTouchListener(
                    mPopupWindowOnTouchListener);
        }
    }


    public void setPopupWindowOnTouchListener(OnTouchListener popupWindowOnTouchListeners) {
        this.mPopupWindowOnTouchListener = popupWindowOnTouchListeners;
    }

    /**
     * @param view       加载的视图View
     * @param setPopView 触发的View
     * @param wight      弹出框的宽度
     * @param hegith     弹出框的高度
     */
    public void loadPopWindows(View view, View setPopView, int wight, int hegith) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(view, wight, hegith);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            setShowLocald(showLocal, setPopView);
            popupWindow.getContentView().setOnTouchListener(
                    mPopupWindowOnTouchListener);
        }
    }

    private void setShowLocald(int i, View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        switch (i) {
            case 0:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]
                        - popupWindow.getWidth(), location[1]);
                break;
            case 1:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0]
                        + view.getWidth(), location[1]);
                break;
            case 2:
                popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0],
                        location[1] - popupWindow.getHeight());
                break;
            case 3:
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case 5:
                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            default:
                popupWindow.showAsDropDown(view);
                break;
        }
    }

    public void closePopWindow() {
        if (popupWindow != null)
            popupWindow.dismiss();
        popupWindow = null;
    }

    public boolean isShowing() {
        return popupWindow != null && popupWindow.isShowing();
    }


    private OnTouchListener popupWindowOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (popupWindow != null) {
                popupWindow.setFocusable(false);
                closePopWindow();
            }
            return true;
        }
    };
}
