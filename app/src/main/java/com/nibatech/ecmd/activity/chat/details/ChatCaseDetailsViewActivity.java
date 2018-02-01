package com.nibatech.ecmd.activity.chat.details;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.details.ChatCaseDetailsViewFragment;


/**
 * 医生端/患者端   guide-聊天-查看基本资料
 */
public class ChatCaseDetailsViewActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("诊断详情");
        addDefaultFragment(new ChatCaseDetailsViewFragment());

    }
}
