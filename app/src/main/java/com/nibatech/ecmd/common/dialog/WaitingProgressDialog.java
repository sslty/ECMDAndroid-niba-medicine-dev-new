package com.nibatech.ecmd.common.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibatech.ecmd.R;

/**
 * WaitingProgressDialog
 * 参数：
 * 功能：
 * 等待框
 */
public class WaitingProgressDialog extends Dialog {
    private Context context = null;
    private static WaitingProgressDialog customProgressDialog = null;

    public WaitingProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public WaitingProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * createDialog
     * 参数：
     * Context context
     * 功能：
     * 新建对话框
     */
    public static WaitingProgressDialog createDialog(Context context) {
        customProgressDialog = new WaitingProgressDialog(context, R.style.WaitingProgressDialog);
        customProgressDialog.setContentView(R.layout.waiting_progress_dialog);
        //noinspection ConstantConditions
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        return customProgressDialog;
    }

    /**
     * onWindowFocusChanged
     * 参数：
     * boolean hasFocus
     * 功能：
     * 焦点改变
     */
    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loading_image_view);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * setTitle
     * 参数：
     * String strTitle
     * 功能：
     * 设置标题
     */
    public WaitingProgressDialog setTitle(String strTitle) {
        return customProgressDialog;
    }

    /**
     * setMessage
     * 参数：
     * String strMessage
     * 功能：
     * 设置消息
     */
    public WaitingProgressDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loading_msg);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }
}
