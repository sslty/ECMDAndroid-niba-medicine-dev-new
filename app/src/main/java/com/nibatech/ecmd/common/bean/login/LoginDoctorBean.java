package com.nibatech.ecmd.common.bean.login;

import io.realm.RealmObject;

public class LoginDoctorBean extends RealmObject{
    /**
     * approved : true
     * excellent : null
     * hospital : 西安市中医院无人间
     * specialism : 全科
     */
    private boolean approved;
    private String excellent;
    private String hospital;
    private String specialism;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getExcellent() {
        return excellent;
    }

    public void setExcellent(String excellent) {
        this.excellent = excellent;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }
}
