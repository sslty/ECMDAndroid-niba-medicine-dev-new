package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MomentArticleItemBean {

    /**
     * content : 聊几句看见了咯啊可口可乐了
     * view_count : 6
     * comment_count : 0
     * doctor : {"gender":"女","avatar_url":null,"name":"abcdefg"}
     * title : 爸爸
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.222659.jpg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.223001.jpg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.223221.jpg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.223442.jpg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.223666.jpeg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.223852.jpeg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.224154.jpeg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.224386.jpg"},{"image_url":"http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.224615.jpg"}]
     * time : 2016-11-24 16:46:52
     * comment_list : http://139.217.8.207:5000/api/doctor_articles/14/comment_list
     * comment : http://139.217.8.207:5000/api/doctor_articles/14/comment
     */

    private String content;
    @SerializedName("view_count")
    private int viewCount;
    @SerializedName("comment_count")
    private int commentCount;
    private Doctor doctor;
    private String title;
    private String time;
    @SerializedName("comment_list")
    private String commentList;
    private String comment;
    private List<Image> images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommentList() {
        return commentList;
    }

    public void setCommentList(String commentList) {
        this.commentList = commentList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public static class Doctor {
        /**
         * gender : 女
         * avatar_url : null
         * name : abcdefg
         */

        private String gender;
        @SerializedName("avatar_url")
        private String avatarUrl;
        private String name;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Image {
        /**
         * image_url : http://139.217.8.207:5000/static/app_files/doctor_article/14/14.1479977212.222659.jpg
         */

        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
