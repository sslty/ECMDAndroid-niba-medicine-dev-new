package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerBean {
    private List<Banner> banners;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public static class Banner {
        /**
         * link_url : 副科级打算减肥离开减肥倒计时
         * image_url : http://139.217.8.207:5000/static/app_files/banners/upload/1.1479180689.325233.jpg
         * title : 发酵粉大家发链接
         */

        @SerializedName("link_url")
        private String linkUrl;
        @SerializedName("image_url")
        private String imageUrl;
        private String title;

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
