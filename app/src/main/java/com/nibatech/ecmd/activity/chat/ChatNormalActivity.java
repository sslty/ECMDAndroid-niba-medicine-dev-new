package com.nibatech.ecmd.activity.chat;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.guide.chat.ChatNormalFragment;

/**
 * 医生端／患者端   guide-聊天／限时聊天
 */
public class ChatNormalActivity extends ChatPaddingActivity {

    private ChatNormalFragment chatNormalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String strTime = getIntent().getStringExtra(ExtraPass.TITLE);
        String strTitle;
        if (strTime != null) {
            strTitle = "限时聊天";
        } else {
            strTitle = "聊天";
        }
        setToolBarText(strTitle);
        addPageFragmentBindData(chatNormalFragment = new ChatNormalFragment(), getIntentSelfUrl());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        chatNormalFragment.onActivityResult(requestCode, resultCode, data);
    }
}
