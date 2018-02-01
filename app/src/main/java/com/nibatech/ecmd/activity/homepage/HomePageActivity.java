package com.nibatech.ecmd.activity.homepage;


import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.common.net.NetWorkStateReceiver;

import java.util.ArrayList;

/**
 * 医生端／患者端／游客端   首页-父类
 */
public abstract class HomePageActivity extends SlidingTabActivity {
    private NetWorkStateReceiver netWorkStateReceiver;

    protected final String PEER_DOCTOR = "D";
    protected final String PEER_PATIENT = "P";

    @Override
    protected void onResume() {
        super.onResume();

        //注册网络
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //注销网络
        unregisterReceiver(netWorkStateReceiver);
    }

    @Override
    public void onTabSelect(int position) {
        //隐藏所有的button
        hideToolbarButtons();

        switch (position) {
            case 0://首页
                findMessageButton().setVisibility(View.VISIBLE);
                break;
            case 1://关注或者好友
            case 2://医生或者患者
                break;
            case 3:
                break;
        }
    }


    public void addHomePageBottomTab(ArrayList<Fragment> fragments, String[] titles, int[] iconUnselectIds, int[] iconSelectIds) {
        addBottomTab(fragments, titles, iconUnselectIds, iconSelectIds);
    }
}