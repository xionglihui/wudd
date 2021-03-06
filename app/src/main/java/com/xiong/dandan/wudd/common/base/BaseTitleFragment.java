package com.xiong.dandan.wudd.common.base;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.R;


/**
 * Created by xionglh on 2016/8/1.
 */
public class BaseTitleFragment extends BaseFragment {
    protected TextView mTextViewTitle, mTextViewLeft, mTextViewRight;
    protected RelativeLayout mLayoutTitleBar;
    private FrameLayout mContentLayout;

    protected RelativeLayout mRlViewTitile;



    // 带title 的 fragment
    public View onInflaterViewWithTitle(LayoutInflater inflater, ViewGroup container, int layoutId) {
        View mView = inflater.inflate(R.layout.fragment_base_layout, container, false);
        mTextViewTitle = (TextView) mView.findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) mView.findViewById(R.id.title_bar_left_button_back);
        mTextViewLeft.setCompoundDrawables(null, null, null, null);
        mTextViewRight = (TextView) mView.findViewById(R.id.title_bar_right_button);
        mLayoutTitleBar = (RelativeLayout) mView.findViewById(R.id.title_bar_layout);
        mContentLayout = (FrameLayout) mView.findViewById(R.id.base_fragment_content_layout);
        inflater.inflate(layoutId, mContentLayout, true);
        return mView;
    }

    /**
     * 重置 隐藏 左键和右键
     */
    public void resetTitleBar() {
        hideLeftButton();
        hideRightButton();
        mTextViewRight.setCompoundDrawables(null, null, null, null);
        mTextViewRight.setText("");
    }

    public void setTitle(int titleResId) {
        setTitle(getString(titleResId));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mTextViewTitle != null)
            mTextViewTitle.setText(title);
    }


    /**
     * 左边 右边 的button 都是默认隐藏的
     */
    public void hideLeftButton() {
        if (mTextViewLeft != null)
            mTextViewLeft.setVisibility(View.GONE);
    }

    /**
     * 左边 右边 的button 都是默认隐藏的
     */
    public void hideRightButton() {
        if (mTextViewRight != null)
            mTextViewRight.setVisibility(View.GONE);
    }


    /**
     * 显示左键，修改文字及添加listener
     */
    public void showLeftButtonWithTextListener(String text,
                                               View.OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setText(text);
            mTextViewLeft.setOnClickListener(listener);
            mTextViewLeft.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 重写 左键 listener
     *
     * @param listener
     */
    public void showLeftButtonWithListener(View.OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(listener);
        }
    }

    /**
     * 重置右键的图片及点击事件
     *
     * @param imageId
     * @param listener
     */
    public void showRightButtonWithImageAndListener(int imageId,
                                                    View.OnClickListener listener) {
        if (mTextViewRight != null) {
            Drawable nav_up = AppMyAplicition.genInstance().getResources().getDrawable(imageId);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());
            mTextViewRight.setCompoundDrawables(null, null, nav_up, null);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 重置右键的文字及点击事件
     *
     * @param text
     * @param listener
     */
    public void showRightButtonWithTextAndListener(String text,
                                                   View.OnClickListener listener) {
        if (mTextViewRight != null) {
            mTextViewRight.setText(text);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 通过resourse id 重置右键的文字及点击事件
     *
     * @param textId
     * @param listener
     */
    public void showRightButtonWithTextAndListener(int textId,
                                                   View.OnClickListener listener) {
        showRightButtonWithTextAndListener(AppMyAplicition.genInstance().getString(textId), listener);
    }

    @Override
    public int onSetLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        mRlViewTitile = (RelativeLayout) mContentView.findViewById(R.id.title_bar_layout);
        mTextViewTitle = (TextView) mContentView.findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) mContentView.findViewById(R.id.title_bar_left_button_back);
        mTextViewRight = (TextView) mContentView.findViewById(R.id.title_bar_right_button);
        hideLeftButton();
        hideRightButton();
    }
}


