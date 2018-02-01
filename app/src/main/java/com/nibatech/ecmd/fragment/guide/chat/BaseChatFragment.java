package com.nibatech.ecmd.fragment.guide.chat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.bean.common.DoctorBean;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.CaseButtonGridView;
import com.nibatech.ecmd.view.ChatView;
import com.nibatech.ecmd.view.CustomSendView;
import com.tencent.TIMMessage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import sj.keyboard.data.PageEntity;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.utils.imageloader.ImageBase;


/**
 * 医生端/患者端   医生和患者／医生和医生／限时-聊天-父类
 */
public class BaseChatFragment extends BaseFragment implements Observer, ChatView.ChatViewListener {
    protected ChatConversation chatConversation;
    public static final int SELECT_BY_SUPPLY = 10;//补充资料
    public static final int SELECT_BY_GUIDE = 11;//指导意见

    private CustomSendView customSendView;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    private ChatView chatView;
    private CaseButtonGridView caseButtonGridView;


    public void onSendImage(String imageUri) {
        if (!TextUtils.isEmpty(imageUri)) {
            if (chatConversation != null) {
                ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
                chatMaterialBean.setName(imageUri);
                chatMaterialBean.setType(ChatConversation.MSG_IMAGE);
                chatConversation.sendMessage(chatMaterialBean);
            } else {
                UIUtils.connectToHostShowFail(getActivity());
            }
        }
    }

    @Override
    public void onSendEmotionsImage(String emotionUri) {
        if (!TextUtils.isEmpty(emotionUri)) {
            if (chatConversation != null) {
                ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
                chatMaterialBean.setName(emotionUri);
                chatMaterialBean.setType(ChatConversation.MSG_EMOTIONS_IMAGE);
                chatConversation.sendMessage(chatMaterialBean);
            } else {
                UIUtils.connectToHostShowFail(getActivity());
            }
        }
    }

    protected void setCustomChat(boolean customChat) {
        chatView.setCustomChat(customChat);
    }

    //初始化控件
    protected void initView(View view) {
        chatView = (ChatView) view.findViewById(R.id.chat_view);
        chatView.setChatViewListener(this);
    }


    //聊天界面的显示
    protected void setChatShow() {
        chatView.setChatShow();
    }

    //设置基本病情的文字
    protected void setBaseIllnessText(String text) {
        if (text != null) {
            caseButtonGridView.setBtnIllnessTextAndClickListener(text, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    illnessBtnClick();
                }
            });
        }
    }

    //设置底部button的文字
    protected void setBottomBtnText(String leftBtnText, String rightBtnText) {
        customSendView.setLeftAndRightText(leftBtnText, rightBtnText);
    }

    protected void illnessBtnClick() {
    }

    protected void leftBtnClick() {
        Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
    }

    protected void rightBtnClick() {
        Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View getChatSendView() {
        customSendView = new CustomSendView(getActivity());
        customSendView.setBtnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftBtnClick();
            }
        });
        customSendView.setBtnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightBtnClick();
            }
        });
        return customSendView;
    }


    @Override
    public PageSetEntity getCustomPageSetEntity() {
        caseButtonGridView = new CaseButtonGridView(getActivity());
        PageSetEntity pageSetEntity = new PageSetEntity.Builder()
                .addPageEntity(new PageEntity(caseButtonGridView))
                .setIconUri(ImageBase.Scheme.DRAWABLE.toUri("pull_refresh_logo"))
                .setShowIndicator(false)
                .build();
        return pageSetEntity;
    }

    @Override
    public int getCustomSendViewHeight() {
        return CustomSendView.VIEW_HEIGHT;
    }

    @Override
    public void onAddClicked() {
        Toast.makeText(getActivity(), "ADD", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSettingClicked() {
        Toast.makeText(getActivity(), "SETTING", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendBtnClick(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            sendNormalMessage(msg);
        }
    }

    protected void sendNormalMessage(String msg) {
        if (chatConversation != null) {
            ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
            chatMaterialBean.setName(msg);
            chatMaterialBean.setType(ChatConversation.MSG);
            chatConversation.sendMessage(chatMaterialBean);
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    //建立会话
    protected void createConversation(ChatIdentityBean myId, ChatIdentityBean peerId, String url) {
        MessageEvent.getInstance().addObserver(this);
        chatConversation = new ChatConversation(chatView, myId, peerId, url);
    }

    //设置已读消息状态
    protected void setReadMessage() {
        chatConversation.setReadMessage();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (chatConversation != null) {
            chatConversation.setReadMessage();
        }
        chatView.reset();
    }

    //收到消息
    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            chatView.getListAdapter().notifyDataSetChanged();
            return;
        }

        if (observable instanceof MessageEvent) {
            if (chatConversation != null) {
                chatConversation.showMessage((TIMMessage) data);
            }
        }
    }


    //移除诊疗阶段的按钮
    protected void removeAllCaseButton() {
        caseButtonGridView.removeAllButton();
    }


    //新建诊疗阶段的按钮
    protected void createButton(String name, boolean highLight, final String selfUrl, final String imageUrl,
                                final int mode) {
        caseButtonGridView.createButton(name, highLight, selfUrl, imageUrl, mode);
    }

    //
    //设置底部button的状态，可点击与否
    protected void setBottomButtonState(int state) {
        customSendView.setBottomButtonState(state);
    }

    //获取医生的信息
    protected static ChatIdentityBean getDoctorInfo(DoctorBean doctor) {
        ChatIdentityBean id = new ChatIdentityBean();

        id.setName(doctor.getFullName());
        id.setPeer(doctor.getCdNumber());
        id.setGender(doctor.getGender());
        id.setHeadUrl(doctor.getAvatarUrl());

        return id;
    }

    //获取病人的信息
    protected static ChatIdentityBean getPatientInfo(PatientBean patient) {
        ChatIdentityBean id = new ChatIdentityBean();

        id.setName(patient.getFullName());
        id.setPeer(patient.getCdNumber());
        id.setGender(patient.getGender());
        id.setHeadUrl(patient.getAvatarUrl());

        return id;
    }

    //获取病人的信息
    protected static ChatIdentityBean getDoctorInfo(DoctorProfileBean profile) {
        ChatIdentityBean id = new ChatIdentityBean();

        id.setName(profile.getFullName());
        id.setPeer(profile.getCdNumber());
        id.setGender(profile.getGender());
        id.setHeadUrl(profile.getAvatarUrl());

        return id;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                chatView.setEmotionAdapter(chatView.isCustom());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                case PhotoViewActivity.REQUIRE_TYPE_GALLERY://图库
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    for (String path : paths) {
                        onSendImage(path);
                    }
                    break;
            }
        }
    }
}

