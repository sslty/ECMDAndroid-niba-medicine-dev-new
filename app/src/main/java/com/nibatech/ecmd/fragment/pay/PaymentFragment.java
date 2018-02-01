package com.nibatech.ecmd.fragment.pay;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.guide.PayListBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * 患者端   guide-挂单-开始诊疗-购买服务
 */
public class PaymentFragment extends BaseFragment implements NormalViewFragment.ShowNormalViewDataListener,
        View.OnClickListener {
    public static final String WEI_XIN_APP_ID = "wx10f92949354f9e65";
    private TextView tvOrderDetails, tvOrderMoney;
    private Button btnPay;
    private LinearLayout layoutImagePay;
    private IWXAPI api;
    private PayListBean payListBean;
    private BroadCast broadCast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        initView(view);
        setViewListener();
        getHostUrlData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBroadCast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(getActivity(), BroadCast.DESTROY_GUIDE_PAYMENT,
                new BroadCastCallBack() {
                    @Override
                    public void onDestroy(String action) {
                        if (action.equals(BroadCast.DESTROY_GUIDE_PAYMENT)) {
                            getActivity().finish();
                        }
                    }
                });
    }

    protected void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    @Override
    public void initView(View view) {
        tvOrderDetails = (TextView) view.findViewById(R.id.tv_order_details);
        tvOrderMoney = (TextView) view.findViewById(R.id.tv_order_money);
        btnPay = (Button) view.findViewById(R.id.btn_pay);
        layoutImagePay = (LinearLayout) view.findViewById(R.id.layout_image_pay);
        api = WXAPIFactory.createWXAPI(getActivity(), WEI_XIN_APP_ID, false);
        api.registerApp(WEI_XIN_APP_ID);
    }

    @Override
    public void setViewListener() {
        btnPay.setOnClickListener(this);
        layoutImagePay.setOnClickListener(this);
    }

    @Override
    public void getHostUrlData() {
        CommonRequest.postUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        payListBean = UIUtils.fromJson(json, PayListBean.class);
    }

    @Override
    public void setViewData(Object object) {
        tvOrderDetails.setText(String.format("订单详情 :%s", ""));
        tvOrderMoney.setText(String.format("订单金额 : %s", ""));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                requestPayment();
                break;
            case R.id.layout_image_pay:
                break;
        }
    }

    private void requestPayment() {
        if (payListBean != null) {
            if (api.getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
                Toast.makeText(getActivity(), "微信版本太低，不支持微信支付", Toast.LENGTH_SHORT).show();
            } else if (!api.isWXAppInstalled()) {
                Toast.makeText(getActivity(), "本机未安装微信，请前往官网下载", Toast.LENGTH_SHORT).show();
            } else {
                btnPay.setClickable(false);
                PayReq request = new PayReq();
                request.appId = payListBean.getAppId();
                request.partnerId = payListBean.getPartnerId();
                request.prepayId = payListBean.getPrepayId();
                request.packageValue = payListBean.getPackAge();
                request.nonceStr = payListBean.getNoncestr();
                request.timeStamp = payListBean.getTimestamp();
                request.sign = payListBean.getSign();
                api.sendReq(request);
            }
        }
    }
}
