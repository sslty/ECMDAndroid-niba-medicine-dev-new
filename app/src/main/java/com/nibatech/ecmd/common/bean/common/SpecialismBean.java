package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpecialismBean {
    @SerializedName("specialisms")
    private ArrayList<SpecialismList> specialismList;

    public class SpecialismList {
        private int id;
        private String name;

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    public ArrayList<SpecialismList> getSpecialismList() {
        return this.specialismList;
    }
}
