package com.nibatech.ecmd.common.exception;


import android.content.Context;
import android.widget.Toast;

import com.nibatech.ecmd.application.ECMDApplication;

public class CustomCrash implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private static CustomCrash instance;

    // 单例模式中获取唯一的ExitApplication实例
    public static CustomCrash getInstance() {
        if (null == instance) {
            instance = new CustomCrash();
        }
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // 3,应用准备退出
        Toast.makeText(mContext, "很抱歉,程序发生异常,即将退出.", Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ECMDApplication.ExitApp.getInstance().exitAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public void setCustomCrashInfo(Context pContext) {
        this.mContext = pContext;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
