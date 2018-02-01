package com.nibatech.ecmd.activity.mine.follow;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mine.follow.FollowedFragment;


/**
 * 医生端   我的-我的关注
 */
public class FollowedActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("我的关注");
        addPageFragmentBindData(new FollowedFragment(), getIntentSelfUrl());
    }
}
