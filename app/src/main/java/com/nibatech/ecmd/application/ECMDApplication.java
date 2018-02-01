package com.nibatech.ecmd.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nibatech.ecmd.common.bean.login.LoginBean;
import com.nibatech.ecmd.common.exception.CustomCrash;
import com.nibatech.ecmd.config.SaveFile;
import com.tencent.TIMManager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class ECMDApplication extends Application {
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    private static RequestQueue requestQueue;
    private static OkHttpClient okHttpClient;
    private LoginBean loginBean;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
        initRealm(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
        context = getApplicationContext();
        mainThreadId = Process.myTid();

        //网络队列请求
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        //okHttp服务器
        okHttpClient = initOkHttpClient();
        //初始化阿里云推送通道
        initCloudChannel(this);
        //初始化腾讯im
        initTM();
        //初始化当前数据库
//        initRealm(context);//已经在attachBaseContext()调用，因为这里classloader发现不了realm这个类

        //初始化异常捕获
        CustomCrash mCustomCrash = CustomCrash.getInstance();
        mCustomCrash.setCustomCrashInfo(this);
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Context getContext() {
        return context;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public static class ExitApp {
        private List<Activity> activityList = new LinkedList<>();
        private static ExitApp instance;

        private ExitApp() {
        }

        // 单例模式中获取唯一的ExitApplication实例
        public static ExitApp getInstance() {
            if (null == instance) {
                instance = new ExitApp();
            }
            return instance;
        }

        // 添加Activity到容器中
        public void addOneActivity(Activity activity) {
            activityList.add(activity);
        }

        // 删除当前的Activity从容器中
        public void deleteOneActivity(Activity activity) {
            activityList.remove(activityList.size() - 1);
        }

        // 遍历所有Activity并finish()
        public void exitAllActivity() {
            for (Activity activity : activityList) {
                activity.finish();
            }
        }
    }

    private OkHttpClient initOkHttpClient() {
        File file = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cache(new Cache(file.getAbsoluteFile(), cacheSize));
        okHttpClient = builder.build();

        return okHttpClient;
    }

    private void initTM() {
        TIMManager.getInstance().init(context);
    }

    private void initCloudChannel(Context applicationContext) {
        // 注册方法会自动判断是否支持小米系统推送，如不支持会跳过注册。
        //MiPushRegister.register(applicationContext, "小米AppID", "小米AppKey");
        // 注册方法会自动判断是否支持华为系统推送，如不支持会跳过注册。
        //HuaWeiRegister.register(applicationContext);

        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(SaveFile.DEBUG_TAG, "init cloud-channel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(SaveFile.DEBUG_TAG, "init cloud-channel failed -- error-code:" + errorCode +
                        " -- error-message:" + errorMessage);
            }
        });
    }

    private void initRealm(Context context) {
        Realm.init(context);

        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }


}
