package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrizeHistoryListBean {
    /**
     * self_url : http://139.217.8.207:5000/api/case_games/history/1
     * name : 第一期有奖病例分析
     * end_date : 2016-10-19
     * bonus : 5300
     * result : {"bonus_per_doctor":0,"doctor_right":0,"doctor_total":0}
     * start_date : 2016-10-17
     */

    @SerializedName("case_games")
    private List<CaseGame> caseGames;

    public List<CaseGame> getCaseGames() {
        return caseGames;
    }

    public void setCaseGames(List<CaseGame> caseGames) {
        this.caseGames = caseGames;
    }

    public static class CaseGame {
        @SerializedName("self_url")
        private String selfUrl;
        private String name;
        @SerializedName("end_date")
        private String endDate;
        private int bonus;
        /**
         * bonus_per_doctor : 0
         * doctor_right : 0
         * doctor_total : 0
         */

        private Result result;
        @SerializedName("start_date")
        private String startDate;

        public String getSelfUrl() {
            return selfUrl;
        }

        public void setSelfUrl(String selfUrl) {
            this.selfUrl = selfUrl;
        }

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
