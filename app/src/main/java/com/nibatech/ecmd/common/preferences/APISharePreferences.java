package com.nibatech.ecmd.common.preferences;


import android.content.Context;

import com.google.gson.Gson;
import com.nibatech.ecmd.common.bean.common.APIsBean;

import static com.j256.ormlite.field.DatabaseField.DEFAULT_STRING;

/**
 * 医生端／患者端／游客端   首页上的api url
 */
public class APISharePreferences extends BaseSharedPreferences {
    private static final String KEY_APIS = "key.apis";

    public static void save(Context context, APIsBean apIsBean) {
        put(context, KEY_APIS, new Gson().toJson(apIsBean));
    }

    public static APIsBean get(Context context) {
        String string = (String) get(context, KEY_APIS, DEFAULT_STRING);
        APIsBean apIsBean = null;
        if (!(string != null && string.isEmpty())) {
            try {
                apIsBean = new Gson().fromJson(string, APIsBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return apIsBean;
    }
}
