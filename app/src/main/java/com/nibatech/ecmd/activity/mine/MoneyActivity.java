package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;



public class MoneyActivity extends SlidingTabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("我的财富");
        //addDefaultFragment(new MoneyFragment());
    }

}
