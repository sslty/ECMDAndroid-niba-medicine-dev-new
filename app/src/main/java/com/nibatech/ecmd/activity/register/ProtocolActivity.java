package com.nibatech.ecmd.activity.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.ProtocolFragment;


/**
 * 医生端/患者端   注册-注册协议
 */
public class ProtocolActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("注册协议");
        addDefaultFragment(new ProtocolFragment());
    }

}
