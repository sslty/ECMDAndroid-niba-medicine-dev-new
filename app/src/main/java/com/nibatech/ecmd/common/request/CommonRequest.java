package com.nibatech.ecmd.common.request;


import android.content.Context;
import android.util.Log;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.SaveFile;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   公共类
 */
public class CommonRequest extends BaseVolleyRequest {
    //请求app首页上所有的api
    public static void getAPIs(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/get_apis/";

        getRequest(url, null, null, volleyCallback);
    }

    //发送验证码请求
    public static void sendCode(String mStrPhone, final VolleyCallback volleyCallback) {
        String mStrKey = "?" + JSON_KEY_CELL_PHONE + "=";
        String url = HOST_URL + "/api/verify_code";

        getRequest(url + mStrKey + mStrPhone, null, null, volleyCallback);
    }

    // 修改密码
    public static void putModifyPassword(String mStrPhone, String mStrPassword, String mStrCode,
                                         final VolleyCallback volleyCallback) {
        Log.d(SaveFile.DEBUG_TAG, "modify password");
        String url = HOST_URL + "/api/password";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_CELL_PHONE, mStrPhone);
            jsonObject.put(JSON_KEY_PASSWORD, mStrPassword);
            jsonObject.put(JSON_KEY_CODE, mStrCode);
            put(url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //得到所有城市列表请求
    public static void getCity(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/cities/";
        getRequest(url, null, null, volleyCallback);
    }

    //得到所有专业列表请求
    public static void getSpecialism(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/specialisms/";

        getRequest(url, null, null, volleyCallback);
    }

    //获取医生所参与的项目名称
    public static void getProject(final VolleyCallback volleyCallback) {
        String mStrKey = "?" + JSON_KEY_QUERY_TYPE + "=" + "unused";
        String url = HOST_URL + "/api/projects/";

        getRequest(url + mStrKey, null, getToken(), volleyCallback);
    }

    //得到所有疗效名称请求
    public static void getEffectName(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/case_game_answers/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //得到首页上的图片banner
    public static void getBanner(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/banners/";

        getRequest(url, null, null, volleyCallback);
    }

    //得到评论列表的更多内容
    public static void getCommonMoreList(String url, final VolleyCallback volleyCallback) {
        getRequestListForPage(url, null, getToken(), volleyCallback);
    }

    //得到评论列表的更多内容
    public static void getCommonMoreList(String url, int perPage, final VolleyCallback volleyCallback) {
        getRequestListForPage(url, perPage, null, getToken(), volleyCallback);
    }


    //登录请求
    public static void login(String mStrPhone, String mStrPassword, final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/auth/login";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_CELL_PHONE, mStrPhone);
            jsonObject.put(JSON_KEY_PASSWORD, mStrPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        postRequest(url, jsonObject, null, volleyCallback);
    }

    //向服务器请求保存资料
    public static void putSaveUserProfile(String strUrl, String strName, Boolean blMan,
                                          String strAge, Integer intSpecialism, Integer intCity,
                                          String strHospital, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            //姓名
            if (strName != null && !strName.isEmpty()) {
                jsonObject.put(JSON_KEY_FULL_NAME, strName);
            }
            //性别
            if (blMan != null) {
                jsonObject.put(JSON_KEY_GENDER, blMan ? GENDER_MAN : GENDER_WOMEN);
            }
            //年龄
            if (strAge != null && !strAge.isEmpty()) {
                jsonObject.put(JSON_KEY_AGE, Integer.valueOf(strAge));
            }
            //专业
            if (intSpecialism != null) {
                jsonObject.put(JSON_KEY_SPECIALISM, intSpecialism);
            }
            //城市
            if (intCity != null) {
                jsonObject.put(JSON_KEY_CITY, intCity);
            }
            //医院
            if (strHospital != null && !strHospital.isEmpty()) {
                jsonObject.put(JSON_KEY_HOSPITAL, strHospital);
            }
            put(strUrl, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //根据url请求各类数据, 需要登录
    public static void getUrlData(String url, final VolleyCallback volleyCallback) {
        getRequest(url, null, getToken(), volleyCallback);
    }

    //根据url修改数据
    public static void putUrlData(Context context, String url, final VolleyCallback volleyCallback) {
        put(context, url, null, volleyCallback);
    }

    //根据url新建数据
    public static void postUrlData(String url, final VolleyCallback volleyCallback) {
        postRequest(url, null, getToken(), volleyCallback);
    }

    //根据url请求各类数据, 需要登录，可以分页，可以上拉刷新，下拉加载
    public static void getUrlDataForPage(String url, final VolleyCallback volleyCallback) {
        getRequestListForPage(url, null, getToken(), volleyCallback);
    }
}
