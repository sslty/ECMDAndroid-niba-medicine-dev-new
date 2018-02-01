package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatPassageBean {
    /**
     * self_url : http://139.217.8.207:5000/api/patient_online_treatments/90/chat
     * type : guide
     * name : guide
     */

    private List<Path> paths;

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public static class Path {
        @SerializedName("self_url")
        private String selfUrl;
        private String type;
        private String name;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
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
    }
}
