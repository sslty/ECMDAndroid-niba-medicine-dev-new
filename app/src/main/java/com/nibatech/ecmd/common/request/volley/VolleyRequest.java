package com.nibatech.ecmd.common.request.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public abstract class VolleyRequest {
    protected VolleyRequest(int method, String url, JSONObject jsonObject, final String tokens) {
        if (url != null) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    method,
                    url,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject volleySuccess) {
                            onSuccess(volleySuccess);//成功
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            UIUtils.connectToHostShowError(UIUtils.getContext(), volleyError);
                            onError(volleyError);//错误回调
                        }
                    }
            ) {//设置头信息
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Accept", "application/json");
                    headers.put("Content-Type", "application/json");

                    if (tokens != null) {
                        headers.put("Authorization", "jwt " + tokens);
                    }

                    return headers;
                }
            };

            //请求前先清除队列中其它请求
            ECMDApplication.getRequestQueue().add(jsonObjectRequest);
        }
    }

    protected abstract void onSuccess(JSONObject volleySuccess);

    protected abstract void onError(VolleyError volleyError);
}
