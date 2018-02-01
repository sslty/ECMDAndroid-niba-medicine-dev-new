package com.nibatech.ecmd.common.bean.common;


import com.google.gson.annotations.SerializedName;

public class SearchBean {

    /**
     * cms_article_dynamic_query_url : http://139.217.8.207:5000/api/cms_articles/中医风采
     * medicine_query_url : http://139.217.8.207:5000/api/medicines/
     * excellent_query_url : http://139.217.8.207:5000/api/indications/search
     * cms_encyclopaedic_query_url : null encyclopedia
     * plat_dynamic_query_url : http://139.217.8.207:5000/api/plat_dynamics/平台动态
     * doctor_article_query_url : http://139.217.8.207:5000/api/doctor_articles/中医圈
     * medicine_legacy_url : null
     * olt_query_url : http://139.217.8.207:5000/api/doctor_online_treatments/search
     */

    @SerializedName("cms_article_dynamic_query_url")
    private String cmsArticleDynamicQueryUrl;
    @SerializedName("medicine_query_url")
    private String medicineQueryUrl;
    @SerializedName("excellent_query_url")
    private String excellentQueryUrl;
    @SerializedName("cms_encyclopaedic_query_url")
    private String cmsEncyclopaedicQueryUrl;
    @SerializedName("plat_dynamic_query_url")
    private String platDynamicQueryUrl;
    @SerializedName("doctor_article_query_url")
    private String doctorArticleQueryUrl;
    @SerializedName("medicine_legacy_url")
    private String medicineLegacyUrl;
    @SerializedName("olt_query_url")
    private String oltQueryUrl;

    public String getCmsArticleDynamicQueryUrl() {
        return cmsArticleDynamicQueryUrl;
    }

    public void setCmsArticleDynamicQueryUrl(String cmsArticleDynamicQueryUrl) {
        this.cmsArticleDynamicQueryUrl = cmsArticleDynamicQueryUrl;
    }

    public String getMedicineQueryUrl() {
        return medicineQueryUrl;
    }

    public void setMedicineQueryUrl(String medicineQueryUrl) {
        this.medicineQueryUrl = medicineQueryUrl;
    }

    public String getExcellentQueryUrl() {
        return excellentQueryUrl;
    }

    public void setExcellentQueryUrl(String excellentQueryUrl) {
        this.excellentQueryUrl = excellentQueryUrl;
    }

    public String getCmsEncyclopaedicQueryUrl() {
        return cmsEncyclopaedicQueryUrl;
    }

    public void setCmsEncyclopaedicQueryUrl(String cmsEncyclopaedicQueryUrl) {
        this.cmsEncyclopaedicQueryUrl = cmsEncyclopaedicQueryUrl;
    }

    public String getPlatDynamicQueryUrl() {
        return platDynamicQueryUrl;
    }

    public void setPlatDynamicQueryUrl(String platDynamicQueryUrl) {
        this.platDynamicQueryUrl = platDynamicQueryUrl;
    }

    public String getDoctorArticleQueryUrl() {
        return doctorArticleQueryUrl;
    }

    public void setDoctorArticleQueryUrl(String doctorArticleQueryUrl) {
        this.doctorArticleQueryUrl = doctorArticleQueryUrl;
    }

    public String getMedicineLegacyUrl() {
        return medicineLegacyUrl;
    }

    public void setMedicineLegacyUrl(String medicineLegacyUrl) {
        this.medicineLegacyUrl = medicineLegacyUrl;
    }

    public String getOltQueryUrl() {
        return oltQueryUrl;
    }

    public void setOltQueryUrl(String oltQueryUrl) {
        this.oltQueryUrl = oltQueryUrl;
    }
}
