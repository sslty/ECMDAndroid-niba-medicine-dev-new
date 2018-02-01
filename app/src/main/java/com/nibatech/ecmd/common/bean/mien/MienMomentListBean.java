package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class MienMomentListBean {
    /**
     * articles : [{"self_url":"http://139.217.8.207:5000/api/doctor_articles/14","view_count":5,"doctor":{"gender":"女","avatar_url":null,"name":"abcdefg"},"title":"爸爸","comment_count":0,"time":"2016-11-24 16:46:52"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/13","view_count":0,"doctor":{"gender":"女","avatar_url":null,"name":"abcdefg"},"title":"可口可乐了","comment_count":0,"time":"2016-11-24 16:46:23"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/12","view_count":1,"doctor":{"gender":"女","avatar_url":null,"name":"abcdefg"},"title":"兔兔","comment_count":0,"time":"2016-11-24 16:45:02"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/11","view_count":0,"doctor":{"gender":"女","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1477972506.342093.jpg","name":"小医生"},"title":"我想喝杯酒","comment_count":0,"time":"2016-11-17 09:42:17"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/9","view_count":0,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"明","comment_count":0,"time":"2016-11-16 09:52:02"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/8","view_count":2,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"fdafdfdsaff","comment_count":0,"time":"2016-11-15 17:54:54"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/7","view_count":2,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"啦啦","comment_count":0,"time":"2016-11-15 17:44:14"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/6","view_count":0,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"第一次怎样","comment_count":0,"time":"2016-11-15 17:04:51"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/5","view_count":1,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"4名父母旅行","comment_count":0,"time":"2016-11-15 16:16:36"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/3","view_count":0,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"骷髅精灵","comment_count":0,"time":"2016-11-15 14:36:58"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/2","view_count":2,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"骷髅精灵","comment_count":0,"time":"2016-11-15 14:34:41"},{"self_url":"http://139.217.8.207:5000/api/doctor_articles/1","view_count":2,"doctor":{"gender":"男","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1478080749.226353.jpg","name":"小发吧谷歌"},"title":"屠龙记","comment_count":0,"time":"2016-11-15 14:32:34"}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://139.217.8.207:5000/api/doctor_articles/?per_page=30&page=1","total":12,"page":1,"first_url":"http://139.217.8.207:5000/api/doctor_articles/?per_page=30&page=1"}
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
         * self_url : http://139.217.8.207:5000/api/doctor_articles/14
         * view_count : 5
         * doctor : {"gender":"女","avatar_url":null,"name":"abcdefg"}
         * title : 爸爸
         * comment_count : 0
         * time : 2016-11-24 16:46:52
         */

        @SerializedName("self_url")
        private String selfUrl;
        @SerializedName("view_count")
        private int viewCount;
        private Doctor doctor;
        private String title;
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

        public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public static class Doctor {
            /**
             * gender : 女
             * avatar_url : null
             * name : abcdefg
             */

            private String gender;
            @SerializedName("avatar_url")
            private Object avatarUrl;
            private String name;

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public Object getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(Object avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
