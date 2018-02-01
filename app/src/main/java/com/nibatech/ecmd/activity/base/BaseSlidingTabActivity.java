package com.nibatech.ecmd.activity.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nibatech.ecmd.view.BaseView;

/**
 * 医生端/患者端/游客端   父类控件
 */
public abstract class BaseSlidingTabActivity extends AppCompatActivity {

    private BaseView baseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseView = new BaseView(this) {
            @Override
            protected int getSuccessViewLayoutId() {
                return BaseSlidingTabActivity.this.getSuccessViewLayoutId();
            }
        };
        baseView.setVisibility(BaseView.Visibility.SUCCESS);
        onCreateSuccessView(baseView.getSuccessView());
        setContentView(baseView);
    }

    protected abstract int getSuccessViewLayoutId();

    protected abstract void onCreateSuccessView(View successView);

    protected void setVisibility(BaseView.Visibility visibility) {
        baseView.setVisibility(visibility);
    }

    protected View getSuccessView() {
        return baseView.getSuccessView();
    }

}
