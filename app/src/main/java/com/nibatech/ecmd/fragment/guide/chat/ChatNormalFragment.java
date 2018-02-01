package com.nibatech.ecmd.fragment.guide.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatNormalDoctorBean;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONObject;


/**
 * 医生端／患者端   guide-聊天／限时聊天
 */
public class ChatNormalFragment extends BaseChatFragment {
    private ChatNormalDoctorBean chatNormalDoctorBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_chat, container, false);
        initView(view);
        setCustomChat(false);
        getHttpData();
        return view;
    }

    private void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    private void getUrlDataSuccess(String success) {
        chatNormalDoctorBean = new Gson().fromJson(success, ChatNormalDoctorBean.class);
        setViewData();
        setChatShow();
    }

    private void setViewData() {
        ChatIdentityBean doctorId = getDoctorInfo(chatNormalDoctorBean.getDoctor());
        ChatIdentityBean patientId = getPatientInfo(chatNormalDoctorBean.getPatient());
        //消息观察者
        MessageEvent.getInstance().addObserver(this);
        //根据患者还是医生，建立与对方的聊天
        if (BaseVolleyRequest.getIdentity() == BaseVolleyRequest.IDENTITY_DOCTOR) {//医生
            createConversation(doctorId, patientId, chatNormalDoctorBean.getConversionUrl());
        } else {//患者
            createConversation(patientId, doctorId, chatNormalDoctorBean.getConversionUrl());
        }
        setReadMessage();
    }
}
