package com.nibatech.ecmd.common.bean.login;

import com.google.gson.annotations.SerializedName;


public class LoginBean {

    /**
     * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZGVudGl0eSI6MTUyLCJpYXQiOjE0ODIzNzE1NzEsIm5iZiI6MTQ4MjM3MTU3MSwiZXhwIjoxNDg3NTU1NTcxfQ._N9AGVzR8yA-_S08FdvbjYUEPrGFXhPovqXDLAomI4I
     * user : {"age":11,"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg","cd_number":"D111000038","cellphone":"13396356329","city":"北京市","doctor":{"age":11,"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg","cd_number":"D111000038","doctor_type":"医院","excellent":false,"fullname":"你地几时","gender":"女","homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/38","specialism":"全科","verified":false},"fullname":"你地几时","gender":"女","patient":null,"role":"doctor","self_url":"http://139.217.8.207:5000/api/users/152","status":true,"tls_sig":"eJxlz01PgzAYwPH7PgXhOiN9CoVp4gUZGQFMFIiZF4JtweqEBiogi9-diUsk8fz-5Xk5rjRN09MouSwobT5qlatPyXXtWtORfvEXpRQsL1Rutuxf5KMULc*LUvF2jkAIwQgtjWC8VqIUZ*EBwAkgc7MwHXvL50W-Q6xTBwuIvSSimmO8zW6De4*GtG4Tz0nUzs-uxix4zA4RHkJj3O9e3fFAww5P5kSNYAiqPdhSssLp4-DBrkY-ig3HKvt1-7J202eo2VM-*dvUHZr4ZrFSiXd*PmgDcIUIwova87YTTT0DjIAANn-*Qvrqa-UN26pdUQ__\n","user_id":152}
     */

    @SerializedName("access_token")
    private String accessToken;
    private UserBean user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}


