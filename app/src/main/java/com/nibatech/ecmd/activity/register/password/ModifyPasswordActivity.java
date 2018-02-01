package com.nibatech.ecmd.activity.register.password;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.password.ForgetPasswordFragment;
import com.nibatech.ecmd.fragment.register.password.ModifyPasswordFragment;

/**
 * 医生端/患者端/游客端   重设密码
 */
public class ModifyPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("重设登录密码");
        addDefaultFragment(new ModifyPasswordFragment());
    }
}



