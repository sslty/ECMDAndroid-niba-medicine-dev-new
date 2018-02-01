package com.nibatech.ecmd.activity.homepage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.fragment.base.EmptyFragment;
import com.nibatech.ecmd.fragment.homepage.TouristHomePageFragment;

import java.util.ArrayList;


/**
 * 游客端   首页
 */
public class TouristHomePageActivity extends HomePageActivity {

    private String[] mBottomTitles = {"首页", "关注", "医生", "我的"};
    private int[] mIconUnSelectIds = {
            R.drawable.bottom_bar_home_hide, R.drawable.bottom_bar_encyclopedia_hide,
            R.drawable.bottom_bar_patient_hide, R.drawable.bottom_bar_mine_hide};
    private int[] mIconSelectIds = {
            R.drawable.bottom_bar_home_show, R.drawable.bottom_bar_encyclopedia_show,
            R.drawable.bottom_bar_patient_show, R.drawable.bottom_bar_mine_show};
    private ArrayList<Fragment> bottomFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bottomFragmentList.add(new TouristHomePageFragment());
        bottomFragmentList.add(new EmptyFragment());
        bottomFragmentList.add(new EmptyFragment());
        bottomFragmentList.add(new EmptyFragment());
        addHomePageBottomTab(bottomFragmentList, mBottomTitles, mIconUnSelectIds, mIconSelectIds);

        //显示搜索按钮
        findMessageButton().setVisibility(View.VISIBLE);
        //去掉后退功能
        hideBackOnToolbar();
    }


    @Override
    public void onTabSelect(int position) {
        super.onTabSelect(position);
        if (position != 0) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}