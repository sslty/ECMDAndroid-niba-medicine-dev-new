package com.nibatech.ecmd.activity.floatactionbutton.guide;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.floatactionbutton.guide.DoctorTakeOrderFragment;


/**
 * 医生端   guide-我要接单
 */
public class DoctorTakeOrderActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("我要接单");

        addDefaultFragment(new DoctorTakeOrderFragment());
    }
}
