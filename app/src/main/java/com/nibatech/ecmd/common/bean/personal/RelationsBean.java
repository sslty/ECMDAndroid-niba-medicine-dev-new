package com.nibatech.ecmd.common.bean.personal;


import com.google.gson.annotations.SerializedName;

public class RelationsBean {
    /**
     * unfollow_url : http://139.217.8.207:5000/api/doctor_relation/unfollow/1
     * follow_url : http://139.217.8.207:5000/api/doctor_relation/follow/1
     * is_following : false
     * is_followed_by : true
     */

    @SerializedName("unfollow_url")
    private String unFollowUrl;
    @SerializedName("follow_url")
    private String followUrl;
    @SerializedName("is_following")
    private boolean isFollowing;
    @SerializedName("is_followed_by")
    private boolean isFollowedBy;

    public String getUnfollowUrl() {
        return unFollowUrl;
    }

    public void setUnfollowUrl(String unFollowUrl) {
        this.unFollowUrl = unFollowUrl;
    }

    public String getFollowUrl() {
        return followUrl;
    }

    public void setFollowUrl(String followUrl) {
        this.followUrl = followUrl;
    }

    public boolean isIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public boolean isIsFollowedBy() {
        return isFollowedBy;
    }

    public void setIsFollowedBy(boolean isFollowedBy) {
        this.isFollowedBy = isFollowedBy;
    }
}
