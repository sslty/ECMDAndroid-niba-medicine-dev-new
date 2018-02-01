package com.nibatech.ecmd.common.request;


import com.android.volley.Request;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;


/**
 * 医生端/患者端/游客端   病例详情
 */
public class CasesRequest extends BaseVolleyRequest {
    //获得项目病例详情列表请求
    public static void getProjectCaseRecordList(String url, final VolleyCallback volleyCallback) {
        String mStrKey = "/data/";

        getRequest(url + mStrKey, null, getToken(), volleyCallback);
    }

    //病人项目详细病例记录请求
    public static void deleteProjectCaseRecordItem(String url, final VolleyCallback volleyCallback) {
        int method = Request.Method.DELETE;

        createRequestVolley(method, url, null, getToken(), volleyCallback);
    }
}
