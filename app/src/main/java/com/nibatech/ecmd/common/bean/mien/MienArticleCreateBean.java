package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;

public class MienArticleCreateBean {
    /**
     * upload_image_url : http://139.217.8.207:5000/api/doctor_articles/1/upload_images
     * publish_url : http://139.217.8.207:5000/api/doctor_articles/1/publish
     */

    @SerializedName("upload_image_url")
    private String uploadImageUrl;
    @SerializedName("publish_url")
    private String publishUrl;

    public String getUploadImageUrl() {
        return uploadImageUrl;
    }

    public void setUploadImageUrl(String uploadImageUrl) {
        this.uploadImageUrl = uploadImageUrl;
    }

    public String getPublishUrl() {
        return publishUrl;
    }

    public void setPublishUrl(String publishUrl) {
        this.publishUrl = publishUrl;
    }
}
