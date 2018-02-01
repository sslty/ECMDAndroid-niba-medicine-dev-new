package com.nibatech.ecmd.common.request;

import android.content.Context;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   项目识别码
 */
public class ProjectRequest extends BaseVolleyRequest {
    //获取未使用的识别码请求
    public static void getProjectCodeUnUsed(final VolleyCallback volleyCallback) {
        String mStrKey = "?" + JSON_KEY_QUERY_TYPE + "=";
        String mStrValue = "unused";
        String url = HOST_URL + "/api/project_codes/";

        getRequest(url + mStrKey + mStrValue, null, getToken(), volleyCallback);
    }

    //获取已使用过的识别码请求
    public static void getProjectCodeAssigned(final VolleyCallback volleyCallback) {
        String mStrKey = "?" + JSON_KEY_QUERY_TYPE + "=";
        String mStrValue = "assigned";
        String url = HOST_URL + "/api/project_codes/";

        getRequest(url + mStrKey + mStrValue, null, getToken(), volleyCallback);
    }

    //生成项目识别码请求
    public static void generateProjectCode(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/project_codes/";

        postRequest(url, null, getToken(), volleyCallback);
    }

    //使用识别码请求
    public static void putSendProjectCode(Context context, String mStrCode, final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/consume_project_code";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_PROJECT_CODE, mStrCode);
            put(context, url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //我要参加项目请求
    public static void joinProject(String url, String mStrContent, final VolleyCallback volleyCallback) {
        //String url = HOST_URL + "/api/applications/";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_INTRODUCTION, mStrContent);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //患者用户绑定识别码请求
    public static void putBindProjectCode(Context context, String mStrCode, final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/bind_project_code";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_PROJECT_CODE, mStrCode);
            put(context, url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
