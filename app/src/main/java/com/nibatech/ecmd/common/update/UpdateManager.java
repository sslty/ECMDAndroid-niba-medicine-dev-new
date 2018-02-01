package com.nibatech.ecmd.common.update;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class UpdateManager {
    // 应用程序Context
    private Context mContext;
    private static final String savePath = Environment.getExternalStorageDirectory() + "/ecmd/";// 保存apk的文件夹
    private static final String saveFileName = savePath + "ecmd.apk";
    // 进度条与通知UI刷新的handler和msg常量
    private ProgressBar mProgress;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private int progress;// 当前进度
    private boolean interceptFlag = false;// 用户取消下载
    // 通知处理刷新界面的handler
    private Handler mHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    installApk();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    // 显示更新程序对话框，供主程序调用
    public boolean checkUpdateInfo() {
        boolean ok = false;

        int currentVersion = 1;
        int httpVersion = 1;
        if (currentVersion != httpVersion) {
            ok = true;
        }

        return ok;
    }

    public void needToUpdate() {
        showNoticeDialog();
    }

    private void showNoticeDialog() {
        AlertDialogBuilder.onCreate(mContext,
                "软件版本更新",
                "有最新的软件包，请下载！",
                "下载",
                "以后再说",
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        showDownloadDialog();
                    }

                    @Override
                    public void selectNegative() {
                        System.exit(0);
                    }
                });
    }

    private void showDownloadDialog() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.activity_update, null);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);

        AlertDialogBuilder.onCreate(mContext, view,
                "软件版本更新",
                "",
                false, new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        System.exit(0);
                        interceptFlag = true;
                    }
                });
        downloadApk();
    }

    private void downloadApk() {
        Thread downLoadThread = new Thread(downRunnable);
        downLoadThread.start();
    }

    private void installApk() {
        File file = new File(saveFileName);
        if (!file.exists()) {
            return;
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.fromFile(new File(saveFileName)), "application/vnd.android.package-archive");
        mContext.startActivity(i);
        System.exit(0);
    }

    private Runnable downRunnable = new Runnable() {
        @Override
        public void run() {
            URL url;
            try {
                String apkUrl = "http://softfile.3g.qq.com:8080/msoft/179/24659/43549/qq_hd_mini_1.4.apk";
                url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream ins = conn.getInputStream();
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream outStream = new FileOutputStream(ApkFile);
                int count = 0;
                byte buf[] = new byte[1024];
                if (conn.getResponseCode() >= 400) {
                    Toast.makeText(mContext, "连接超时", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    do {
                        int number = ins.read(buf);
                        count += number;
                        progress = (int) (((float) count / length) * 100);
                        // 下载进度
                        mHandler.sendEmptyMessage(DOWN_UPDATE);
                        if (number <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(DOWN_OVER);
                            break;
                        }
                        outStream.write(buf, 0, number);
                    } while (!interceptFlag);// 点击取消停止下载
                }

                outStream.close();
                ins.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
