package com.nibatech.ecmd.activity.mine;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.view.BaseView;


public class JoinActivity extends SlidingTabActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("我的参与");
//        getSelfView(R.layout.fragment_send_code);
//        getSelfView(R.layout.fragment_send_code).setVisibility(BaseView.Visibility.EMPTY);
        setVisibility(BaseView.Visibility.EMPTY);
    }
}