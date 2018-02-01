package com.nibatech.ecmd.activity.message;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.message.MessageFragment;

public class MessageActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("消息");

        addDefaultFragment(new MessageFragment());
    }

}
