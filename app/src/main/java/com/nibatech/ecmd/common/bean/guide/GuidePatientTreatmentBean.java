package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;

public class GuidePatientTreatmentBean {
    /**
     * online_treatment : {"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/228","patient":{"gender":"女","cd_number":"P011000176","avatar_url":null,"age":54,"fullname":"兔兔"},"description":"兔兔","order_type":1,"start_time":null,"updated_time":"2016-12-16T14:06:18","doctor_profile":{"homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/1","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1481184126.691076.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"},"end_time":null,"formatted_info":{"max_expense":0,"min_expense":0,"participated":null,"doctor_count":0,"stage_count":0},"created_time":"2016-12-16T14:06:18","free_first_time":null,"expense":0,"symptom":"看见了咯","pay_info":{"payed":true,"weixin_pay_unified_order_url":null,"weixin_pay_query_order_url":null}}
     * success : true
     */

    @SerializedName("online_treatment")
    private OnlineTreatmentBean onlineTreatment;
    private boolean success;

    public OnlineTreatmentBean getOnlineTreatment() {
        return onlineTreatment;
    }

    public void setOnlineTreatment(OnlineTreatmentBean onlineTreatment) {
        this.onlineTreatment = onlineTreatment;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}