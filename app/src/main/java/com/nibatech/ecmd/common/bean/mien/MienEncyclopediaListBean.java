package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class MienEncyclopediaListBean {

    /**
     * articles : [{"self_url":"http://60.205.141.66:5000/api/cms_encyclopaedics/3","view_count":0,"title":"中医讲解冬季为什么要养肾？","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/1.1480298420.520577.jpg","comment_count":0,"time":"2016-11-28 10:00:28"},{"self_url":"http://60.205.141.66:5000/api/cms_encyclopaedics/2","view_count":0,"title":"曾国藩的\u200b中医养生之道\u200b","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/1.1480298126.435062.jpg","comment_count":0,"time":"2016-11-28 09:55:34"},{"self_url":"http://60.205.141.66:5000/api/cms_encyclopaedics/1","view_count":1,"title":"这些茶，冷天喝着很暖","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/2.1479348880.908242.jpg","comment_count":0,"time":"2016-11-17 10:15:26"}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://60.205.141.66:5000/api/cms_encyclopaedics/?per_page=30&page=1","total":3,"page":1,"first_url":"http://60.205.141.66:5000/api/cms_encyclopaedics/?per_page=30&page=1"}
     */

    private PagesBean pages;
    private List<Article> articles;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public static class Article {
        /**
         * self_url : http://60.205.141.66:5000/api/cms_encyclopaedics/3
         * view_count : 0
         * title : 中医讲解冬季为什么要养肾？
         * cover_url : http://60.205.141.66:5000/static/app_files/cms_article/upload/1.1480298420.520577.jpg
         * comment_count : 0
         * time : 2016-11-28 10:00:28
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
