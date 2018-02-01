package com.nibatech.ecmd.common.preferences;


import android.content.Context;

import com.google.gson.Gson;
import com.nibatech.ecmd.common.bean.login.LoginPhoneBean;


/**
 * 医生端／患者端／游客端   登录信息
 */
public class LoginSharedPreferences extends BaseSharedPreferences {
    private static final String KEY_LOGIN = "key.login";
    private static final String DEFAULT_STRING = "";

    public static void save(Context context, String phone, String password) {
        LoginPhoneBean login = new LoginPhoneBean();
        login.setPhone(phone);
        login.setPassword(password);
        put(context, KEY_LOGIN, new Gson().toJson(login));
    }

    public static LoginPhoneBean get(Context context) {
        String string = (String) get(context, KEY_LOGIN, DEFAULT_STRING);
        LoginPhoneBean login = null;
        if (!(string != null && string.isEmpty())) {
            try {
                login = new Gson().fromJson(string, LoginPhoneBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return login;
    }

    public static void remove(Context context) {
        clear(context);
    }
}
