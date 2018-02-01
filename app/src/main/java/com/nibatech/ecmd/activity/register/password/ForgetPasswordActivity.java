package com.nibatech.ecmd.activity.register.password;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.password.ForgetPasswordFragment;


/**
 * 医生端/患者端/游客端   忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("忘记密码");
        addDefaultFragment(new ForgetPasswordFragment());
    }
}
