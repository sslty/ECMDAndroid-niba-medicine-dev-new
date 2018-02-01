package com.nibatech.ecmd.common.bean.sea;

import com.google.gson.annotations.SerializedName;

public class SeaDetailBean {
    /**
     * detail_url : http://139.217.8.207:5000/static/app_files/medicine_legacy/1.html
     */

    @SerializedName("detail_url")
    private String detailUrl;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
