package com.nibatech.ecmd.fragment.guide.chat.supply;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatQuestionBean;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.view.AutoGridImageView;
import com.nibatech.ecmd.view.AutoGridView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生端／患者端   guide-聊天-查看基本资料
 */
public class ChatSupplyViewFragment extends Fragment {

    private AutoGridView agvQuestion;
    private AutoGridImageView autoGridImageView;
    private String selfUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_supply_view, container, false);
        getIntentData();
        getAllController(view);
        getHttpUrl();
        initData();
        return view;

    }

    private void getIntentData() {
        selfUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_SELF_URL);
    }

    private void getAllController(View view) {
        agvQuestion = (AutoGridView) view.findViewById(R.id.agv_question);
        autoGridImageView = (AutoGridImageView) view.findViewById(R.id.auto_image_container);

    }


    private void getHttpUrl() {
        getUrl();
    }

    private void initData() {

    }

    private void getUrl() {
        CommonRequest.getUrlData(selfUrl, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    private void getUrlDataSuccess(String success) {
        //得到数据
        ChatQuestionBean chatQuestionBean = new Gson().fromJson(success, ChatQuestionBean.class);
        //设置控件
        setControllerData(chatQuestionBean.getQuestionsAnswers(), chatQuestionBean.getImages());
    }

    private void setControllerData(List<ChatQuestionBean.QuestionsAnswers> questionsAnswers,
                                   List<ImageNameBean> images) {
        //问题列表
        QuestionAndReplyAdapter questionAndReplyAdapter = new QuestionAndReplyAdapter(
                getQuestions(questionsAnswers), getAnswers(questionsAnswers));
        agvQuestion.setAdapter(questionAndReplyAdapter);

        if (images.size() > 0) {
            autoGridImageView.setVisibility(View.VISIBLE);
            autoGridImageView.addImages(getImageUrls(images));
        }

    }

    private ArrayList<String> getImageUrls(List<ImageNameBean> images) {
        ArrayList<String> url = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            url.add(i, images.get(i).getImageUrl());
        }

        return url;
    }

    private ArrayList<String> getAnswers(List<ChatQuestionBean.QuestionsAnswers> questionsAnswers) {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < questionsAnswers.size(); i++) {
            answers.add(i, questionsAnswers.get(i).getAnswer());
        }

        return answers;
    }

    private ArrayList<String> getQuestions(List<ChatQuestionBean.QuestionsAnswers> questionsAnswers) {
        ArrayList<String> questions = new ArrayList<>();
        for (int i = 0; i < questionsAnswers.size(); i++) {
            questions.add(i, questionsAnswers.get(i).getQuestion());
        }

        return questions;
    }


    class QuestionAndReplyAdapter extends BaseAdapter {
        ArrayList<String> questions;
        ArrayList<String> answers;

        public QuestionAndReplyAdapter(ArrayList<String> questions, ArrayList<String> answers) {
            this.questions = questions;
            this.answers = answers;
        }

        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View view = View.inflate(getActivity(), R.layout.list_chat_material_view, null);
            TextView tvQuestion = (TextView) view.findViewById(R.id.tv_title);
            TextView tvAnswer = (TextView) view.findViewById(R.id.tv_question);
            tvQuestion.setText("问题" + (position + 1) + ": " + questions.get(position));
            if (answers.get(position) != null) {
                tvAnswer.setText("回复" + (position + 1) + ": " + answers.get(position));
            } else {
                tvAnswer.setTextColor(Color.RED);
                tvAnswer.setText("回复" + (position + 1) + ": 未回答");
            }
            return view;
        }
    }
}
