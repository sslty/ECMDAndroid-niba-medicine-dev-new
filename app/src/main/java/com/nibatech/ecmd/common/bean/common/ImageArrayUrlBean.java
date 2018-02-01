package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageArrayUrlBean {
    @SerializedName("image_urls")
    private ArrayList<String> imagesUrl;

    public ArrayList<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(ArrayList<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }
}
