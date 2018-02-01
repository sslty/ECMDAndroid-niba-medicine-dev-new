package com.nibatech.ecmd.common.bean.common;

import com.google.gson.annotations.SerializedName;

public class APIsBean {

    /**
     * entrance_doctor_friends : http://139.217.8.207:5000/api/api_entrance_doctor_friends
     * entrance_patient_mine : http://139.217.8.207:5000/api/api_entrance_patient_mine
     * entrance_normal_search : http://139.217.8.207:5000/api/api_entrance_normal_search
     * entrance_excellent : http://139.217.8.207:5000/api/api_entrance_excellent
     * entrance_my_doctors : http://139.217.8.207:5000/api/api_entrance_my_doctors
     * entrance_doctor_patients : http://139.217.8.207:5000/api/api_entrance_doctor_friends
     * entrance_olt_tourist : http://139.217.8.207:5000/api/api_entrance_tourist_olt
     * entrance_doctor_mine : http://139.217.8.207:5000/api/api_entrance_doctor_mine
     * entrance_medicine : http://139.217.8.207:5000/api/api_entrance_medicine
     * entrance_olt_patient : http://139.217.8.207:5000/api/api_entrance_patient_olt
     * entrance_olt_doctor : http://139.217.8.207:5000/api/api_entrance_doctor_olt
     */

    @SerializedName("entrance_doctor_friends")
    private String entranceDoctorFriends;
    @SerializedName("entrance_patient_mine")
    private String entrancePatientMine;
    @SerializedName("entrance_normal_search")
    private String entranceNormalSearch;
    @SerializedName("entrance_excellent")
    private String entranceExcellent;
    @SerializedName("entrance_my_doctors")
    private String entranceMyDoctors;
    @SerializedName("entrance_doctor_patients")
    private String entranceDoctorPatients;
    @SerializedName("entrance_olt_tourist")
    private String entranceOltTourist;
    @SerializedName("entrance_doctor_mine")
    private String entranceDoctorMine;
    @SerializedName("entrance_medicine")
    private String entranceMedicine;
    @SerializedName("entrance_olt_patient")
    private String entranceOltPatient;
    @SerializedName("entrance_olt_doctor")
    private String entranceOltDoctor;

    public String getEntranceDoctorFriends() {
        return entranceDoctorFriends;
    }

    public void setEntranceDoctorFriends(String entranceDoctorFriends) {
        this.entranceDoctorFriends = entranceDoctorFriends;
    }

    public String getEntrancePatientMine() {
        return entrancePatientMine;
    }

    public void setEntrancePatientMine(String entrancePatientMine) {
        this.entrancePatientMine = entrancePatientMine;
    }

    public String getEntranceNormalSearch() {
        return entranceNormalSearch;
    }

    public void setEntranceNormalSearch(String entranceNormalSearch) {
        this.entranceNormalSearch = entranceNormalSearch;
    }

    public String getEntranceExcellent() {
        return entranceExcellent;
    }

    public void setEntranceExcellent(String entranceExcellent) {
        this.entranceExcellent = entranceExcellent;
    }

    public String getEntranceMyDoctors() {
        return entranceMyDoctors;
    }

    public void setEntranceMyDoctors(String entranceMyDoctors) {
        this.entranceMyDoctors = entranceMyDoctors;
    }

    public String getEntranceDoctorPatients() {
        return entranceDoctorPatients;
    }

    public void setEntranceDoctorPatients(String entranceDoctorPatients) {
        this.entranceDoctorPatients = entranceDoctorPatients;
    }

    public String getEntranceOltTourist() {
        return entranceOltTourist;
    }

    public void setEntranceOltTourist(String entranceOltTourist) {
        this.entranceOltTourist = entranceOltTourist;
    }

    public String getEntranceDoctorMine() {
        return entranceDoctorMine;
    }

    public void setEntranceDoctorMine(String entranceDoctorMine) {
        this.entranceDoctorMine = entranceDoctorMine;
    }

    public String getEntranceMedicine() {
        return entranceMedicine;
    }

    public void setEntranceMedicine(String entranceMedicine) {
        this.entranceMedicine = entranceMedicine;
    }

    public String getEntranceOltPatient() {
        return entranceOltPatient;
    }

    public void setEntranceOltPatient(String entranceOltPatient) {
        this.entranceOltPatient = entranceOltPatient;
    }

    public String getEntranceOltDoctor() {
        return entranceOltDoctor;
    }

    public void setEntranceOltDoctor(String entranceOltDoctor) {
        this.entranceOltDoctor = entranceOltDoctor;
    }
}
