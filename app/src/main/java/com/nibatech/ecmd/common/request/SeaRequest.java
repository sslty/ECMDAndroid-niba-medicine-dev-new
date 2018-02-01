package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;


/**
 * 医生端/患者端/游客端   sea
 */
public class SeaRequest extends BaseVolleyRequest {

    //请求list
    public static void getSeaList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/medicine_legacy/";

        getRequestListForPage(url, null, null, volleyCallback);
    }
}
