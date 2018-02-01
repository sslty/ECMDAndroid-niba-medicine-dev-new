package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class MienListBean {

    /**
     * articles : [{"self_url":"http://60.205.141.66:5000/api/cms_articles/5","view_count":10,"title":"中医名家\u2014\u2014王位","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/1.1480298958.278660.jpg","comment_count":0,"time":"2016-11-28 10:49:37","type":"名医视频"},{"self_url":"http://60.205.141.66:5000/api/cms_articles/4","view_count":6,"title":"萧龙友验案\u2014\u2014后脑昏痛案","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/2.1479347805.563337.jpg","comment_count":0,"time":"2016-11-22 16:02:16","type":"名家理论"},{"self_url":"http://60.205.141.66:5000/api/cms_articles/1","view_count":5,"title":"养生堂：名老中医话长寿\u2014\u2014李乾构","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/3.1478751476.632994.png","comment_count":0,"time":"2016-11-10 16:05:17","type":"名医视频"},{"self_url":"http://60.205.141.66:5000/api/cms_articles/3","view_count":7,"title":"新的文章-民家医案","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/3.1478759874.569355.png","comment_count":0,"time":"2016-11-10 14:40:49","type":"名家医案"},{"self_url":"http://60.205.141.66:5000/api/cms_articles/2","view_count":3,"title":"新的文章","cover_url":"http://60.205.141.66:5000/static/app_files/cms_article/upload/3.1478759163.744928.png","comment_count":0,"time":"2016-11-10 14:28:31","type":"名医视频"}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://60.205.141.66:5000/api/cms_articles/?per_page=30&page=1","total":5,"page":1,"first_url":"http://60.205.141.66:5000/api/cms_articles/?per_page=30&page=1"}
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
         * self_url : http://60.205.141.66:5000/api/cms_articles/5
         * view_count : 10
         * title : 中医名家——王位
         * cover_url : http://60.205.141.66:5000/static/app_files/cms_article/upload/1.1480298958.278660.jpg
         * comment_count : 0
         * time : 2016-11-28 10:49:37
         * type : 名医视频
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
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
