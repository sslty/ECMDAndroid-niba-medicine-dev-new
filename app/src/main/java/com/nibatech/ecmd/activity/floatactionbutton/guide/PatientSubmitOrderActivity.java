package com.nibatech.ecmd.activity.floatactionbutton.guide;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.floatactionbutton.guide.PatientSubmitOrderFragment;


/**
 * 患者端   guide-挂单
 */
public class PatientSubmitOrderActivity extends SlidingTabActivity{
    private PatientSubmitOrderFragment patientSubmitOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("挂单");

        patientSubmitOrderFragment = new PatientSubmitOrderFragment();
        addDefaultFragment(patientSubmitOrderFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        patientSubmitOrderFragment.onActivityResult(requestCode, resultCode, data);
    }
}
