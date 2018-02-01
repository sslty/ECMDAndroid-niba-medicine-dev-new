package com.nibatech.ecmd.common.bean.prize;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PrizeCurrentListBean {
    /**
     * bonus : 5000
     * rounds : [{"self_url":"http://139.217.8.207:5000/api/case_game_rounds/5","end_date":"2016-10-25","name":"第2轮","doctor_right":0,"processing":true,"start_date":"2016-10-25","doctor_total":0},{"self_url":"http://139.217.8.207:5000/api/case_game_rounds/4","end_date":"2016-10-24","name":"第1轮","doctor_right":0,"processing":false,"start_date":"2016-10-24","doctor_total":0}]
     * name : 发生的发生法
     * end_date : 2016-10-26
     * start_date : 2016-10-24
     */

    private int bonus;
    private String name;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("start_date")
    private String startDate;


    private List<PrizeRoundBean> rounds;

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<PrizeRoundBean> getRounds() {
        return rounds;
    }

    public void setRounds(List<PrizeRoundBean> rounds) {
        this.rounds = rounds;
    }
}
