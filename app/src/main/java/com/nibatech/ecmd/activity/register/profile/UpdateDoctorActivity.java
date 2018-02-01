package com.nibatech.ecmd.activity.register.profile;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.profile.UpdateDoctorFragment;


/**
 * 医生端   医生补充资料（医生没有个人资料，会限制很多行为）
 */
public class UpdateDoctorActivity extends RegisterPaddingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("建档资料");
        addDefaultFragment(new UpdateDoctorFragment());
    }
}
