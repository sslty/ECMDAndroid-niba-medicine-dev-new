package com.nibatech.ecmd.common.bean.prize;


import java.util.List;


public class PrizeEffectArrayListBean {
    /**
     * name : 痊愈
     */

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public static class Answer {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
