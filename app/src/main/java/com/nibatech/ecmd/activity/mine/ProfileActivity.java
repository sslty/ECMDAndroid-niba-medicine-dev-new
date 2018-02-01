package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mine.ProfileFragment;


/**
 * 医生端/患者端   我的-个人资料
 */
public class ProfileActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPadding(0, 0, 0, 0);
        setToolBarText("个人资料");
        addDefaultFragment(new ProfileFragment());
    }
}
