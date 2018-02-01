package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;



public class HistoryActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("历史数据");
        //addDefaultFragment(new HistoryFragment());
    }


}
