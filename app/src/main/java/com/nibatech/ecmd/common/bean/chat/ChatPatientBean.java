package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.bean.guide.PayInfoBean;

public class ChatPatientBean {
    /**
     * conversion_url : http://139.217.8.207:5000/api/online_treatments/276/conversions/
     * doctor : {"gender":"女","cd_number":"D111000038","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/152/152.1481190808.841979.jpg","age":11,"fullname":"你地几时"}
     * new_stage_url : http://139.217.8.207:5000/api/patient_online_treatments/my_treating/new_stage
     * end_stage_url : http://139.217.8.207:5000/api/patient_online_treatments/my_treating/end
     * stages_url : http://139.217.8.207:5000/api/patient_online_treatments/276/stages/
     * patient : {"gender":"女","cd_number":"P011000182","avatar_url":null,"age":21,"fullname":"可口可乐了"}
     * detail_url : http://139.217.8.207:5000/api/patient_online_treatments/276
     * start_time : 2016-12-20T17:33:55
     * pay_info : {"payed":true,"weixin_pay_unified_order_url":"http://139.217.8.207:5000/api/weixin/pay/unified_order/online_treatment/276","weixin_pay_query_order_url":"http://139.217.8.207:5000/api/weixin/pay/query_order/online_treatment/276"}
     */

    @SerializedName("conversion_url")
    private String conversionUrl;
    private DoctorBean doctor;
    @SerializedName("new_stage_url")
    private String newStageUrl;
    @SerializedName("end_stage_url")
    private String endStageUrl;
    @SerializedName("stages_url")
    private String stagesUrl;
    private PatientBean patient;
    @SerializedName("detail_url")
    private String detailUrl;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("pay_info")
    private PayInfoBean payInfo;

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

    public String getNewStageUrl() {
        return newStageUrl;
    }

    public void setNewStageUrl(String newStageUrl) {
        this.newStageUrl = newStageUrl;
    }

    public String getEndStageUrl() {
        return endStageUrl;
    }

    public void setEndStageUrl(String endStageUrl) {
        this.endStageUrl = endStageUrl;
    }

    public String getStagesUrl() {
        return stagesUrl;
    }

    public void setStagesUrl(String stagesUrl) {
        this.stagesUrl = stagesUrl;
    }

    public PatientBean getPatient() {
        return patient;
    }

    public void setPatient(PatientBean patient) {
        this.patient = patient;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }
}
