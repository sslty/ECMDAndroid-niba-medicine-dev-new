package com.nibatech.ecmd.common.bean.chat;

import com.google.gson.annotations.SerializedName;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;

import java.util.List;

public class ChatQuestionBean {
    /**
     * self_url : http://139.217.8.207:5000/api/online_treatments/22/stages/5/materials/4
     * name : 补充资料4
     * qas : [{"answer":null,"start_time":"2016-10-14T18:27:48","question":"[","id":82,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"L","id":83,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"j","id":84,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"a","id":85,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"v","id":86,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"a","id":87,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":".","id":88,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"l","id":89,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"a","id":90,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"n","id":91,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"g","id":92,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":".","id":93,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"S","id":94,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"t","id":95,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"r","id":96,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"i","id":97,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"n","id":98,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"g","id":99,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":";","id":100,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"@","id":101,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"3","id":102,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"1","id":103,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"2","id":104,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"0","id":105,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"e","id":106,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"7","id":107,"end_time":null},{"answer":null,"start_time":"2016-10-14T18:27:48","question":"f","id":108,"end_time":null}]
     * image_description : 图片
     * start_time : 2016-10-14T18:27:47
     * end_time : null
     * images : []
     */

    @SerializedName("self_url")
    private String selfUrl;
    private String name;
    @SerializedName("image_description")
    private String imageDescription;
    @SerializedName("start_time")
    private String startTime;
    @SerializedName("end_time")
    private String endTime;
    /**
     * answer : null
     * start_time : 2016-10-14T18:27:48
     * question : [
     * id : 82
     * end_time : null
     */

    @SerializedName("qas")
    private List<QuestionsAnswers> questionsAnswers;
    private List<ImageNameBean> images;

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

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<QuestionsAnswers> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(List<QuestionsAnswers> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    public List<ImageNameBean> getImages() {
        return images;
    }

    public void setImages(List<ImageNameBean> images) {
        this.images = images;
    }

    public static class QuestionsAnswers {
        private String answer;
        @SerializedName("start_time")
        private String startTime;
        private String question;
        private int id;
        @SerializedName("end_time")
        private String endTime;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
