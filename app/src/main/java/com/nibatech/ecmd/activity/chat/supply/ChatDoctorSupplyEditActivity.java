package com.nibatech.ecmd.activity.chat.supply;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.supply.ChatDoctorSupplyEditFragment;



/**
 * 医生端   guide-聊天-补充资料
 */
public class ChatDoctorSupplyEditActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("添加基本资料");
        addDefaultFragment(new ChatDoctorSupplyEditFragment());
    }

}

