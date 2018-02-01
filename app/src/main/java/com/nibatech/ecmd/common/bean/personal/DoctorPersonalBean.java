package com.nibatech.ecmd.common.bean.personal;


import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.EffectsBean;

public class DoctorPersonalBean {
    /**
     * assigned_olt : {"treating_status":"http://139.217.8.207:5000/api/patient_online_treatments/treating_status","create_assigned_olt_url":"http://139.217.8.207:5000/api/patient_online_treatments/assigned/1"}
     * relations : {"unfollow_url":"http://139.217.8.207:5000/api/patient_followed/unfollow/1","follow_url":"http://139.217.8.207:5000/api/patient_followed/follow/1","is_following":true,"is_followed_by":false}
     * doctor_profile : {"homepage_url":"http://139.217.8.207:5000/api/doctor_homepage/1","avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1481184126.691076.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}
     * success : true
     * effects : {"remarkable_rate":0,"recover":0,"lost":0,"remarkable":0,"processing":1,"valid":0,"invalid":0,"delete":0}
     */

    @SerializedName("assigned_olt")
    private AssignedOltBean assignedOlt;
    private RelationsBean relations;
    @SerializedName("doctor_profile")
    private DoctorProfileBean doctorProfile;
    private boolean success;
    private EffectsBean effects;

    public AssignedOltBean getAssignedOlt() {
        return assignedOlt;
    }

    public void setAssignedOlt(AssignedOltBean assignedOlt) {
        this.assignedOlt = assignedOlt;
    }

    public RelationsBean getRelations() {
        return relations;
    }

    public void setRelations(RelationsBean relations) {
        this.relations = relations;
    }

    public DoctorProfileBean getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfileBean doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public EffectsBean getEffects() {
        return effects;
    }

    public void setEffects(EffectsBean effects) {
        this.effects = effects;
    }
}
