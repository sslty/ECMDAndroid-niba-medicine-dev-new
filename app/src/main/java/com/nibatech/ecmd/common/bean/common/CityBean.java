package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityBean {
    @SerializedName("cities")
    private List<CityList> cityList;
    public class CityList {
        private String code;
        private int id;
        private String name;

        public String getCode() {
            return this.code;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    public List<CityList> getCityList() {
        return this.cityList;
    }
}
