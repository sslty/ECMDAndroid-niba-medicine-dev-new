package com.nibatech.ecmd.activity.mien;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mien.details.MienArticleFragment;


/**
 * 医生端   mien-风采-文章
 */
public class MienArticleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("文章");
        addPageFragmentBindData(new MienArticleFragment(), getIntentSelfUrl());
    }
}
