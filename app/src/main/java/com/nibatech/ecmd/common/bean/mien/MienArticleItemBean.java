package com.nibatech.ecmd.common.bean.mien;

import com.google.gson.annotations.SerializedName;

public class MienArticleItemBean {

    /**
     * comment : http://60.205.141.66:5000/api/cms_articles/5/comment
     * view_count : 11
     * comment_count : 0
     * detail_url : http://60.205.141.66:5000/static/app_files/cms_article/5.html
     * author : 大秦
     * title : 中医名家——王位
     * time : 2016-11-28 10:49:37
     * type : 名医视频
     * comment_list : http://60.205.141.66:5000/api/cms_articles/5/comment_list
     */

    private String comment;
    @SerializedName("view_count")
    private int viewCount;
    @SerializedName("comment_count")
    private int commentCount;
    @SerializedName("detail_url")
    private String detailUrl;
    private String author;
    private String title;
    private String time;
    private String type;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommentList() {
        return commentList;
    }

    public void setCommentList(String commentList) {
        this.commentList = commentList;
    }
}
