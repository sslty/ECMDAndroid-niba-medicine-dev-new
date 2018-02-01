package com.nibatech.ecmd.common.request.callback;


import android.graphics.Bitmap;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public abstract class VolleyCallback {

    public abstract void onSuccessResponse(JSONObject success);

    public void onErrorResponse(VolleyError error) {
    }

    public void onSuccessImage(Bitmap bitmap) {
    }
}
