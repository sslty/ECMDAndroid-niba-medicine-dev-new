package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mine.ModifyProfileFragment;

/**
 * 医生端/患者端   我的-个人资料-修改资料
 */
public class ModifyProfileActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("修改资料");
        addDefaultFragment(new ModifyProfileFragment());
    }
}
