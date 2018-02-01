package com.nibatech.ecmd.activity.mine.follow;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mine.follow.FollowerViewPageFragment;


/**
 * 医生端   我的-关注我的
 */
public class FollowerViewPageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("关注我的");
        setPadding(0, 0, 0, 0);
        addPageFragmentBindData(new FollowerViewPageFragment(), getIntentSelfUrl());
    }

}
