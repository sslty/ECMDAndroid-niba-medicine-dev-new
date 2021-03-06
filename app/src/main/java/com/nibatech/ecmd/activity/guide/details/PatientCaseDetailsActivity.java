package com.nibatech.ecmd.activity.guide.details;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.guide.details.PatientCaseDetailsFragment;

/**
 * 患者端   guide-挂单区／进行中-病例详情-子类
 */
public class PatientCaseDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("病例详情");
        addDefaultFragment(new PatientCaseDetailsFragment());
    }
}
