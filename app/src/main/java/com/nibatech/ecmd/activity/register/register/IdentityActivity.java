package com.nibatech.ecmd.activity.register.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.register.IdentityFragment;


/**
 * 医生端／患者端   选择身份
 */
public class IdentityActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("请选择身份");
        addDefaultFragment(new IdentityFragment());
    }
}
