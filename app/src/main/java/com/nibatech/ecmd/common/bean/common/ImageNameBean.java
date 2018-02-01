package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class ImageNameBean {
    @SerializedName("image_url")
    private String imageUrl;
    private String name;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }
}
