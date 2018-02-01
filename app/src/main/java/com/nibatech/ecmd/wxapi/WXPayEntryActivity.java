package com.nibatech.ecmd.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.nibatech.ecmd.activity.chat.ChatPatientCustomActivity;
import com.nibatech.ecmd.activity.guide.pay.PaymentActivity;
import com.nibatech.ecmd.common.bean.guide.GuidePaymentBean;
import com.nibatech.ecmd.common.bean.guide.GuideStartTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.preferences.PaymentSharePreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.pay.PaymentFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;


/**
 * 患者端   guide-病例详情-医生介绍-购买服务-支付
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private GuideStartTreatmentBean treatmentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //微信支付
        api = WXAPIFactory.createWXAPI(this, PaymentFragment.WEI_XIN_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        //从文件中获取数据
        treatmentBean = PaymentSharePreferences.get(getApplicationContext());
        if (baseResp.errCode == 0) {//成功
            requestQueryPaymentStatus(treatmentBean.getWeixinPayQueryOrderUrl());
        } else {//取消或者失败
            gotoPayment();
        }

        finish();
    }

    private void requestQueryPaymentStatus(String url) {
        CommonRequest.getUrlData(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                queryPaymentSuccess(success.toString());
            }
        });
    }

    private void queryPaymentSuccess(String json) {
        //广播通知，支付成功，销毁支付界面
        BroadCast.send(this, BroadCast.DESTROY_GUIDE_PAYMENT);

        GuidePaymentBean guidePaymentBean = UIUtils.fromJson(json, GuidePaymentBean.class);
        Toast.makeText(this, guidePaymentBean.getMsg(), Toast.LENGTH_SHORT).show();

        if (guidePaymentBean.isResult()) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, treatmentBean.getChatUrl());
            intent.setClass(this, ChatPatientCustomActivity.class);
            startActivity(intent);
        } else {
            gotoPayment();
        }
    }

    private void gotoPayment() {
        //广播通知，进入重新支付流程前，也要销毁支付界面
        BroadCast.send(this, BroadCast.DESTROY_GUIDE_PAYMENT);
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.SELF_URL, treatmentBean.getWeixinPayUnifiedOrderUrl());
        intent.setClass(this, PaymentActivity.class);
        startActivity(intent);
    }
}
