package com.nibatech.ecmd.common.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class AlertDialogBuilder {
    /**
     * onCreate
     * 参数：
     * final Context context 上下文
     * final View view
     * String title
     * String message
     * String positive
     * boolean negative
     * final OnAlertDialogListener onAlertDialogListener
     * 功能：
     * 弹出对话框
     */
    public static void onCreate(final Context context,
                                String title, String message,
                                boolean negative,
                                final AlertDialogListener alertDialogListener) {
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        dialog.dismiss();
                        alertDialogListener.selectPositive();
                        break;
                }
            }
        };

        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setTitle(title); //设置标题
        builder.setMessage(message); //设置内容
        builder.setPositiveButton("确认", listener);//确认
        builder.setCancelable(negative);//设置点其他空白地方是否取消 false=不取消
        //显示
        builder.create().show();
    }

    public static void onCreate(final Context context,
                                String title, String message, String positive, String negative,
                                final AlertDialogListener alertDialogListener) {
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        dialog.dismiss();
                        alertDialogListener.selectPositive();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        alertDialogListener.selectNegative();
                        break;
                }
            }
        };

        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setTitle(title); //设置标题
        builder.setMessage(message); //设置内容
        builder.setPositiveButton(positive, listener);//确认
        builder.setNegativeButton(negative, listener);//取消
        builder.create().show();
    }

    public static void onCreate(final Context context, View view,
                                String title, String message,
                                boolean negative,
                                final AlertDialogListener alertDialogListener) {
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        dialog.dismiss();
                        alertDialogListener.selectPositive();
                        break;
                }
            }
        };

        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //先得到构造器
        builder.setView(view);// 设置对话框的内容为一个View
        builder.setTitle(title); //设置标题
        builder.setMessage(message); //设置内容
        builder.setPositiveButton("取消", listener);//确认
        builder.setCancelable(negative);//设置点其他空白地方是否取消 false=不取消
        //显示
        builder.create().show();
    }
}
