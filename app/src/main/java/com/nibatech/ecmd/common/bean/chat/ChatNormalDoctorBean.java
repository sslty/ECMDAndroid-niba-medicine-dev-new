package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;

public class ChatNormalDoctorBean {
    /**
     * gender : 男
     * cd_number : P111000096
     * avatar_url : null
     * age : 32
     * fullname : 没流量了
     */

    private PatientBean patient;
    /**
     * patient : {"gender":"男","cd_number":"P111000096","avatar_url":null,"age":32,"fullname":"没流量了"}
     * conversion_url : http://139.217.8.207:5000/api/offline_patients/8/conversions/
     * doctor : {"gender":"男","cd_number":"D011000004","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1477652899.993724.jpg","age":69,"fullname":"小发吧谷歌"}
     */

    @SerializedName("conversion_url")
    private String conversionUrl;
    /**
     * gender : 男
     * cd_number : D011000004
     * avatar_url : http://139.217.8.207:5000/static/app_files/avatars/14/14.1477652899.993724.jpg
     * age : 69
     * fullname : 小发吧谷歌
     */

    private DoctorBean doctor;

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }

    public String getConversionUrl() {
        return conversionUrl;
    }

    public void setConversionUrl(String conversionUrl) {
        this.conversionUrl = conversionUrl;
    }

    public DoctorBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorBean doctor) {
        this.doctor = doctor;
    }
}
