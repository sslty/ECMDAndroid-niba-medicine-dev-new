package com.nibatech.ecmd.activity.register.profile;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.profile.CreateDoctorFragment;

/**
 * 医生端   注册-新建资料
 */
public class CreateDoctorActivity extends RegisterPaddingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("基本资料");
        addDefaultFragment(new CreateDoctorFragment());
    }
}
