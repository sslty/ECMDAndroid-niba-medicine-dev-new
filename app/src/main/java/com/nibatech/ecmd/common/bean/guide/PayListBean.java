package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;

public class PayListBean {
    /**
     * package : Sign=WXPay
     * timestamp : 1481617084
     * sign : C3F9BEF747D7FBA345FB2E4A7480ED2B
     * partnerid : 1408090902
     * appid : wx10f92949354f9e65
     * prepayid : wx20161213161804c7ba932c970291752932
     * noncestr : FSqcZPHvIGOyYD4bCr6Hkr7U3LOb5bKT
     */

    @SerializedName("package")
    private String packAge;
    private String timestamp;
    private String sign;
    @SerializedName("partnerid")
    private String partnerId;
    @SerializedName("appid")
    private String appId;
    @SerializedName("prepayid")
    private String prepayId;
    private String noncestr;

    public String getPackAge() {
        return packAge;
    }

    public void setPackAge(String packAge) {
        this.packAge = packAge;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
}
