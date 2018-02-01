package com.nibatech.ecmd.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nibatech.ecmd.fragment.guide.chat.ChatDoctorCustomFragment;


/**
 * 医生端   guide-聊天
 */
public class ChatDoctorCustomActivity extends ChatPaddingActivity {

    ChatDoctorCustomFragment chatDoctorCustomFragment = new ChatDoctorCustomFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("中医指导-对话");
        addPageFragmentBindData(chatDoctorCustomFragment, getIntentSelfUrl());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        chatDoctorCustomFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        chatDoctorCustomFragment.onActivityResult(requestCode, resultCode, data);
    }
}
