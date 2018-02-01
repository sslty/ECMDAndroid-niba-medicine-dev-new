package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrizeCurrentRoundBean {

    /**
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477531416.392905.jpg","name":"法师打发"},{"image_url":"http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477531423.548167.png","name":"发生的"}]
     * gender : 女
     * age : 30
     * description : 发打发斯蒂芬
     * detail : 发士大夫撒打算
     */

    @SerializedName("case_game")
    private CaseGame caseGame;
    /**
     * diagnose : 法师打发
     * treat_method : 发生的发生法
     * end_date : 2016-10-26
     * certification : 发生的发生
     * processing : false
     * answer_url : null
     * start_date : 2016-10-26
     * participants : [{"answer":"加重","is_winner":false},{"answer":"有效","is_winner":true},{"answer":"痊愈","is_winner":false},{"answer":"显效","is_winner":false},{"answer":"有效","is_winner":true}]
     * images : [{"image_url":"http://139.217.8.207:5000/static/app_files/1.1477531485.328216.png","name":"发的发生"}]
     * correct_answer : 有效
     * guide : 法师法师打发是发顺丰
     */

    private Round round;

    public CaseGame getCaseGame() {
        return caseGame;
    }

    public void setCaseGame(CaseGame caseGame) {
        this.caseGame = caseGame;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public static class CaseGame {
        private String gender;
        private String age;
        private String description;
        private String detail;
        /**
         * image_url : http://139.217.8.207:5000/static/app_files/case_game/upload/1.1477531416.392905.jpg
         * name : 法师打发
         */

        private List<PrizeImageBean> images;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public List<PrizeImageBean> getImages() {
            return images;
        }

        public void setImages(List<PrizeImageBean> images) {
            this.images = images;
        }
    }

    public static class Round {
        private String diagnose;
        @SerializedName("treat_method")
        private String treatMethod;
        @SerializedName("end_date")
        private String endDate;
        private String certification;
        private boolean processing;
        @SerializedName("answer_url")
        private String answerUrl;
        @SerializedName("start_date")
        private String startDate;
        @SerializedName("correct_answer")
        private String correctAnswer;
        private String guide;
        @SerializedName("allow_answer")
        private String allowAnswer;
        /**
         * answer : 加重
         * is_winner : false
         */

        private List<PrizeParticipantBean> participants;
        /**
         * image_url : http://139.217.8.207:5000/static/app_files/1.1477531485.328216.png
         * name : 发的发生
         */

        private List<PrizeImageBean> images;

        public String getDiagnose() {
            return diagnose;
        }

        public void setDiagnose(String diagnose) {
            this.diagnose = diagnose;
        }

        public String getTreatMethod() {
            return treatMethod;
        }

        public void setTreatMethod(String treatMethod) {
            this.treatMethod = treatMethod;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public boolean isProcessing() {
            return processing;
        }

        public void setProcessing(boolean processing) {
            this.processing = processing;
        }

        public String getAnswerUrl() {
            return answerUrl;
        }

        public void setAnswerUrl(String answerUrl) {
            this.answerUrl = answerUrl;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public String getGuide() {
            return guide;
        }

        public void setGuide(String guide) {
            this.guide = guide;
        }

        public List<PrizeParticipantBean> getParticipants() {
            return participants;
        }

        public void setParticipants(List<PrizeParticipantBean> participants) {
            this.participants = participants;
        }

        public List<PrizeImageBean> getImages() {
            return images;
        }

        public void setImages(List<PrizeImageBean> images) {
            this.images = images;
        }

        public String getAllowAnswer() {
            return allowAnswer;
        }

        public void setAllowAnswer(String allowAnswer) {
            this.allowAnswer = allowAnswer;
        }
    }
}
