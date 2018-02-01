package com.nibatech.ecmd.common.bean.sea;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class SeaListBean {
    /**
     * prev_url : null
     * pages : 1
     * next_url : null
     * per_page : 30
     * last_url : http://139.217.8.207:5000/api/medicine_legacy/?per_page=30&page=1
     * total : 1
     * page : 1
     * first_url : http://139.217.8.207:5000/api/medicine_legacy/?per_page=30&page=1
     */

    private PagesBean pages;
    /**
     * self_url : http://139.217.8.207:5000/api/medicine_legacy/1
     * quote : 秦始皇大典
     * time : 2016-10-27 12:12:13
     * short_desc : 你好，秦始皇发送到发送到
     * title : 长生不老药
     */

    @SerializedName("medicine_legacy")
    private List<MedicineLegacy> medicineLegacy;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<MedicineLegacy> getMedicineLegacy() {
        return medicineLegacy;
    }

    public void setMedicineLegacy(List<MedicineLegacy> medicineLegacy) {
        this.medicineLegacy = medicineLegacy;
    }

    public static class MedicineLegacy {
        @SerializedName("self_url")
        private String selfUrl;
        private String quote;
        private String time;
        @SerializedName("short_desc")
        private String shortDesc;
        private String title;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
