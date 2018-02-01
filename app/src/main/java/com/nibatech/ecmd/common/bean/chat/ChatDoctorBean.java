package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;

public class ChatDoctorBean {
    /**
     * gender : 男
     * cd_number : P111000024
     * avatar_url : null
     * age : 21
     * fullname : 李经理
     */

    private PatientBean patient;
    /**
     * gender : 男
     * cd_number : D161000001
     * avatar_url : null
     * fullname : 石智嘉
     * age : 33
     * doctor_type : 医院
     */

    private DoctorBean doctor;
    /**
     * patient : {"gender":"男","cd_number":"P111000024","avatar_url":null,"age":21,"fullname":"李经理"}
     * doctor : {"gender":"男","cd_number":"D161000001","avatar_url":null,"fullname":"石智嘉","age":33,"doctor_type":"医院"}
     * start_time : 2016-10-14T16:03:01
     * conversion_url : http://139.217.8.207:5000/api/online_treatments/22/conversions/
     * stages_url : http://139.217.8.207:5000/api/doctor_online_treatments/22/stages/
     * detail_url : http://139.217.8.207:5000/api/doctor_online_treatments/22
     * create_material_url : http://139.217.8.207:5000/api/doctor_online_treatments/22/stages/create_material
     */

    @SerializedName("start_time")
    private String startTime;
    @SerializedName("conversion_url")
    private String conversionUrl;
    @SerializedName("stages_url")
    private String stagesUrl;
    @SerializedName("detail_url")
    private String detailUrl;
    @SerializedName("create_material_url")
    private String createMaterialUrl;
    @SerializedName("guide_image_url")
    private String guideImageUrl;
    @SerializedName("guide_url")
    private String guideUrl;

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }

    public DoctorBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorBean doctor) {
        this.doctor = doctor;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getConversionUrl() {
        return conversionUrl;
    }

    public void setConversionUrl(String conversionUrl) {
        this.conversionUrl = conversionUrl;
    }

    public String getStagesUrl() {
        return stagesUrl;
    }

    public void setStagesUrl(String stagesUrl) {
        this.stagesUrl = stagesUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getCreateMaterialUrl() {
        return createMaterialUrl;
    }

    public void setCreateMaterialUrl(String createMaterialUrl) {
        this.createMaterialUrl = createMaterialUrl;
    }

    public String getGuideUrl() {
        return guideUrl;
    }

    public void setGuideUrl(String guideUrl) {
        this.guideUrl = guideUrl;
    }

    public String getGuideImageUrl() {
        return guideImageUrl;
    }

    public void setGuideImageUrl(String guideImageUrl) {
        this.guideImageUrl = guideImageUrl;
    }
}
