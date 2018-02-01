package com.nibatech.ecmd.activity.register.profile;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.profile.UpdateByDoctorFragment;


/**
 * 医生端   医生-发放识别码-补充患者资料
 */
public class UpdateByDoctorActivity extends RegisterPaddingActivity {
    public static final int ACTIVITY_RESULT_UPDATE_PROFILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("建档资料");
        addDefaultFragment(new UpdateByDoctorFragment());
    }
}
