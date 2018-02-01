package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   我的
 */
public class PersonalRequest extends BaseVolleyRequest {

    //上传头像
    public static void putUpdateAvatar(String avatarUrl, final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/user/update_avatar";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_AVATAR_URL, avatarUrl);
            put(url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
