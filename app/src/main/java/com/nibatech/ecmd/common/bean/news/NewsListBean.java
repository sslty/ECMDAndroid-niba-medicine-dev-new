package com.nibatech.ecmd.common.bean.news;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class NewsListBean {

    /**
     * dynamics : [{"self_url":"http://139.217.8.207:5000/api/plat_dynamics/2","view_count":1,"title":"这是平台动态22222","cover_url":"http://139.217.8.207:5000/static/app_files/plat_dynamic/upload/1.1479181025.125815.jpg","comment_count":0,"time":"2016-11-15 11:37:12"},{"self_url":"http://139.217.8.207:5000/api/plat_dynamics/1","view_count":0,"title":"这是平台动态的为难","cover_url":"http://139.217.8.207:5000/static/app_files/plat_dynamic/upload/1.1479180992.641260.jpg","comment_count":0,"time":"2016-11-15 11:36:44"}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://139.217.8.207:5000/api/plat_dynamics/?per_page=30&page=1","total":2,"page":1,"first_url":"http://139.217.8.207:5000/api/plat_dynamics/?per_page=30&page=1"}
     */

    private PagesBean pages;
    private List<Dynamic> dynamics;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<Dynamic> getDynamics() {
        return dynamics;
    }

    public void setDynamics(List<Dynamic> dynamics) {
        this.dynamics = dynamics;
    }

    public static class Dynamic {
        /**
         * self_url : http://139.217.8.207:5000/api/plat_dynamics/2
         * view_count : 1
         * title : 这是平台动态22222
         * cover_url : http://139.217.8.207:5000/static/app_files/plat_dynamic/upload/1.1479181025.125815.jpg
         * comment_count : 0
         * time : 2016-11-15 11:37:12
         */

        @SerializedName("self_url")
        private String selfUrl;
        @SerializedName("view_count")
        private int viewCount;
        private String title;
        @SerializedName("cover_url")
        private String coverUrl;
        @SerializedName("comment_count")
        private int commentCount;
        private String time;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
