package com.nibatech.ecmd.common.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import com.nibatech.ecmd.common.bean.common.APIsBean;
import com.nibatech.ecmd.common.preferences.APISharePreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;


public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("网络状态发生变化");
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean connected = true;
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Log.d("tag", "WIFI已连接,移动数据已连接");
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Log.d("tag", "WIFI已连接,移动数据已断开");
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Log.d("tag", "WIFI已断开,移动数据已连接");
            } else {
                Log.d("tag", "WIFI已断开,移动数据已断开");
                connected = false;
            }

            getAppAPIs(context, connected);
        } else {
            //这里的就不写了，前面有写，大同小异
            System.out.println("API level 大于21");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (Network network : networks) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
                sb.append(networkInfo.getTypeName()).append(" connect is ").append(networkInfo.isConnected());
                Log.d("tag", sb.toString());
                getAppAPIs(context, networkInfo.isConnected());
            }
        }
    }

    //判断有网络时候就去获取api
    private void getAppAPIs(final Context context, boolean connected) {
        if (connected && APISharePreferences.get(context) == null) {
            CommonRequest.getAPIs(new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    getAppAPIsSuccess(context, success.toString());
                }
            });
        }
    }

    private void getAppAPIsSuccess(Context context, String json) {
        APIsBean apIsBean = UIUtils.fromJson(json, APIsBean.class);

        APISharePreferences.save(context, apIsBean);
    }
}
