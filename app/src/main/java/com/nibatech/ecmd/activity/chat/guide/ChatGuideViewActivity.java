package com.nibatech.ecmd.activity.chat.guide;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.guide.ChatGuideViewFragment;


/**
 * 医生端／患者端   guide-聊天-查看指导意见
 */
public class ChatGuideViewActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("指导意见");
        addDefaultFragment(new ChatGuideViewFragment());
    }
}
