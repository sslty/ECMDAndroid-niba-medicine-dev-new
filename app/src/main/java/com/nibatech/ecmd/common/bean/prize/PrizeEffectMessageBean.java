package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PrizeEffectMessageBean {

    /**
     * correct_answer : 无效
     * participants : [{"answer":"无效","is_winner":true},{"answer":"无效","is_winner":true}]
     * message : 上轮竞猜结果错误，无法继续，请再接再厉！
     */

    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public static class Answer{
        @SerializedName("correct_answer")
        private String correctAnswer;
        private String message;

        private List<PrizeParticipantBean> participants;

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<PrizeParticipantBean> getParticipants() {
            return participants;
        }

        public void setParticipants(List<PrizeParticipantBean> participants) {
            this.participants = participants;
        }
    }
}
