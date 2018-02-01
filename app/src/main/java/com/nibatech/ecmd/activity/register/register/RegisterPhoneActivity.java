package com.nibatech.ecmd.activity.register.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.register.RegisterPhoneFragment;


/**
 * 医生端/患者端   注册-发送验证码
 */
public class RegisterPhoneActivity extends RegisterPaddingActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("注册");
        addDefaultFragment(new RegisterPhoneFragment());
    }
}