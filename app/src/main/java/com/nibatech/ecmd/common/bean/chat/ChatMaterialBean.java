package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;

public class ChatMaterialBean {
    /**
     * url : http://139.217.8.207:5000/api/online_treatments/25/stages/6/materials/109
     * finished : false
     * type : material
     * name : 补充资料13
     * time : 2016-10-15T21:00:38
     */

    private String url;
    private boolean finished;
    private String type;
    private String name;
    private String time;
    @SerializedName("upload_url")
    private String uploadUrl;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }


}