package com.nibatech.ecmd.activity.chat.guide;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.guide.ChatGuideEditFragment;


/**
 * 医生端   guide-聊天-发送指导意见
 */
public class ChatGuideEditActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("指导意见确认");
        addDefaultFragment(new ChatGuideEditFragment());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
