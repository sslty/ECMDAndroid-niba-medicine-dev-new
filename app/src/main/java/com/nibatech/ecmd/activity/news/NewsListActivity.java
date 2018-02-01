package com.nibatech.ecmd.activity.news;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.news.NewsListFragment;


/**
 * 医生端／患者端／游客端   平台动态
 */
public class NewsListActivity extends SlidingTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("平台动态");
        addDefaultFragment(new NewsListFragment());
//        addDefaultFragment(new TestDepartmentFragment());
    }
}
