package com.nibatech.ecmd.common.request.base;


import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.common.bean.login.LoginBean;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.dialog.WaitingProgressDialog;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.common.request.volley.VolleyNetworkImage;
import com.nibatech.ecmd.common.request.volley.VolleyRequest;
import com.nibatech.ecmd.config.SaveFile;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class BaseVolleyRequest {

    //public static final String HOST_URL = "http://60.205.141.66:5000";
    public static final String HOST_URL = "http://139.217.8.207:5000";
    //public static final String HOST_URL = "http://192.168.31.123:5000";

    protected static final String JSON_KEY_CELL_PHONE = "cellphone";
    protected static final String JSON_KEY_PASSWORD = "password";
    protected static final String JSON_KEY_CODE = "code";
    protected static final String JSON_KEY_ROLE = "role";
    protected static final String JSON_KEY_SPECIALISM = "specialism_id";
    protected static final String JSON_KEY_CITY = "city_id";
    protected static final String JSON_KEY_PROJECT_NAME = "project_name";
    protected static final String JSON_KEY_PROJECT_CODE = "project_code";
    protected static final String JSON_KEY_HOSPITAL = "hospital";
    protected static final String JSON_KEY_INTRODUCTION = "introduction";
    protected static final String JSON_KEY_QUERY_TYPE = "query_type";
    protected static final String JSON_KEY_PROJECT_TYPE = "project_type";
    protected static final String JSON_KEY_VIEW_POINT = "viewpoint";
    protected static final String JSON_KEY_EXPECTATION = "expectation";
    protected static final String JSON_KEY_EXPENSE = "expense";
    protected static final String JSON_KEY_FREE_FIRST_TIME = "free_first_time";
    protected static final String JSON_KEY_QUESTIONS = "questions";
    protected static final String JSON_KEY_IMAGE_DESCRIPTION = "image_description";
    protected static final String JSON_KEY_ANSWERS = "answers";
    protected static final String JSON_KEY_ANSWER = "answer";
    protected static final String JSON_KEY_IMAGE_URL = "image_urls";
    protected static final String JSON_KEY_GUIDE = "guide";
    protected static final String JSON_KEY_GUIDE_IMAGE_URL = "guide_image_url";
    protected static final String JSON_KEY_NEXT_GUIDE_TIME = "next_guide_time";
    protected static final String JSON_KEY_MESSAGE = "message";
    protected static final String JSON_KEY_MESSAGE_TYPE = "msg_type";
    protected static final String JSON_KEY_AVATAR_URL = "avatar_url";
    protected static final String JSON_KEY_TITLE = "title";
    protected static final String JSON_KEY_CONTENT = "content";
    protected static final String JSON_KEY_COMMENT = "comment";
    protected static final String JSON_KEY_EFFECT_ID = "therapeutic_effect_id";
    protected static final String JSON_KEY_CD_NUMBER = "cd_number";
    protected static final String JSON_KEY_EXT_INFO = "ext_info";

    public static final String JSON_KEY_FULL_NAME = "fullname";
    public static final String JSON_KEY_GENDER = "gender_id";
    public static final String JSON_KEY_AGE = "age";

    //注册时的身份，医生或者患者
    public static final String JSON_VALUE_DOCTOR = "doctor";
    public static final String JSON_VALUE_PATIENT = "patient";

    //身份
    public static final int IDENTITY_DOCTOR = 0;//医生
    public static final int IDENTITY_PATIENT = 1;//患者
    public static final int IDENTITY_TOURIST = 2;//游客
    //性别
    public static final int GENDER_WOMEN = 1;//女
    public static final int GENDER_MAN = 2;//男
    //页数，页码
    public static int PAGES_NUMBER = 1;
    public static int PAGES_PER_PAGE_NORMAL = 5;
    public static int PAGES_PER_PAGE_FOR_COMMENT = 6;
    public static int PAGES_PER_PAGE_FOR_CHAT = 20;

    //获取全局变量
    public static ECMDApplication getECMDApplication() {
        return (ECMDApplication) UIUtils.getContext();
    }

    //获取当前的用户
    public static LoginBean getLogin() {
        return getECMDApplication().getLoginBean();
    }

    //获取当前的登录token
    public static String getToken() {
        try {
            return getLogin().getAccessToken();
        } catch (Exception e) {
            return null;
        }
    }

    public static int getIdentity() {
        LoginBean loginBean = getECMDApplication().getLoginBean();
        int id;

        if (loginBean == null) {//游客
            id = BaseVolleyRequest.IDENTITY_TOURIST;
        } else if (loginBean.getUser().getDoctor() != null) {//医生
            id = BaseVolleyRequest.IDENTITY_DOCTOR;
        } else if (loginBean.getUser().getPatient() != null) {//患者
            id = BaseVolleyRequest.IDENTITY_PATIENT;
        } else {
            id = BaseVolleyRequest.IDENTITY_TOURIST;
            Log.d(SaveFile.DEBUG_TAG, "error identity");
        }

        return id;
    }

    //获取当前性别
    public static String getGender(int id) {
        String gender;

        if (id == 1) {
            gender = "女";
        } else {
            gender = "男";
        }

        return gender;
    }

    //设置登录信息
    public static void setLogin(LoginBean login) {
        getECMDApplication().setLoginBean(login);
    }

    //设置当前用户信息
    public static void setUser(UserBean user) {
        getLogin().setUser(user);
    }

    //设置当前页码的url
    public static String getPageUrl(int num, int per) {
        String pageNum = String.format("page=%s", num);
        String perPage = String.format("per_page=%s", per);

        return "?" + pageNum + "&" + perPage;
    }

    //弹出错误信息
    public static void showError(Context context, VolleyError error) {
        if (error.networkResponse != null) {//离线状态改为跳到登录界面
            try {
                Toast.makeText(context, UIUtils.unicode2string(
                        new String(error.networkResponse.data, "UTF-8")), Toast.LENGTH_SHORT).show();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    //同步缓存请求
    public static void loadNetworkImage(Context context, String url, ImageView imageView) {
        if (url != null) {
            ImageLoader mImageLoader = VolleyNetworkImage.getInstance(context).getImageLoader();
            mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                    R.drawable.image_show_loading, R.drawable.image_show_error));
        }
    }

    //向服务器请求
    protected static void createRequestVolley(int method, String url,
                                              JSONObject jsonObject, String heads,
                                              final VolleyCallback volleyCallback) {
        new VolleyRequest(method, url, jsonObject, heads) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
            }
        };
    }

    //从服务器得到数据
    protected static void getRequest(final String url, JSONObject jsonObject, String heads,
                                     final VolleyCallback volleyCallback) {
        new VolleyRequest(Request.Method.GET, url, jsonObject, heads) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
                Log.d(SaveFile.DEBUG_TAG, "volley error url = " + url);
            }
        };
    }

    //向服务器提交数据
    protected static void postRequest(String url, JSONObject jsonObject, String heads,
                                      final VolleyCallback volleyCallback) {
        new VolleyRequest(Request.Method.POST, url, jsonObject, heads) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
            }
        };
    }

    //向服务器提交数据
    protected static void putRequest(String url, JSONObject jsonObject, String heads,
                                     final VolleyCallback volleyCallback) {
        new VolleyRequest(Request.Method.PUT, url, jsonObject, heads) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
            }
        };
    }

    //从服务器得到list数据，可以灵活控制一次性拉去几页，或者几个数据
    protected static void getRequestListForPage(String url, JSONObject jsonObject, String heads,
                                                final VolleyCallback volleyCallback) {
        getRequestListForPage(url, null, jsonObject, heads, volleyCallback);
    }

    //从服务器得到list数据，可以灵活控制一次性拉去几页，或者几个数据
    protected static void getRequestListForPage(String url, Integer perPage, JSONObject jsonObject, String heads,
                                                final VolleyCallback volleyCallback) {
        if (perPage == null) {
            perPage = PAGES_PER_PAGE_NORMAL;
        }
        String key = getPageUrl(PAGES_NUMBER, perPage);

        new VolleyRequest(Request.Method.GET, url + key, jsonObject, heads) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
            }
        };
    }


    //向服务器修改数据
    protected static void put(Context context, String url, JSONObject jsonObject,
                              final VolleyCallback volleyCallback) {
        final WaitingProgressDialog waitingProgressDialog = WaitingProgressDialog.createDialog(context);
        waitingProgressDialog.show();

        new VolleyRequest(Request.Method.PUT, url, jsonObject, getToken()) {
            @Override
            protected void onSuccess(JSONObject success) {
                waitingProgressDialog.dismiss();
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                waitingProgressDialog.dismiss();
                volleyCallback.onErrorResponse(error);
            }
        };
    }

    protected static void put(String url, JSONObject jsonObject,
                              final VolleyCallback volleyCallback) {
        new VolleyRequest(Request.Method.PUT, url, jsonObject, getToken()) {
            @Override
            protected void onSuccess(JSONObject success) {
                volleyCallback.onSuccessResponse(success);
            }

            @Override
            protected void onError(VolleyError error) {
                volleyCallback.onErrorResponse(error);
            }
        };
    }

}
