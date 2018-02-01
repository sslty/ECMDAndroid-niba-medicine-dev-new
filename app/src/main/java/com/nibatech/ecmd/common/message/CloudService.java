package com.nibatech.ecmd.common.message;


import android.util.Log;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.nibatech.ecmd.config.SaveFile;

public class CloudService {
    private static String[] strArray;


    private static void setTagArray(String mStrTag) {
        strArray = new String[2];
        strArray[0] = mStrTag;
    }

    private static String[] getTagArray() {
        return strArray;
    }

    public static void bindAccountToALiYun(final String mStrAccount) {
        PushServiceFactory.getCloudPushService().bindAccount(mStrAccount, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SaveFile.DEBUG_TAG, "@用户绑定账号 ：" + mStrAccount + " 成功");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.i(SaveFile.DEBUG_TAG, "@用户绑定账号 ：" + mStrAccount + " 失败，原因 ： " + errorMessage);
            }
        });
    }

    /**
     //target 目标类型，1：本设备； 2：本设备绑定账号； 3：别名
     //tags 标签（数组输入）
     //alias 别名（仅当target = 3时生效）
     //callback 回调
     **/
    public static void bindTagToALiYun(String mStrTag)
    {
        setTagArray(mStrTag);
        PushServiceFactory.getCloudPushService().bindTag(2, getTagArray(), null, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(SaveFile.DEBUG_TAG, "绑定标签到账号成功.");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(SaveFile.DEBUG_TAG, "绑定标签到账号失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
            }
        });
    }

    public static void unbindAccountToALiYun() {
        PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(SaveFile.DEBUG_TAG, "解除绑定账号成功");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d(SaveFile.DEBUG_TAG, "解除绑定账号失败，原因 ： " + errorMessage);
            }
        });
    }

    /**
     //target 目标类型，1：本设备； 2：本设备绑定账号； 3：别名
     //tags 标签（数组输入）
     //alias 别名（仅当target = 3时生效）
     //callback 回调
     **/
    public static void unbindTagToALiYun() {
        PushServiceFactory.getCloudPushService().unbindTag(1, getTagArray(), null, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(SaveFile.DEBUG_TAG, "解绑设备标签成功.");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(SaveFile.DEBUG_TAG, "解绑设备标签失败，errorCode: " + errorCode + ", errorMessage：" + errorMessage);
            }
        });
    }

}
