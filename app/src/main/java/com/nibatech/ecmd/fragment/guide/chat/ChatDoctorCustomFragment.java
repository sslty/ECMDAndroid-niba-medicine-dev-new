package com.nibatech.ecmd.fragment.guide.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.details.ChatCaseDetailsViewActivity;
import com.nibatech.ecmd.activity.chat.guide.ChatGuideEditActivity;
import com.nibatech.ecmd.activity.chat.supply.ChatDoctorSupplyEditActivity;
import com.nibatech.ecmd.common.bean.chat.ChatDoctorBean;
import com.nibatech.ecmd.common.bean.chat.ChatGuideResultBean;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.bean.chat.ChatStageBean;
import com.nibatech.ecmd.common.bean.chat.ChatSupplyResultBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.CustomSendView;
import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMMessage;

import org.json.JSONObject;

import java.util.List;
import java.util.Observable;


/**
 * 医生端   guide-聊天
 */
public class ChatDoctorCustomFragment extends BaseChatFragment implements
        ChatConversation.Refresh {
    private ChatDoctorBean chatDoctorBean;
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
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    //一次性拉去聊天信息
    private void getHostUrlDataSuccess(String json) {
        chatDoctorBean = new Gson().fromJson(json, ChatDoctorBean.class);
        getHttpMaterialStages(chatDoctorBean.getStagesUrl());
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
        CustomSendView.ButtonState state = CustomSendView.getButtonState(type, finished, empty, true);
        setBottomButtonState(state.bottomState);
        //诊疗状态button
        addScrollButton(chatStageBean.getStages());

        if (!refresh) {
            //成功后再建立对话模式
            initConversation();
            //基本信息按钮
            setBaseIllnessText(UIUtils.linkNameAndTimeOnButton("基本信息",
                    chatDoctorBean.getStartTime()));
            setBottomBtnText("指导意见", "补充资料");

            setChatShow();
        }

    }

    private void initConversation() {
        ChatIdentityBean myId = getDoctorInfo(chatDoctorBean.getDoctor());
        ChatIdentityBean peerId = getPatientInfo(chatDoctorBean.getPatient());
        createConversation(myId, peerId, chatDoctorBean.getConversionUrl());
        chatConversation.setRefreshButton(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_BY_SUPPLY:
                if (data != null) {
                    returnSupply(data);//补充数据数据返回
                }
                break;
            case SELECT_BY_GUIDE:
                if (data != null) {
                    returnGuide(data);//指导意见界面返回的数据
                }
                break;
        }
    }

    private void returnGuide(Intent data) {
        String json = data.getExtras().getString(ExtraPass.EXTRA_CHAT_GUIDE_SUGGESTION_JSON);
        if (json != null) {
            ChatGuideResultBean guideBean = new Gson().fromJson(json, ChatGuideResultBean.class);
            ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
            chatMaterialBean.setName(guideBean.getGuide().getName());
            chatMaterialBean.setType(ChatConversation.MSG_PUT_GUIDE);
            chatMaterialBean.setFinished(false);
            chatConversation.sendMessage(chatMaterialBean);
        }
    }

    private void returnSupply(Intent data) {
        String json = data.getExtras().getString(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_SUCCESS);
        if (json != null) {
            ChatSupplyResultBean supplyBean = new Gson().fromJson(json, ChatSupplyResultBean.class);
            ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
            chatMaterialBean.setName(supplyBean.getMaterial().getName());
            chatMaterialBean.setType(ChatConversation.MSG_SUPPLY);
            chatMaterialBean.setFinished(false);
            chatConversation.sendMessage(chatMaterialBean);
        }
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
                CustomSendView.ButtonState state = CustomSendView.getButtonState(type, finished, false, true);
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
        if (chatDoctorBean != null) {
            refresh = true;
            removeAllCaseButton();
            getHttpMaterialStages(chatDoctorBean.getStagesUrl());
        }
    }

    protected void illnessBtnClick() {
        gotoCase();//查看基本资料
    }

    protected void leftBtnClick() {
        gotoGuide();//发送指导意见
    }

    protected void rightBtnClick() {
        gotoSupply();//发送补充资料
    }

    private void gotoGuide() {
        Intent intent = new Intent(getActivity(), ChatGuideEditActivity.class);
        intent.putExtra(ExtraPass.EXTRA_CHAT_PATIENT_JSON, new Gson().toJson(chatDoctorBean.getPatient()));
        intent.putExtra(ExtraPass.EXTRA_CHAT_SELF_URL, chatDoctorBean.getGuideUrl());
        intent.putExtra(ExtraPass.EXTRA_CHAT_IMAGE_URL, chatDoctorBean.getGuideImageUrl());
        startActivityForResult(intent, SELECT_BY_GUIDE);
    }

    private void gotoSupply() {
        if (chatDoctorBean.getCreateMaterialUrl() != null) {
            Activity mActivity = getActivity();
            Intent intent = new Intent(mActivity, ChatDoctorSupplyEditActivity.class);
            intent.putExtra(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_URL, chatDoctorBean.getCreateMaterialUrl());
            startActivityForResult(intent, SELECT_BY_SUPPLY);
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    private void gotoCase() {
        if (chatDoctorBean.getDetailUrl() != null) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ChatCaseDetailsViewActivity.class);
            intent.putExtra(ExtraPass.EXTRA_CHAT_CASE_URL, chatDoctorBean.getDetailUrl());
            startActivity(intent);
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        super.update(observable, data);

        if (data != null) {
            TIMMessage message = (TIMMessage) data;
            TIMElem elem = message.getElement(0);
            TIMCustomElem customElem = (TIMCustomElem) elem;
            byte[] bytes = customElem.getData();
            String dataJson = new String(bytes);
            ChatMaterialBean chatMaterialBean = new Gson().fromJson(dataJson, ChatMaterialBean.class);
            //结束指导
            if (chatMaterialBean.getType().equals(ChatConversation.MSG_END_GUIDE)) {
                onDialogFinishedGuide();
            }
        }
    }

    private void onDialogFinishedGuide() {
        if (getActivity() != null) {
            AlertDialogBuilder.onCreate(getActivity(), "提示", "患者已经结束康复指导，点确定关闭本界面", false,
                    new AlertDialogListener() {
                        @Override
                        public void selectPositive() {
                            getActivity().finish();
                        }
                    });
        }
    }
}
