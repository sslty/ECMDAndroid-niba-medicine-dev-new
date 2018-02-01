package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;

public class ChatGuideBean {
    /**
     * next_guide_time : null
     * guide : null
     * guide_image_url : null
     */

    @SerializedName("next_guide_time")
    private String nextGuideTime;
    private String guide;
    @SerializedName("guide_image_url")
    private String guideImageUrl;

    public String getNextGuideTime() {
        return nextGuideTime;
    }

    public void setNextGuideTime(String nextGuideTime) {
        this.nextGuideTime = nextGuideTime;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getGuideImageUrl() {
        return guideImageUrl;
    }

    public void setGuideImageUrl(String guideImageUrl) {
        this.guideImageUrl = guideImageUrl;
    }
}
