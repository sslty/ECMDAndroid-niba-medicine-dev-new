package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;

public class PayInfoBean {
    /**
     * payed : false
     * weixin_pay_unified_order_url : http://139.217.8.207:5000/api/weixin/pay/unified_order/online_treatment/218
     * weixin_pay_query_order_url : http://139.217.8.207:5000/api/weixin/pay/query_order/online_treatment/218
     */

    private boolean payed;
    @SerializedName("weixin_pay_unified_order_url")
    private String weiXinPayUnifiedOrderUrl;
    @SerializedName("weixin_pay_query_order_url")
    private String weiXinPayQueryOrderUrl;

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getWeiXinPayUnifiedOrderUrl() {
        return weiXinPayUnifiedOrderUrl;
    }

    public void setWeiXinPayUnifiedOrderUrl(String weiXinPayUnifiedOrderUrl) {
        this.weiXinPayUnifiedOrderUrl = weiXinPayUnifiedOrderUrl;
    }

    public String getWeiXinPayQueryOrderUrl() {
        return weiXinPayQueryOrderUrl;
    }

    public void setWeiXinPayQueryOrderUrl(String weiXinPayQueryOrderUrl) {
        this.weiXinPayQueryOrderUrl = weiXinPayQueryOrderUrl;
    }
}
