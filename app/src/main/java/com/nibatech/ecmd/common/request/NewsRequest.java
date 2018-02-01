package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

/**
 * 医生端/患者端/游客端   news
 */
public class NewsRequest extends BaseVolleyRequest{

    //患者端，news
    public static void getNewsList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/plat_dynamics/";

        getRequestListForPage(url, null, null, volleyCallback);
    }
}
