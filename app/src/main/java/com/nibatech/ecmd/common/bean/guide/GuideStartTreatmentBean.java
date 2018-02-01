package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;

public class GuideStartTreatmentBean {
    /**
     * free_first_time : false
     * chat_url : http://139.217.8.207:5000/api/patient_online_treatments/179/chat
     * weixin_pay_unified_order_url : http://139.217.8.207:5000/api/weixin/pay/unified_order/online_treatment/179
     * weixin_pay_query_order_url : http://139.217.8.207:5000/api/weixin/pay/query_order/online_treatment/179
     */

    @SerializedName("free_first_time")
    private boolean freeFirstTime;
    @SerializedName("chat_url")
    private String chatUrl;
    @SerializedName("weixin_pay_unified_order_url")
    private String weixinPayUnifiedOrderUrl;
    @SerializedName("weixin_pay_query_order_url")
    private String weixinPayQueryOrderUrl;

    public boolean isFreeFirstTime() {
        return freeFirstTime;
    }

    public void setFreeFirstTime(boolean freeFirstTime) {
        this.freeFirstTime = freeFirstTime;
    }

    public String getChatUrl() {
        return chatUrl;
    }

    public void setChatUrl(String chatUrl) {
        this.chatUrl = chatUrl;
    }

    public String getWeixinPayUnifiedOrderUrl() {
        return weixinPayUnifiedOrderUrl;
    }

    public void setWeixinPayUnifiedOrderUrl(String weixinPayUnifiedOrderUrl) {
        this.weixinPayUnifiedOrderUrl = weixinPayUnifiedOrderUrl;
    }

    public String getWeixinPayQueryOrderUrl() {
        return weixinPayQueryOrderUrl;
    }

    public void setWeixinPayQueryOrderUrl(String weixinPayQueryOrderUrl) {
        this.weixinPayQueryOrderUrl = weixinPayQueryOrderUrl;
    }
}
