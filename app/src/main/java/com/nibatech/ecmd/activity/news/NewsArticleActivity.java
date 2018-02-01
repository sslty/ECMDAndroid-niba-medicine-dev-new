package com.nibatech.ecmd.activity.news;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.news.NewsArticleFragment;

/**
 * 医生端／患者端／游客端   news-详情
 */
public class NewsArticleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("平台动态");
        addPageFragmentBindData(new NewsArticleFragment(), getIntentSelfUrl());
    }
}
