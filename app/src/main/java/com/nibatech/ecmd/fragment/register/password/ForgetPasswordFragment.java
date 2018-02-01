package com.nibatech.ecmd.fragment.register.password;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nibatech.ecmd.activity.register.password.ModifyPasswordActivity;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.register.SendCodeFragment;

/**
 * 医生端/患者端   忘记密码
 */
public class ForgetPasswordFragment extends SendCodeFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //重载父类的方法
    public void onClickNext() {
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.PHONE, editPhone.getText().toString());
        intent.putExtra(ExtraPass.CODE, editCode.getText().toString());
        intent.setClass(getActivity(), ModifyPasswordActivity.class);
        startActivity(intent);
    }
}
