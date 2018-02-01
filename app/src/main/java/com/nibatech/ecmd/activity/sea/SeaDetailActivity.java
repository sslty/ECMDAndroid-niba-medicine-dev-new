package com.nibatech.ecmd.activity.sea;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.sea.SeaDetailsFragment;


/**
 * 患者端/游客端   首页-sea-详情
 */
public class SeaDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText(getIntent().getStringExtra(ExtraPass.TITLE));
        addDefaultFragment(new SeaDetailsFragment());
    }
}
