package com.nibatech.ecmd.utils;

import android.os.Message;


public abstract class  MyAsycTask {
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            afterTask();
        }
    };

    public abstract void preTask();

    public abstract void afterTask();

    public abstract void doInBack();

    public void execute() {
        preTask();
        new Thread() {
            @Override
            public void run() {

                doInBack();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
}
