package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MineRequest extends BaseVolleyRequest {

    //上传医师证明
    public static void postCertify(String url, JSONArray jsonArray, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_EXT_INFO, jsonArray);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
