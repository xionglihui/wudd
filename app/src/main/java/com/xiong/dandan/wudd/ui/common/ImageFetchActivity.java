package com.xiong.dandan.wudd.ui.common;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiong.dandan.common.util.FileUtil;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.dandan.wudd.libs.GlobalConstants;
import com.xiong.routerlibrary.url.ARouterPageUrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 拍照截图启动页面 */

@Route(path = ARouterPageUrl.ACTIVITY_IMAGEFETCH)
public class ImageFetchActivity extends BaseActivity {
    public static final String ACTION_TYPE = "com.augmentum.ball.common.activity.ImageFetchActivity.ACTION_TYPE";
    public static final String IS_HEADER_CROP = "ImageFetchActivity.IS_HEADER_CROP";

    public final static int ACTION_TYPE_CAMERA = 0x001;
    public final static int ACTION_TYPE_PICTURE = 0x002;
    public final static int ACTION_TYPE_CROP = 0x003;
    private final static int ACTION_TYPE_FINISH = 0x004;
    private int mActionType = 0;
    private String mPath;
    private boolean isHeaderCrop = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_fetch);
    }

    @Override
    protected void initViews() {
        mActionType = getIntent().getIntExtra(ACTION_TYPE, ACTION_TYPE_CAMERA);
        isHeaderCrop = getIntent().getBooleanExtra(IS_HEADER_CROP, true);

        mPath = GlobalConstants.IMAGE_CAMERA_URI.getPath();
        switch (mActionType) {
            case ACTION_TYPE_CAMERA:
                gotoCameraActivity();
                break;
            case ACTION_TYPE_PICTURE:
                gotoPicturePickActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTION_TYPE_PICTURE:
                    String fileRealPath;
                    // 获取选中的图片路径
                    Uri uriPath = intent.getData();
                    if (uriPath != null) {
                        String imgUriPath = uriPath.getPath();
                        Cursor cursor = getContentResolver().query(
                                intent.getData(), null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            int columnIndex = cursor
                                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            fileRealPath = cursor.getString(columnIndex);
                        } else {
                            fileRealPath = imgUriPath;
                        }
                    } else {
                        fileRealPath = GlobalConstants.IMAGE_CAPTURE_URI.getPath();
                    }
                    mPath = fileRealPath;
                case ACTION_TYPE_CROP:
                    if (FileUtil.exists(mPath)) {
                        gotoCropImage();
                    } else {
                        finishActivity(false);
                    }
                    break;
                case ACTION_TYPE_FINISH:
                    if (!FileUtil.exists(GlobalConstants.IMAGE_CAPTURE_URI
                            .getPath())) {
                        Bitmap bmap = intent.getParcelableExtra("data");
                        FileOutputStream foutput = null;
                        try {
                            foutput = new FileOutputStream(
                                    GlobalConstants.IMAGE_CAPTURE_URI.getPath());
                            bmap.compress(Bitmap.CompressFormat.JPEG, 100, foutput);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            if (null != foutput) {
                                try {
                                    foutput.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    finishActivity(true);
                    break;
                default:
                    finishActivity(false);
                    break;
            }
        } else {
            finishActivity(false);
        }

    }

    private void gotoCameraActivity() {
        startCameraActivity(ACTION_TYPE_CROP);
        mActionType = ACTION_TYPE_CROP;
    }

    private void gotoPicturePickActivity() {
        startPicturePickActivity(ACTION_TYPE_PICTURE);
        mActionType = ACTION_TYPE_CROP;
    }

    private void gotoCropImage() {
        startCropImage(Uri.fromFile(new File(mPath)), ACTION_TYPE_FINISH);
        mActionType = ACTION_TYPE_FINISH;
    }

    private void finishActivity(boolean isOk) {
        if (isOk) {
            Intent data = new Intent();
            data.setData(GlobalConstants.IMAGE_CAPTURE_URI);
            setResult(RESULT_OK, data);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    /**
     * 启动选择图片的activity
     *
     * @param requestCode
     */
    private void startPicturePickActivity(int requestCode) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, requestCode);
    }

    /**
     * 启动系统照相机 图片存在{@link GlobalConstants#IMAGE_CAMERA_URI}
     *
     * @param requestCode
     */
    private void startCameraActivity(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                GlobalConstants.IMAGE_CAMERA_URI);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 启动系统截图 图片存在{@link GlobalConstants#IMAGE_CAPTURE_URI}
     *
     * @param uri         （图片所在path）
     * @param requestCode
     */
    private void startCropImage(Uri uri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        if (isHeaderCrop) { //除了头像之外都是不规则截图
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
//		// outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", GlobalConstants.USER_IMAGE_SIZE_WIDTH);
            intent.putExtra("outputY", GlobalConstants.USER_IMAGE_SIZE_HEIGHT);
        }
        intent.putExtra("output", GlobalConstants.IMAGE_CAPTURE_URI);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, requestCode);
    }
}