package com.nibatech.ecmd.common.bean.register;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterExamListBean {
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static class Question {
        /**
         * question : 解表剂属于治病八法中的
         * answers : [{"answer":"汗"},{"answer":"吐"},{"answer":"和"},{"answer":"温"},{"answer":"清"}]
         * question_id : 2
         */

        private String question;
        @SerializedName("question_id")
        private int questionId;
        private List<Answer> answers;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

        public static class Answer {
            /**
             * answer : 汗
             */

            private String answer;

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
