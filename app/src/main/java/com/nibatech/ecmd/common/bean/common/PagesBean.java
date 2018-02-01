package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;


public class PagesBean {
    @SerializedName("prev_url")
    private String prevUrl;
    private int pages;
    @SerializedName("next_url")
    private String nextUrl;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("last_url")
    private String lastUrl;
    private int total;
    private int page;
    @SerializedName("first_url")
    private String firstUrl;

    public String getPrevUrl() {
        return prevUrl;
    }

    public int getPages() {
        return pages;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public String getFirstUrl() {
        return firstUrl;
    }
}
