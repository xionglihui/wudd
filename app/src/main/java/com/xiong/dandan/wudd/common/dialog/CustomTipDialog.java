package com.xiong.dandan.wudd.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.xiong.dandan.wudd.R;


public class CustomTipDialog extends Dialog {

    public CustomTipDialog(Context context) {
        super(context);
    }

    public CustomTipDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String message2;
        private String positiveButtonText;
        private String negativeButtonText;

        private OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public String getMessage2() {
            return message2;
        }

        public void setMessage2(String message2) {
            this.message2 = message2;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomTipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomTipDialog dialog = new CustomTipDialog(context,
                    R.style.Dialog);
            View layout = inflater.inflate(R.layout.view_common_tip_dialog,
                    null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            if (title != null)
                ((TextView) layout.findViewById(R.id.common_tip_dialog_title))
                        .setText(title);
            else
                ((TextView) layout.findViewById(R.id.common_tip_dialog_title))
                        .setText(context.getString(R.string.dialog_common_title_text));
            // set the confirm button
            Button positive = (Button) layout
                    .findViewById(R.id.common_tip_dialog_positive_button);
            if (positiveButtonText != null) {
                positive.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    positive.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                positive.setVisibility(View.GONE);
            }
            // set the cancel button
            Button cancel = (Button) layout
                    .findViewById(R.id.common_tip_dialog_cancle_button);
            if (negativeButtonText != null) {
                cancel.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    cancel.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                } else {
                    cancel.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            } else {
                cancel.setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout
                        .findViewById(R.id.common_tip_dialog_message_1))
                        .setText(message);
            } else {
                ((TextView) layout
                        .findViewById(R.id.common_tip_dialog_message_1))
                        .setVisibility(View.GONE);
            }

            if (message2 != null) {
                ((TextView) layout
                        .findViewById(R.id.common_tip_dialog_message_2))
                        .setText(message2);
            } else {
                ((TextView) layout
                        .findViewById(R.id.common_tip_dialog_message_2))
                        .setVisibility(View.GONE);
            }

            dialog.setContentView(layout);
            return dialog;
        }
    }
}
