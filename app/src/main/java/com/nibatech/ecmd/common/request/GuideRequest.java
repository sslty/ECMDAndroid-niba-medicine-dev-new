package com.nibatech.ecmd.common.request;

import android.content.Context;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   guide
 */
public class GuideRequest extends BaseVolleyRequest {
    private static final String URL_PATIENT_ONLINE_TREATMENTS = HOST_URL + "/api/patient_online_treatments/";
    private static final String URL_DOCTOR_ONLINE_TREATMENTS = HOST_URL + "/api/doctor_online_treatments/";
    private static final String URL_TOURIST_ONLINE_TREATMENTS = HOST_URL + "/api/tourist_online_treatments/";

    //患者端, 我的挂单列表
    public static void getPatientOrderListOfMine(final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "my_unaccepted";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端, 其他人的挂单列表
    public static void getPatientOrderListOfOthers(final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "others_unaccepted/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端, 我的进行中挂单列表
    public static void getPatientOngoingListOfMine(final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "my_treating";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端，其他人进行中的挂单列表
    public static void getPatientOngoingListOfOthers(final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "others_treating/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端，判断是否挂单，挂单成功后是否在进行中
    public static void getPatientOrderState(final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "my_unfinished";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //患者端，再一次挂单
    public static void putAgainOrder(Context context, final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "submit_again";

        put(context, url, null, volleyCallback);
    }

    //患者端，取消订单
    public static void putCancelOrder(Context context, final VolleyCallback volleyCallback) {
        String url = URL_PATIENT_ONLINE_TREATMENTS + "cancel";

        put(context, url, null, volleyCallback);
    }

    //医生端，获取挂单的病人列表
    public static void getDoctorOrderList(final VolleyCallback volleyCallback) {
        String url = URL_DOCTOR_ONLINE_TREATMENTS;

        getRequest(url, null, getToken(), volleyCallback);
    }

    //医生端，获取求高手的病人列表
    public static void getDoctorDifficultList(final VolleyCallback volleyCallback) {
        String url = URL_DOCTOR_ONLINE_TREATMENTS + "difficulties/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //医生端，获取进行中的病人列表
    public static void getDoctorOngoingList(final VolleyCallback volleyCallback) {
        String url = URL_DOCTOR_ONLINE_TREATMENTS + "treatings/";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //医生端，我要接单
    public static void postAcceptPatientOrder(String url, String viewPoint, String expectation,
                                              int expense, boolean free, final VolleyCallback volleyCallback) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_VIEW_POINT, viewPoint);
            jsonObject.put(JSON_KEY_EXPECTATION, expectation);
            jsonObject.put(JSON_KEY_EXPENSE, expense);
            jsonObject.put(JSON_KEY_FREE_FIRST_TIME, free);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //医生端，我的患者，中医指导的病人列表
    public static void getPatientListOnMyPatient(final VolleyCallback volleyCallback) {
        String url = URL_DOCTOR_ONLINE_TREATMENTS + "my_treatings";

        getRequest(url, null, getToken(), volleyCallback);
    }

    //游客端，查看所有人挂单信息
    public static void getTouristOrderListOfAll(final VolleyCallback volleyCallback) {
        String url = URL_TOURIST_ONLINE_TREATMENTS + "unaccepted";

        getRequest(url, null, null, volleyCallback);
    }

    //游客端，查看所有人进行中信息
    public static void getTouristOngoingListOfAll(final VolleyCallback volleyCallback) {
        String url = URL_TOURIST_ONLINE_TREATMENTS + "treating";

        getRequest(url, null, null, volleyCallback);
    }

    public static void createOrder(final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/test_wxpay/create_order";

        postRequest(url, null, getToken(), volleyCallback);
    }
}