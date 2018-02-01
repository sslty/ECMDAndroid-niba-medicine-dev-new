package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrizeHistoryRoundBean {
    /**
     * name : 第一期有奖病例分析
     * end_date : 2016-10-19
     * bonus : 5300
     * gender : 男
     * age : 42
     * detail : 说话不算数啊
     * result : {"bonus_per_doctor":0,"doctor_right":0,"doctor_total":0}
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477387995.471152.jpg","name":"侧面舌照"},{"image_url":"http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477387972.241264.png","name":"正面舌照"}]
     * start_date : 2016-10-17
     * description : 病入膏肓无可救药
     */

    @SerializedName("case_game")
    private CaseGame caseGame;
    /**
     * self_url : http://139.217.8.207:5000/api/case_game_rounds/3
     * end_date : 2016-10-19
     * name : 第3轮
     * doctor_right : 0
     * processing : false
     * start_date : 2016-10-19
     * doctor_total : 0
     */

    private List<PrizeRoundBean> rounds;

    public CaseGame getCaseGame() {
        return caseGame;
    }

    public void setCaseGame(CaseGame caseGame) {
        this.caseGame = caseGame;
    }

    public List<PrizeRoundBean> getRounds() {
        return rounds;
    }

    public void setRounds(List<PrizeRoundBean> rounds) {
        this.rounds = rounds;
    }

    public static class CaseGame {
        private String name;
        @SerializedName("end_date")
        private String endDate;
        private int bonus;
        private String gender;
        private String age;
        private String detail;
        /**
         * bonus_per_doctor : 0
         * doctor_right : 0
         * doctor_total : 0
         */

        private Result result;
        @SerializedName("start_date")
        private String startDate;
        private String description;


        private List<PrizeImageBean> images;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<PrizeImageBean> getImages() {
            return images;
        }

        public void setImages(List<PrizeImageBean> images) {
            this.images = images;
        }

        public static class Result {
            @SerializedName("bonus_per_doctor")
            private int bonusPerDoctor;
            @SerializedName("doctor_right")
            private int doctorRight;
            @SerializedName("doctor_total")
            private int doctorTotal;

            public int getBonusPerDoctor() {
                return bonusPerDoctor;
            }

            public void setBonusPerDoctor(int bonusPerDoctor) {
                this.bonusPerDoctor = bonusPerDoctor;
            }

            public int getDoctorRight() {
                return doctorRight;
            }

            public void setDoctorRight(int doctorRight) {
                this.doctorRight = doctorRight;
            }

            public int getDoctorTotal() {
                return doctorTotal;
            }

            public void setDoctorTotal(int doctorTotal) {
                this.doctorTotal = doctorTotal;
            }
        }

    }
}
