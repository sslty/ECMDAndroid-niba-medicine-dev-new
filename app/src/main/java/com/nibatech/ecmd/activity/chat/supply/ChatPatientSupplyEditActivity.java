package com.nibatech.ecmd.activity.chat.supply;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.chat.supply.ChatPatientSupplyEditFragment;


/**
 * 患者端   guide-聊天-添加基本资料
 */
public class ChatPatientSupplyEditActivity extends SlidingTabActivity {
    private ChatPatientSupplyEditFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("添加基本资料");
        addDefaultFragment(newFragment = new ChatPatientSupplyEditFragment());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*然后在碎片中调用重写的onActivityResult方法*/
        newFragment.onActivityResult(requestCode, resultCode, data);
    }
}

