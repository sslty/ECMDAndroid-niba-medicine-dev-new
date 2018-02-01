package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 医生端/患者端/游客端   有奖分析
 */
public class PrizeRequest extends BaseVolleyRequest{
    private static final String API = "/api/case_games/";

    //本期竞猜数据
    public static void getCurrentPeriod(final VolleyCallback volleyCallback) {
        String url = HOST_URL + API + "current";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //历史竞猜数据
    public static void getHistoryPeriod(final VolleyCallback volleyCallback) {
        String url = HOST_URL + API + "history/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //提交疗效预判答案
    public static void postAnswerOnEffect(String url, String answer, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_ANSWER, answer);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
