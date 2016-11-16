package com.xiong.dandan.wudd.common.base;

import android.app.Application;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiong.dandan.wudd.R;



public class BaseApplication extends Application {

    public void showToast(String message) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.bg_error_toast);
        textView.setTextColor(getResources().getColor(R.color.white));

        Toast toast = new Toast(this);
        toast.setView(textView);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void showToast(int id) {
        showToast(getString(id));
    }

    public void showToastWithPicture(int pictureResourceId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(pictureResourceId);
        Toast toast = new Toast(this);
        toast.setView(imageView);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
