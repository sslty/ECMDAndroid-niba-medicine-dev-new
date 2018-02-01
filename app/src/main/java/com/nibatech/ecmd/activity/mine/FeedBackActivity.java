package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.mine.FeedBackFragment;


public class FeedBackActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("反馈意见");
        addDefaultFragment(new FeedBackFragment());
    }

}
