package com.nibatech.ecmd.activity.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.SelectIdentityFragment;


/**
 * 医生端/患者端   注册-选择身份
 */
public class SelectIdentityActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("注册");
        addDefaultFragment(new SelectIdentityFragment());
    }
}