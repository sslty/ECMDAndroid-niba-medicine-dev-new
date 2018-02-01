package com.nibatech.ecmd.common.bean.guide;


import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;

public class OnlineTreatmentBean {
    /**
     * self_url : http://139.217.8.207:5000/api/patient_online_treatments/228
     * patient : {"gender":"女","cd_number":"P011000176","avatar_url":null,"age":54,"fullname":"兔兔"}
     * description : 兔兔
     * order_type : 1
     * start_time : null
     * updated_time : 2016-12-16T14:06:18
     * doctor_profile : {"homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/1","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1481184126.691076.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}
     * end_time : null
     * formatted_info : {"max_expense":0,"min_expense":0,"participated":null,"doctor_count":0,"stage_count":0}
     * created_time : 2016-12-16T14:06:18
     * free_first_time : null
     * expense : 0
     * symptom : 看见了咯
     * pay_info : {"payed":true,"weixin_pay_unified_order_url":null,"weixin_pay_query_order_url":null}
     */

    @SerializedName("self_url")
    private String selfUrl;
    private PatientBean patient;
    private String description;
    @SerializedName("order_type")
    private int orderType;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("updated_time")
    private String updatedTime;
    @SerializedName("doctor_profile")
    private DoctorProfileBean doctorProfile;
    @SerializedName("end_time")
    private String endTime;
    @SerializedName("formatted_info")
    private FormattedInfoBean formattedInfo;
    @SerializedName("created_time")
    private String createdTime;
    @SerializedName("free_first_time")
    private boolean freeFirstTime;
    private int expense;
    private String symptom;
    @SerializedName("pay_info")
    private PayInfoBean payInfo;

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public DoctorProfileBean getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfileBean doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public FormattedInfoBean getFormattedInfo() {
        return formattedInfo;
    }

    public void setFormattedInfo(FormattedInfoBean formattedInfo) {
        this.formattedInfo = formattedInfo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isFreeFirstTime() {
        return freeFirstTime;
    }

    public void setFreeFirstTime(boolean freeFirstTime) {
        this.freeFirstTime = freeFirstTime;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }

}
