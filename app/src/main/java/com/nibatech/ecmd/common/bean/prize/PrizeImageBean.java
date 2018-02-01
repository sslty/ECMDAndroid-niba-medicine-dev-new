package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

public class PrizeImageBean {
    /**
     * image_url : http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477387995.471152.jpg
     * name : 侧面舌照
     */
    @SerializedName("image_url")
    private String imageUrl;
    private String name;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
