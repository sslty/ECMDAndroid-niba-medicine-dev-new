package com.nibatech.ecmd.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nibatech.ecmd.fragment.guide.chat.ChatPatientCustomFragment;


/**
 * 患者端   中医指导-聊天
 */
public class ChatPatientCustomActivity extends ChatPaddingActivity {
    ChatPatientCustomFragment chatPatientCustomFragment = new ChatPatientCustomFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("中医指导-对话");
        addPageFragmentBindData(chatPatientCustomFragment, getIntentSelfUrl());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        chatPatientCustomFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        chatPatientCustomFragment.onActivityResult(requestCode, resultCode, data);
    }
}
