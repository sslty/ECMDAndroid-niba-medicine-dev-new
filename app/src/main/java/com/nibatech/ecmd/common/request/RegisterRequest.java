package com.nibatech.ecmd.common.request;

import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   注册
 */
public class RegisterRequest extends BaseVolleyRequest {
    private static String URL_API_EXAM = HOST_URL + "/api/cm_exam/";

    //注册请求
    public static void register(String mStrPhone, String mStrPassword,
                                String mStrId, String mStrCode,
                                final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/users/";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_CELL_PHONE, mStrPhone);
            jsonObject.put(JSON_KEY_PASSWORD, mStrPassword);
            jsonObject.put(JSON_KEY_ROLE, mStrId);
            jsonObject.put(JSON_KEY_CODE, mStrCode);
            postRequest(url, jsonObject, null, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //新建不带识别码的患者新建资料的请求
    public static void createPatientProfileOfNoCode(String mStrName, boolean boolMan,
                                                    String mStrAge, String mStrProject, Integer intCity,
                                                    final VolleyCallback volleyCallback) {
        String url = HOST_URL + "/api/project_cases/";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_FULL_NAME, mStrName);
            jsonObject.put(JSON_KEY_GENDER, boolMan ? GENDER_MAN : GENDER_WOMEN);
            jsonObject.put(JSON_KEY_AGE, Integer.valueOf(mStrAge));
            jsonObject.put(JSON_KEY_CITY, intCity);
            jsonObject.put(JSON_KEY_PROJECT_NAME, mStrProject);
            postRequest(url, jsonObject, getToken(), volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //注册回答问题环节，请求问题
    public static void getExamList(final VolleyCallback volleyCallback) {
        String url = URL_API_EXAM + "questions/";

        getRequest(url, null, null, volleyCallback);
    }

    //注册回答问题环节，请求是否还有答题机会
    public static void getAllowExam(String phone, final VolleyCallback volleyCallback) {
        String url = URL_API_EXAM + "allow_answer";
        String key = "?" + JSON_KEY_CELL_PHONE + "=" + phone;

        getRequest(url + key, null, null, volleyCallback);
    }

    //注册回答问题环节，请求验证是否回答正确
    public static void putSubmitAnswers(String phone, JSONArray answers, final VolleyCallback volleyCallback) {
        String url = URL_API_EXAM + "answer";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_CELL_PHONE, phone);
            jsonObject.put(JSON_KEY_ANSWERS, answers);
            put(url, jsonObject, volleyCallback);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
