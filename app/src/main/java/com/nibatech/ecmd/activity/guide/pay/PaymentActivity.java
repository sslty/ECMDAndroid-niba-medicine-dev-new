package com.nibatech.ecmd.activity.guide.pay;

import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.pay.PaymentFragment;


/**
 * 患者端   guide-挂单-开始诊疗-购买服务
 */
public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("购买服务");
        addDefaultFragment(new PaymentFragment());
    }
}
