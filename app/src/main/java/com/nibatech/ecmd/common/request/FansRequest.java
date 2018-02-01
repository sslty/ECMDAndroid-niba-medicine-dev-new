package com.nibatech.ecmd.common.request;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;


/**
 * 医生端/患者端/游客端   粉丝患者
 */
public class FansRequest extends BaseVolleyRequest {
    //粉丝患者列表请求
    public static void getOfflinePatientList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/offline_patients/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //粉丝医生的列表请求
    public static void getOfflineDoctorList(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/offline_doctors/";

        getRequest(url, null, getToken(), volleyCallback);
    }
}
