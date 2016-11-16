package com.xiong.dandan.wudd.common.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.libs.utils.DensityUtils;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class MyPtrFrameLayout extends PtrClassicFrameLayout {

    public MyPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSetparameters();
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetparameters();
    }

    public MyPtrFrameLayout(Context context) {
        super(context);
        initSetparameters();
    }

    private void initSetparameters() {
        // show empty view
        this.setVisibility(View.VISIBLE);
        // 阻尼系数    默认: 1.7f，越大，感觉下拉时越吃力。
        this.setResistance(1.7f);
        //触发刷新时移动的位置比例  默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
        this.setRatioOfHeaderHeightToRefresh(1.2f);
        //回弹延时 默认 200ms，回弹到刷新高度所用时间
        this.setDurationToClose(200);
        //头部回弹时间 默认1000ms
        this.setDurationToCloseHeader(500);
        //下拉刷新 / 释放刷新 默认为释放刷新
        this.setPullToRefresh(false);
        //刷新是保持头部 默认值 true.
        this.setKeepHeaderWhenRefresh(true);
        //横着滑动时隐藏 用于viewpager
        this.disableWhenHorizontalMove(true);
        // 设置背景颜色
        this.setBackgroundColor(getResources().getColor(R.color.gray_common_background));
    }

    public void setUltraPullToRefresh(final OnRefreshListener refreshListener, final View InternalView) {
        final MaterialHeader header = new MaterialHeader(getContext());
        header.setPadding(0, DensityUtils.dp2px(getContext(), 10), 0, DensityUtils.dp2px(getContext(), 10));
        this.setHeaderView(header);
        this.addPtrUIHandler(header);
        this.disableWhenHorizontalMove(true);
        this.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if (refreshListener != null) {
                    // 刷新代码
                    refreshListener.refresh(frame);
                }
                // 十秒后自动关闭刷新动画
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 10000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame,
                                             View content, View header) {
                if (InternalView != null) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                            InternalView, header);
                } else {
                    return false;
                }
            }
        });
    }

    public interface OnRefreshListener {
        void refresh(PtrFrameLayout frame);
    }

}
