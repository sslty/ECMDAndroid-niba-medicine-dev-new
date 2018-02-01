package com.nibatech.ecmd.activity.register.profile;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.profile.UpdatePatientFragment;


/**
 * 患者端   患者补充资料（患者没有个人资料，会限制很多行为）
 */
public class UpdatePatientActivity extends RegisterPaddingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("建档资料");
        addDefaultFragment(new UpdatePatientFragment());
    }

}
