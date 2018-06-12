package com.xiong.dandan.wudd.common.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiong.dandan.wudd.R;


public class BaseTitleBarActivity extends BaseActivity {
    protected TextView mTextViewTitle, mTextViewLeft, mTextViewRight;

    protected RelativeLayout mRlViewTitile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void initViews() {
        mRlViewTitile=(RelativeLayout)findViewById(R.id.title_bar_layout);
        mTextViewTitle = (TextView) findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) findViewById(R.id.title_bar_left_button_back);
        mTextViewRight = (TextView) findViewById(R.id.title_bar_right_button);
        hideLeftButton();
        hideRightButton();
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
     * 显示返回键，点击后关闭页面
     */
    public void showLeftButton() {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });
        }
    }

    /**
     * 显示左键，修改文字及添加listener
     */
    public void showLeftButtonWithTextListener(String text,
                                               OnClickListener listener){
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
    public void showLeftButtonWithListener(OnClickListener listener) {
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
                                                    OnClickListener listener) {
        if (mTextViewRight != null) {
            Drawable nav_up = getResources().getDrawable(imageId);
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
                                                   OnClickListener listener) {
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
                                                   OnClickListener listener) {
        showRightButtonWithTextAndListener(getString(textId), listener);
    }
}
