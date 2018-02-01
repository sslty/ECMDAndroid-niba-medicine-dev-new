package com.nibatech.ecmd.common.request;


import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;


/**
 * 医生端   好友-聊天
 */
public class FriendRequest extends BaseVolleyRequest{
    public static void getChatItemByCdNumber(String url, String cdNumber, final VolleyCallback volleyCallback) {
        String key = "?" + JSON_KEY_CD_NUMBER + "=";

        getRequest(url+key+cdNumber, null, getToken(), volleyCallback);
    }

}
