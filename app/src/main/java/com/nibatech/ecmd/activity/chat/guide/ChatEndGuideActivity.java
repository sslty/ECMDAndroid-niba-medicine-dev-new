package com.nibatech.ecmd.activity.chat.guide;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.guide.chat.guide.ChatEndGuideFragment;


/**
 * 患者端   guide-聊天-结束指导意见
 */
public class ChatEndGuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("结束康复指导");
        addDefaultFragment(new ChatEndGuideFragment());
    }
}
