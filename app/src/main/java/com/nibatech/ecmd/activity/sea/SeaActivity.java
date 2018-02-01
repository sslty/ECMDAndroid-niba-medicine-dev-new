package com.nibatech.ecmd.activity.sea;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.sea.SeaListFragment;


/**
 * 患者端/游客端   首页-方海寻珠
 */
public class SeaActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("方海寻珠");
        addDefaultFragment(new SeaListFragment());
    }
}
