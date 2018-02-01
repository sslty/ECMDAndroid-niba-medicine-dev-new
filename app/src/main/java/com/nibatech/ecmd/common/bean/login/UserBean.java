package com.nibatech.ecmd.common.bean.login;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import io.realm.RealmObject;

public class UserBean extends RealmObject {
    /**
     * age : 11
     * avatar_url : http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg
     * cd_number : D111000038
     * cellphone : 13396356329
     * city : 北京市
     * doctor : {"age":11,"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg","cd_number":"D111000038","doctor_type":"医院","excellent":false,"fullname":"你地几时","gender":"女","homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/38","specialism":"全科","verified":false}
     * fullname : 你地几时
     * gender : 女
     * patient : null
     * role : doctor
     * self_url : http://139.217.8.207:5000/api/users/152
     * status : true
     * tls_sig : eJxlz01PgzAYwPH7PgXhOiN9CoVp4gUZGQFMFIiZF4JtweqEBiogi9-diUsk8fz-5Xk5rjRN09MouSwobT5qlatPyXXtWtORfvEXpRQsL1Rutuxf5KMULc*LUvF2jkAIwQgtjWC8VqIUZ*EBwAkgc7MwHXvL50W-Q6xTBwuIvSSimmO8zW6De4*GtG4Tz0nUzs-uxix4zA4RHkJj3O9e3fFAww5P5kSNYAiqPdhSssLp4-DBrkY-ig3HKvt1-7J202eo2VM-*dvUHZr4ZrFSiXd*PmgDcIUIwova87YTTT0DjIAANn-*Qvrqa-UN26pdUQ__
     * <p>
     * user_id : 152
     */

    private int age;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("cd_number")
    private String cdNumber;
    @SerializedName("cellphone")
    private String cellPhone;
    private String city;
    private DoctorProfileBean doctor;
    @SerializedName("fullname")
    private String fullName;
    private String gender;
    private LoginPatientIdBean patient;
    private String role;
    @SerializedName("self_url")
    private String selfUrl;
    private boolean status;
    @SerializedName("tls_sig")
    private String tlsSig;
    @SerializedName("user_id")
    private int userId;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DoctorProfileBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorProfileBean doctor) {
        this.doctor = doctor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LoginPatientIdBean getPatient() {
        return patient;
    }

    public void setPatient(LoginPatientIdBean patient) {
        this.patient = patient;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTlsSig() {
        return tlsSig;
    }

    public void setTlsSig(String tlsSig) {
        this.tlsSig = tlsSig;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}


