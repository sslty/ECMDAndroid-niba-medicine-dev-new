package com.nibatech.ecmd.common.request;


import android.content.Context;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 医生端/患者端/游客端   mien
 */
public class MienRequest extends BaseVolleyRequest {
    private static final String DOCTOR_ARTICLE = HOST_URL + "/api/doctor_articles/";

    //医生端：moment
    public static void getChineseMedicineMomentList(final VolleyCallback volleyCallback) {
        String url = DOCTOR_ARTICLE;

        getRequestListForPage(url, null, getToken(), volleyCallback);
    }

    //医生端：mien
    public static void getMienList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/cms_articles/";

        getRequestListForPage(url, null, null, volleyCallback);
    }

    //医生端：新建发表文章
    public static void postCreateArticle(final VolleyCallback volleyCallback) {
        String url = DOCTOR_ARTICLE + "create_draft";

        postRequest(url, null, getToken(), volleyCallback);
    }

    //医生端：发表文章，包括标题，内容，图片，也可以没有图片
    public static void putPublishArticle(Context context, String url, String title, String content,
                                         JSONArray imagesUrl, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_TITLE, title);
            jsonObject.put(JSON_KEY_CONTENT, content);

            if (imagesUrl != null) {
                jsonObject.put(JSON_KEY_IMAGE_URL, imagesUrl);
            }

            if (context != null) {
                put(context, url, jsonObject, volleyCallback);
            } else {
                put(url, jsonObject, volleyCallback);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //医生端：发表评论
    public static void postComment(String url, String comment, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_COMMENT, comment);

            if (comment != null) {
                jsonObject.put(JSON_KEY_IMAGE_URL, comment);
            }

            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //患者端，encyclopedia
    public static void getEncyclopediaList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/cms_encyclopaedics/";

        getRequestListForPage(url, null, null, volleyCallback);
    }
}
