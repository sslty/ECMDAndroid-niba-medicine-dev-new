package com.nibatech.ecmd.fragment.guide.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.details.ChatCaseDetailsViewActivity;
import com.nibatech.ecmd.activity.chat.guide.ChatEndGuideActivity;
import com.nibatech.ecmd.activity.guide.pay.PaymentActivity;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.bean.chat.ChatPatientBean;
import com.nibatech.ecmd.common.bean.chat.ChatStageBean;
import com.nibatech.ecmd.common.bean.chat.ChatSupplyResultBean;
import com.nibatech.ecmd.common.bean.guide.GuideStartTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.preferences.PaymentSharePreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.guide.chat.guide.ChatEndGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.CustomSendView;

import org.json.JSONObject;

import java.util.List;


/**
 * 患者端   guide-聊天
 */
public class ChatPatientCustomFragment extends BaseChatFragment implements
        ChatConversation.Refresh {
    private ChatPatientBean chatPatientBean;
    private boolean refresh = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_chat, container, false);
        initView(view);
        setCustomChat(true);
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
        chatPatientBean = UIUtils.fromJson(success, ChatPatientBean.class);
        //得到诊疗阶段详情
        if (chatPatientBean != null) {
            getHttpMaterialStages(chatPatientBean.getStagesUrl());
        }
    }

    private void getHttpMaterialStages(String url) {
        CommonRequest.getUrlData(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getMaterialStagesSuccess(success.toString());
            }
        });
    }

    public void getMaterialStagesSuccess(String success) {
        ChatStageBean chatStageBean = new Gson().fromJson(success, ChatStageBean.class);
        String type = "material";
        boolean finished = false;
        boolean empty = true;
        int size = chatStageBean.getStages().size();
        if (size != 0) {//某一次诊疗阶段
            size = chatStageBean.getStages().get(0).getUrls().size();
            if (size != 0) {//某一次治疗阶段中的某一次诊疗
                type = chatStageBean.getStages().get(0).getUrls().get(0).getType();
                finished = chatStageBean.getStages().get(0).getUrls().get(0).isFinished();
                empty = false;
            }
        }
        //底部button
        CustomSendView.ButtonState state = CustomSendView.getButtonState(type, finished, empty, false);
        setBottomButtonState(state.bottomState);
        //诊疗状态button
        addScrollButton(chatStageBean.getStages());
        if (!refresh) {
            //建立会话
            initConversation();
            //基本信息按钮
            setBaseIllnessText(UIUtils.linkNameAndTimeOnButton("基本信息",
                    chatPatientBean.getStartTime()));
            setBottomBtnText("结束指导", "开始复诊");
            setChatShow();
        }
    }

    //初始化聊天
    private void initConversation() {
        ChatIdentityBean myId = getPatientInfo(chatPatientBean.getPatient());
        ChatIdentityBean peerId = getDoctorInfo(chatPatientBean.getDoctor());
        createConversation(myId, peerId, chatPatientBean.getConversionUrl());
        chatConversation.setRefreshButton(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SELECT_BY_SUPPLY://补充数据数据返回
                if (data != null) {
                    returnSupply(data);
                }
                break;
            case SELECT_BY_GUIDE://结束指导返回
                returnGuide();
                break;
        }
    }

    private void returnSupply(Intent data) {
        String json = data.getExtras().getString(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_SUCCESS);
        if (json != null) {
            ChatSupplyResultBean supplyBean = new Gson().fromJson(json, ChatSupplyResultBean.class);
            ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
            chatMaterialBean.setName(supplyBean.getMaterial().getName());
            chatMaterialBean.setType(ChatConversation.MSG_SUPPLY);
            chatMaterialBean.setFinished(true);
            chatConversation.sendMessage(chatMaterialBean);
        }
    }

    private void returnGuide() {
        ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
        chatMaterialBean.setName("患者已经结束指导");
        chatMaterialBean.setType(ChatConversation.MSG_END_GUIDE);
        chatMaterialBean.setFinished(true);
        chatConversation.sendMessage(chatMaterialBean);
        getActivity().finish();
    }

    private void addScrollButton(List<ChatStageBean.Stage> stages) {
        for (int i = 0; i < stages.size(); i++) {
            List<ChatStageBean.Stage.Url> ulr = stages.get(i).getUrls();
            for (int j = 0; j < ulr.size(); j++) {
                String name = ulr.get(j).getName();
                String time = ulr.get(j).getTime();
                String type = ulr.get(j).getType();
                String selfUrl = ulr.get(j).getUrl();
                String imageUrl = ulr.get(j).getUploadUrl();
                boolean finished = ulr.get(j).isFinished();
                CustomSendView.ButtonState state = CustomSendView.getButtonState(type, finished, false, false);
                if (!(i == 0 && j == 0)) {
                    state.highLight = false;
                }
                createButton(UIUtils.linkNameAndTimeOnButton(name, time),
                        state.highLight, selfUrl, imageUrl, state.mode);
            }
        }
    }

    @Override
    public void refreshButton(ChatMaterialBean chatMaterialBean) {
        if (chatPatientBean != null) {
            refresh = true;
            removeAllCaseButton();
            getHttpMaterialStages(chatPatientBean.getStagesUrl());
        }
    }


    protected void illnessBtnClick() {
        gotoCase();//查看基本资料
    }

    protected void leftBtnClick() {
        gotoEndGuide();
    }

    protected void rightBtnClick() {
        gotoNewStage();//发送开始复诊
    }

    private void gotoNewStage() {
        if (chatPatientBean != null) {
            CommonRequest.postUrlData(chatPatientBean.getNewStageUrl(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
                    chatMaterialBean.setName("开始进入复诊阶段");
                    chatMaterialBean.setType(ChatConversation.MSG_AGAIN);
                    chatMaterialBean.setFinished(true);
                    chatConversation.sendMessage(chatMaterialBean);
                    //开始复诊后，先刷新进行中的列表，让支付状态改变，在进入支付界面。
                    BroadCast.send(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ONGOING);
                    gotoPayment();
                }
            });
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    //结束指导进入疗效统计
    private void gotoEndGuide() {
        if (chatPatientBean != null) {
            startActivityBindDataForResult(ChatEndGuideActivity.class, chatPatientBean.getEndStageUrl(),
                    ChatEndGuideFragment.ACTIVITY_RESULT_END_GUIDE);
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    //基本病例详情
    private void gotoCase() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ChatCaseDetailsViewActivity.class);
        intent.putExtra(ExtraPass.EXTRA_CHAT_CASE_URL, chatPatientBean.getDetailUrl());
        startActivity(intent);
    }

    //支付界面
    private void gotoPayment() {
        //保存url
        GuideStartTreatmentBean guideStartTreatmentBean = new GuideStartTreatmentBean();
        guideStartTreatmentBean.setChatUrl(getIntentSelfUrl());
        guideStartTreatmentBean.setFreeFirstTime(false);
        guideStartTreatmentBean.setWeixinPayUnifiedOrderUrl(chatPatientBean.getPayInfo().getWeiXinPayUnifiedOrderUrl());
        guideStartTreatmentBean.setWeixinPayQueryOrderUrl(chatPatientBean.getPayInfo().getWeiXinPayQueryOrderUrl());
        PaymentSharePreferences.save(getActivity(), guideStartTreatmentBean);
        //关闭本界面
        getActivity().finish();
        //开始支付
        startActivityBindData(PaymentActivity.class,
                chatPatientBean.getPayInfo().getWeiXinPayUnifiedOrderUrl());
    }

}

