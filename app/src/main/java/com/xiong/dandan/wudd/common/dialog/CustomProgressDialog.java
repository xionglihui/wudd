package com.xiong.dandan.wudd.common.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.xiong.dandan.wudd.R;


public class CustomProgressDialog extends ProgressDialog {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_common_progress_dialog);
	}

	public CustomProgressDialog(Context context,
			boolean canceledOnTouchOutside, boolean cancelable) {
		super(context, R.style.Dialog);
		setCanceledOnTouchOutside(canceledOnTouchOutside);
		setCancelable(cancelable);
	}
}
