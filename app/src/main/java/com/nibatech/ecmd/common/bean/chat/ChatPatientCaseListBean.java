package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorBean;
import com.nibatech.ecmd.common.bean.common.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;

import java.util.List;

public class ChatPatientCaseListBean {


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
     * description : 苍井空
     * doctor : null
     * start_time : null
     * updated_time : 2016-10-28T18:58:25
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.440386.webp","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.440873.jpg","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.441140.jpg","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.448444.webp","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.448682.webp","name":null},{"image_url":"http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.448929.webp","name":null}]
     * end_time : null
     * created_time : 2016-10-28T18:58:25
     * free_first_time : null
     * expense : 0
     * offer_url : http://139.217.8.207:5000/api/doctor_online_treatments/89/offer
     * symptom : 八菱科技
     * formatted_info : {"max_expense":0,"min_expense":0,"participated":false,"doctor_count":0,"stage_count":0}
     */

    private String description;
    private DoctorBean doctor;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("updated_time")
    private String updatedTime;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("created_time")
    private String createdTime;
    @SerializedName("free_first_time")
    private String freeFirstTime;
    private int expense;
    @SerializedName("offer_url")
    private String offerUrl;
    private String symptom;
    /**
     * max_expense : 0
     * min_expense : 0
     * participated : false
     * doctor_count : 0
     * stage_count : 0
     */

    @SerializedName("formatted_info")
    private FormattedInfoBean formattedInfo;
    /**
     * image_url : http://139.217.8.207:5000/static/app_files/online_treatment/89/data/89.1477652305.440386.webp
     * name : null
     */

    private List<ImageNameBean> images;

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getFreeFirstTime() {
        return freeFirstTime;
    }

    public void setFreeFirstTime(String freeFirstTime) {
        this.freeFirstTime = freeFirstTime;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public FormattedInfoBean getFormattedInfo() {
        return formattedInfo;
    }

    public void setFormattedInfo(FormattedInfoBean formattedInfo) {
        this.formattedInfo = formattedInfo;
    }

    public List<ImageNameBean> getImages() {
        return images;
    }

    public void setImages(List<ImageNameBean> images) {
        this.images = images;
    }


}
