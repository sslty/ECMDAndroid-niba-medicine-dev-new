package com.nibatech.ecmd.activity.register.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.register.register.ExamFragment;


/**
 * 医生端   注册-回答问题
 */
public class ExamActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setToolBarText("注册-回答问题");
        addDefaultFragment(new ExamFragment());
    }
}
