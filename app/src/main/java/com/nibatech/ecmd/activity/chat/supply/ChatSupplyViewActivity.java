package com.nibatech.ecmd.activity.chat.supply;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.supply.ChatSupplyViewFragment;


/**
 * 医生端／患者端   guide-聊天-查看基本资料
 */
public class ChatSupplyViewActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("查看基本资料");
        addDefaultFragment(new ChatSupplyViewFragment());
    }
}

