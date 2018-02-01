package com.nibatech.ecmd.common.request;


import android.content.Context;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   聊天类
 */
public class ChatRequest extends BaseVolleyRequest {
    //医生端，发给患者资料的请求
    public static void sendMaterialToPatient(String url, JSONArray questions,
                                             String description, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_QUESTIONS, questions);
            jsonObject.put(JSON_KEY_IMAGE_DESCRIPTION, description);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //医生端，请求发送指导意见
    public static void getGuideSuggestion(String url, String guide, String imageUrl, int nextTime,
                                          final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_GUIDE, guide);
            jsonObject.put(JSON_KEY_GUIDE_IMAGE_URL, imageUrl);
            jsonObject.put(JSON_KEY_NEXT_GUIDE_TIME, nextTime);
            put(url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //患者端，补充资料图片发送成功后返回的url再次请求
    public static void putSupplyMaterialToDoctor(String url, JSONArray answers,
                                                 JSONArray imagesUrl, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_ANSWERS, answers);
            jsonObject.put(JSON_KEY_IMAGE_URL, imagesUrl);
            put(url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //给服务器发消息
    public static void postMessageToHost(String url, String content, String type,
                                         final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_MESSAGE, content);
            jsonObject.put(JSON_KEY_MESSAGE_TYPE, type);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //向服务器获取消息
    public static void getMessageFromHost(String url, final VolleyCallback volleyCallback) {
        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端：在结束指导后，给医生评价
    public static void putJudgeEndGuide(Context context, String url, int effectId, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_EFFECT_ID, effectId);
            put(context, url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
