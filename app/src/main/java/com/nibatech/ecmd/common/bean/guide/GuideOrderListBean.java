package com.nibatech.ecmd.common.bean.guide;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PagesBean;

import java.util.List;

public class GuideOrderListBean {

    /**
     * online_treatments : [{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/146","patient":{"gender":"女","cd_number":"P011000153","avatar_url":null,"age":22,"fullname":"可口可乐了"},"description":"vv观后感","start_time":"2016-12-07T16:31:30","updated_time":"2016-12-07T16:31:30","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-12-07T16:27:57","free_first_time":"False","expense":11,"symptom":"哥哥","doctor_profile":{"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1481184126.691076.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/142","patient":{"gender":"男","cd_number":"P111000156","avatar_url":null,"age":11,"fullname":"新婆婆"},"description":"民工","start_time":"2016-11-28T14:21:15","updated_time":"2016-11-28T14:21:15","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-11-28T14:20:40","free_first_time":"False","expense":11,"symptom":"姑姑","doctor_profile":{"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/14/14.1481191840.440692.jpeg","fullname":"小发吧谷歌","excellent":false,"specialism":"全科","gender":"男","age":69,"verified":true,"cd_number":"D011000004","doctor_type":"医院"}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/131","patient":{"gender":"男","cd_number":"P111000152","avatar_url":null,"age":11,"fullname":"明龙"},"description":"新坡undue","start_time":"2016-11-23T18:02:04","updated_time":"2016-11-23T18:02:04","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-11-23T17:57:58","free_first_time":"False","expense":11,"symptom":"哄你","doctor_profile":{"avatar_url":null,"fullname":"迷宫","excellent":false,"specialism":"全科","gender":"男","age":20,"verified":false,"cd_number":"D111000061","doctor_type":"医院"}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/130","patient":{"gender":"男","cd_number":"P111000151","avatar_url":null,"age":11,"fullname":"以一种"},"description":"明","start_time":"2016-11-23T12:06:48","updated_time":"2016-11-23T12:06:48","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-11-23T12:06:05","free_first_time":"False","expense":11,"symptom":"一名","doctor_profile":{"avatar_url":null,"fullname":"给我送女","excellent":false,"specialism":"全科","gender":"男","age":11,"verified":false,"cd_number":"D111000060","doctor_type":null}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/129","patient":{"gender":"男","cd_number":"P111000150","avatar_url":null,"age":11,"fullname":"图来看看"},"description":"图来看看咯","start_time":"2016-11-23T10:31:22","updated_time":"2016-11-23T10:31:22","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-11-23T10:20:35","free_first_time":"False","expense":11,"symptom":"考虑考虑","doctor_profile":{"avatar_url":null,"fullname":"哦搜搜哦","excellent":false,"specialism":"全科","gender":"男","age":11,"verified":false,"cd_number":"D111000059","doctor_type":null}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/114","patient":{"gender":"男","cd_number":"P111000002","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/9/9.1478844760.826611.jpg","age":33,"fullname":"星星"},"description":"fdafdsaff","start_time":"2016-11-11T16:09:57","updated_time":"2016-11-11T16:09:57","end_time":null,"formatted_info":{"max_expense":22,"min_expense":22,"participated":null,"doctor_count":1,"stage_count":1},"created_time":"2016-11-11T14:09:56","free_first_time":"False","expense":22,"symptom":"dafdsaf","doctor_profile":{"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1481184126.691076.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}},{"self_url":"http://139.217.8.207:5000/api/patient_online_treatments/109","patient":{"gender":"男","cd_number":"P111000129","avatar_url":null,"age":11,"fullname":"工地"},"description":"ing哦尼","start_time":"2016-11-09T15:47:11","updated_time":"2016-11-09T15:47:11","end_time":null,"formatted_info":{"max_expense":11,"min_expense":11,"participated":null,"doctor_count":1,"stage_count":10},"created_time":"2016-11-09T15:44:50","free_first_time":"False","expense":11,"symptom":"ing哦尼","doctor_profile":{"avatar_url":null,"fullname":"和弟弟","excellent":false,"specialism":"全科","gender":"男","age":11,"verified":false,"cd_number":"D111000052","doctor_type":null}}]
     * pages : {"prev_url":null,"pages":1,"next_url":null,"per_page":30,"last_url":"http://139.217.8.207:5000/api/patient_online_treatments/others_treating/?per_page=30&page=1","total":7,"page":1,"first_url":"http://139.217.8.207:5000/api/patient_online_treatments/others_treating/?per_page=30&page=1"}
     */

    private PagesBean pages;
    @SerializedName("online_treatments")
    private List<OnlineTreatmentBean> onlineTreatments;

    public PagesBean getPages() {
        return pages;
    }

    public void setPages(PagesBean pages) {
        this.pages = pages;
    }

    public List<OnlineTreatmentBean> getOnlineTreatments() {
        return onlineTreatments;
    }

    public void setOnlineTreatments(List<OnlineTreatmentBean> onlineTreatments) {
        this.onlineTreatments = onlineTreatments;
    }
}
