package com.nibatech.ecmd.activity.floatactionbutton.mien;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.fragment.floatactionbutton.mien.PublishArticleFragment;


/**
 * 医生端   mien-点加号-发表文章
 */
public class PublishArticleActivity extends SlidingTabActivity {
    private PublishArticleFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("发表文章");
        fragment = new PublishArticleFragment();
        addDefaultFragment(fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

}
