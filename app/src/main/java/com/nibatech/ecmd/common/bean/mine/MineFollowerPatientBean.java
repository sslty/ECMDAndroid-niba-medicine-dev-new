package com.nibatech.ecmd.common.bean.mine;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.PatientProfileBean;

import java.util.List;

public class MineFollowerPatientBean {

    private List<Patient> patients;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public static class Patient {
        /**
         * patient_profile : {"gender":"女","age":28,"avatar_url":"http://60.205.141.66:5000/static/app_files/avatars/5/5.1480059334.508876.jpg","fullname":"杨曦","cd_number":"P061000002"}
         */

        @SerializedName("patient_profile")
        private PatientProfileBean patientProfile;

        public PatientProfileBean getPatientProfile() {
            return patientProfile;
        }

        public void setPatientProfile(PatientProfileBean patientProfile) {
            this.patientProfile = patientProfile;
        }
    }
}
