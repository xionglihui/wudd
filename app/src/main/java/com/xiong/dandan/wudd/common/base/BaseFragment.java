package com.xiong.dandan.wudd.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiong.dandan.wudd.ui.common.ImageFetchActivity;


public abstract class BaseFragment extends Fragment {


    public BaseActivity mContext;

    public View mContentView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseActivity();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(onSetLayoutId(), container, false);
            initView();
        }
        return mContentView;
    }

    /**
     * 设置布局文件
     *
     * @return 返回布局文件资源Id
     */
    public abstract int onSetLayoutId();

    public abstract void initView();

    public void skipAct(Intent intent) {
        mContext.skipAct(intent);
    }

    public void skipAct(int requestCode, Intent intent) {
        mContext.skipAct(requestCode, intent);
    }

    public void skipAct(Class clazz) {
        mContext.skipAct(clazz);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        mContext.skipAct(clazz, bundle);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        mContext.skipAct(clazz, bundle, flags);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 启动相机+截图
     *
     * @param requestCode
     */
    public void startCameraCropActivity(int requestCode) {
        Intent intent = new Intent(getActivity(), ImageFetchActivity.class);
        intent.putExtra(ImageFetchActivity.ACTION_TYPE,
                ImageFetchActivity.ACTION_TYPE_CAMERA);
        skipAct(requestCode, intent);
    }

    /**
     * 启动图册+截图
     *
     * @param requestCode
     */
    public void startPicturePickCropActivity(int requestCode) {
        Intent intent = new Intent(getActivity(), ImageFetchActivity.class);
        intent.putExtra(ImageFetchActivity.ACTION_TYPE,
                ImageFetchActivity.ACTION_TYPE_PICTURE);
        skipAct(requestCode, intent);
    }


}
