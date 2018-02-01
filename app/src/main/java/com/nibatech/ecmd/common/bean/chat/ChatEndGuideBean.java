package com.nibatech.ecmd.common.bean.chat;


import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;

import java.util.List;

public class ChatEndGuideBean {
    /**
     * effects : [{"id":2,"name":"恢复"},{"id":3,"name":"显效"},{"id":4,"name":"有效"},{"id":5,"name":"无效"}]
     * doctor : {"avatar_url":"http://139.217.8.207:5000/static/app_files/avatars/7/7.1477972506.342093.jpg","fullname":"小医生","excellent":false,"specialism":"儿科","gender":"女","age":30,"verified":true,"cd_number":"D161000001","doctor_type":"医院"}
     */

    private DoctorProfileBean doctor;
    private List<Effect> effects;

    public DoctorProfileBean getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorProfileBean doctor) {
        this.doctor = doctor;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public static class Effect {
        /**
         * id : 2
         * name : 恢复
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
