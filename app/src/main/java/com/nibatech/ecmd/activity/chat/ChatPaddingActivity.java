package com.nibatech.ecmd.activity.chat;


import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;


/**
 * 医生端   好友-聊天
 */
public class ChatPaddingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPadding(0, 0, 0, 0);
    }
}
