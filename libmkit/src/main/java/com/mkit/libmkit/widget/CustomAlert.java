package com.mkit.libmkit.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mkit.libmkit.R;


public class CustomAlert {
    Context mContext;
    AlertDialog mAlertDialog;
    TextView mTitle;
    TextView mMessage;
    Button mConfirm, mCancel;

    public CustomAlert(Context mContext, boolean showTitle) {
        this.mContext = mContext;
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        if (showTitle) {
            window.setContentView(R.layout.custom_alert);
            mTitle = (TextView) window.findViewById(R.id.title);
        } else {
            window.setContentView(R.layout.custom_msg_alert);
        }
        mMessage = (TextView) window.findViewById(R.id.message);
        mConfirm = (Button) window.findViewById(R.id.btn_confirm);
        mCancel = (Button) window.findViewById(R.id.btn_cancel);
    }

    public void setTitle(int resId) {
        mTitle.setText(resId);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setMessage(int resId) {
        mMessage.setText(resId);
    }

    public void setMessage(String message) {
        mMessage.setText(message);
    }

    /**
     * 确定按钮
     *
     * @param text
     * @param listener
     */
    public void setPositive(String text, final View.OnClickListener listener) {
        mConfirm.setText(text);
        mConfirm.setOnClickListener(listener);
    }

    /**
     * 取消按钮
     *
     * @param text
     * @param listener
     */
    public void setNegative(String text, final View.OnClickListener listener) {
        mCancel.setText(text);
        mCancel.setOnClickListener(listener);
    }

    /**
     * 关闭对话框
     */
    public void dismiss() {
        mAlertDialog.dismiss();
    }
}
