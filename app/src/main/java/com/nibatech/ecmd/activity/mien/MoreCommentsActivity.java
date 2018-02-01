package com.nibatech.ecmd.activity.mien;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mien.MoreCommentsFragment;


/**
 * 医生端／患者端／游客端   mien-encyclopedia／风采／moment／news-查看更多评论
 */
public class MoreCommentsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("更多评论");
        addPageFragmentBindData(new MoreCommentsFragment(), getIntentSelfUrl());
    }

}
