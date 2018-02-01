package com.nibatech.ecmd.activity.guide.introduce;

import android.os.Bundle;

import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.guide.introduce.GuideIntroduceDoctorFragment;

/**
 * 患者端   guide-挂单区-医生介绍
 */
public class GuideIntroduceDoctorActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("医生介绍");
        addDefaultFragment(new GuideIntroduceDoctorFragment());
    }
}
