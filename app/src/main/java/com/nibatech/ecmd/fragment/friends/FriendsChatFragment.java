package com.nibatech.ecmd.fragment.friends;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.friends.FriendDoctorProfileBean;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.guide.chat.BaseChatFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

/**
 * 医生端   好友-聊天
 */
public class FriendsChatFragment extends BaseChatFragment {
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
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    private void getHostUrlDataSuccess(String json) {
        FriendDoctorProfileBean friendDoctorProfileBean = UIUtils.fromJson(json, FriendDoctorProfileBean.class);
        setViewData(friendDoctorProfileBean);
        setChatShow();
    }

    private void setViewData(FriendDoctorProfileBean friendDoctorProfileBean) {
        //消息观察者
        MessageEvent.getInstance().addObserver(this);
        //返回的结果得到两个信息，getDoctorProfileOne和getDoctorProfileTwo
        //建立与对方的聊天，用cd-number与自己登录信息比较，获取对方的信息
        DoctorProfileBean myProfile, otherProfile;
        if (BaseVolleyRequest.getLogin().getUser().getCdNumber().equals(
                friendDoctorProfileBean.getDoctorProfileOne().getCdNumber())) {
            myProfile = friendDoctorProfileBean.getDoctorProfileOne();
            otherProfile = friendDoctorProfileBean.getDoctorProfileTwo();
        } else {
            myProfile = friendDoctorProfileBean.getDoctorProfileTwo();
            otherProfile = friendDoctorProfileBean.getDoctorProfileOne();
        }

        createConversation(getDoctorInfo(myProfile), getDoctorInfo(otherProfile),
                friendDoctorProfileBean.getConversionUrl());
        //设置已读消息
        setReadMessage();
    }
}
