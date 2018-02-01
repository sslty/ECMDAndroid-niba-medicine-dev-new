package com.nibatech.ecmd.activity.register.profile;

import android.os.Bundle;

import com.nibatech.ecmd.activity.register.RegisterPaddingActivity;
import com.nibatech.ecmd.fragment.register.profile.UpdateByPatientFragment;



/**
 * 患者端   识别码-输入识别码-补充自己的资料
 */
public class UpdateByPatientActivity extends RegisterPaddingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("建档资料");
        addDefaultFragment(new UpdateByPatientFragment());
    }

}
