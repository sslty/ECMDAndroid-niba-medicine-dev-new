package com.nibatech.ecmd.activity.register;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.base.SlidingTabActivity;
import com.nibatech.ecmd.utils.UIUtils;


/**
 * 医生端   医生-发放识别码-补充患者资料
 */
public class RegisterPaddingActivity extends BaseActivity {
    int padding = UIUtils.dip2px(12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPadding(padding, 0, padding, 0);
    }
}
