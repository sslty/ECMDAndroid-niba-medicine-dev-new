package com.nibatech.ecmd.common.realm.object;

import io.realm.RealmObject;


public class SearchKeyObject extends RealmObject {
    private String keyword;
    private int id;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
