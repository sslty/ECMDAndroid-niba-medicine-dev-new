package com.nibatech.ecmd.common.bean.news;

import com.google.gson.annotations.SerializedName;

public class NewsItemBean {

    /**
     * comment : http://139.217.8.207:5000/api/plat_dynamics/2/comment
     * view_count : 2
     * comment_count : 0
     * detail_url : http://139.217.8.207:5000/static/app_files/plat_dynamic/2.html
     * author : null
     * title : 这是平台动态22222
     * time : 2016-11-15 11:37:12
     * comment_list : http://139.217.8.207:5000/api/plat_dynamics/2/comments/
     */

    private String comment;
    @SerializedName("view_count")
    private int viewCount;
    @SerializedName("comment_count")
    private int commentCount;
    @SerializedName("detail_url")
    private String detailUrl;
    private Object author;
    private String title;
    private String time;
    @SerializedName("comment_list")
    private String commentList;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
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
}
