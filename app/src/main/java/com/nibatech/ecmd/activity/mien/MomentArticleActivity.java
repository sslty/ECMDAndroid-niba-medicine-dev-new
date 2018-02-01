package com.nibatech.ecmd.activity.mien;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mien.details.MomentArticleFragment;

/**
 * 医生端   mien-moment-文章
 */
public class MomentArticleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("文章");
        addPageFragmentBindData(new MomentArticleFragment(), getIntentSelfUrl());
    }

}
