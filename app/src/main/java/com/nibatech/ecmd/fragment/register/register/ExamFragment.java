package com.nibatech.ecmd.fragment.register.register;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.activity.register.register.RegisterPasswordActivity;
import com.nibatech.ecmd.common.bean.register.RegisterAllowExamBean;
import com.nibatech.ecmd.common.bean.register.RegisterCheckCorrectBean;
import com.nibatech.ecmd.common.bean.register.RegisterExamListBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.RegisterRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 医生端   注册-回答问题
 */
public class ExamFragment extends Fragment implements View.OnClickListener {
    private RegisterExamListBean registerExamListBean;
    private List<Map<String, Object>> list;
    private String strPhone;
    private LinearLayout llRadioGroup;
    private Button btnNext;
    private TextView tvTime;
    private boolean overTime;
    private Set<Map<String, Object>> answerSelect;
    private List<Map<String, Object>> answerList;
    private CountDownTimer countDownTimer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        getIntentData();
        getAllController(view);
        setViewListener();
        getHttpData();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        //计时器取消
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                onClickNext();
                break;
        }
    }

    private void getIntentData() {
        strPhone = getActivity().getIntent().getStringExtra(ExtraPass.PHONE);
    }

    private void getAllController(View view) {
        llRadioGroup = (LinearLayout) view.findViewById(R.id.ll_radio_group);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
    }

    private void setViewListener() {
        btnNext.setOnClickListener(this);
    }

    private void getHttpData() {
        RegisterRequest.getExamList(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getExamListSuccess(success.toString());
            }
        });
    }

    private void getExamListSuccess(String success) {
        registerExamListBean = new Gson().fromJson(success, RegisterExamListBean.class);

        //获取数据
        getListData();
        //把数据添加到控件
        setListToView(list);
        //设置倒计时控件
        setCountDownTimeView();
    }

    private void getListData() {
        list = new ArrayList<>();
        answerList = new ArrayList<>();
        answerSelect = new HashSet<>();

        for (int i = 0; i < registerExamListBean.getQuestions().size(); i++) {
            Map<String, Object> map = new HashMap<>();

            RegisterExamListBean.Question question = registerExamListBean.getQuestions().get(i);
            //问题
            map.put(DataKey.KEY_QUESTION, question.getQuestion());
            //id
            map.put(DataKey.KEY_ID, question.getQuestionId());
            //答案列表
            List<String> answers = new ArrayList<>();
            for (int j = 0; j < question.getAnswers().size(); j++) {
                answers.add(question.getAnswers().get(j).getAnswer());
            }
            map.put(DataKey.KEY_ANSWER, answers);

            list.add(map);
        }
    }

    private void setListToView(List<Map<String, Object>> data) {
        llRadioGroup.removeAllViews();

        for (int i = 0; i < data.size(); i++) {
            RadioGroup radioGroup = new RadioGroup(getActivity());
            radioGroup.setId((Integer) data.get(i).get(DataKey.KEY_ID));

            String question = (String) data.get(i).get(DataKey.KEY_QUESTION);
            TextView tvQuestion = new TextView(getActivity());
            tvQuestion.setText(question);
            tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

            ArrayList<String> answers = (ArrayList<String>) data.get(i).get(DataKey.KEY_ANSWER);
            for (int j = 0; j < answers.size(); j++) {
                RadioButton radioButton = new RadioButton(getActivity());
                radioButton.setPadding(UIUtils.dip2px(6), 0, 0, 0);
                radioButton.setText(answers.get(j));
                radioButton.setBackgroundColor(getResources().getColor(R.color.normal_button));
                radioButton.setId((Integer) data.get(i).get(DataKey.KEY_ID) * 100 + j);
                radioGroup.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }

            //记录选择的数据
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton radioButton = (RadioButton) getActivity().findViewById(checkedId);

                    Map<String, Object> map = new HashMap<>();
                    map.put("question_id", radioGroup.getId());
                    map.put("answer", radioButton.getText());
                    answerSelect.add(map);
                    answerList.clear();
                    answerList.addAll(answerSelect);
                }
            });

            //添加到view
            llRadioGroup.addView(tvQuestion, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llRadioGroup.addView(radioGroup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    private void setCountDownTimeView() {
        overTime = false;//是否超时

        if (countDownTimer != null) {
            countDownTimer.start();
        } else {
            countDownTimer = new CountDownTimer(30 * 1000, 1000 - 10) {
                @Override
                public void onTick(long l) {
                    //第一次调用会有1-10ms的误差，因此需要+15ms，防止第一个数不显示，第二个数显示2s
                    tvTime.setText("倒计时" + ((l + 15) / 1000) + "秒");
                }

                @Override
                public void onFinish() {
                    //回答超时
                    tvTime.setText("倒计时" + 0 + "秒");
                    overTime = true;

                    Toast.makeText(getActivity(), "抱歉，回答超时", Toast.LENGTH_SHORT).show();
                    submitAnswers();
                }
            }.start();
        }
    }

    private void onClickNext() {
        if (registerExamListBean != null) {
            boolean bl = true;

            if (overTime) {//回答超时
                Toast.makeText(getActivity(), "抱歉，回答超时", Toast.LENGTH_SHORT).show();
            } else {
                if (answerList.size() < registerExamListBean.getQuestions().size()) {//未全部回答
                    Toast.makeText(getActivity(), "还有题目未回答，请检查", Toast.LENGTH_SHORT).show();
                    bl = false;
                }

                if (bl) {//提交答案
                    submitAnswers();
                }
            }
        } else {
            UIUtils.connectToHostShowFail(getActivity());
        }
    }

    //提交答案
    private void submitAnswers() {
        countDownTimer.cancel();//提交答案后，取消计时器
        RegisterRequest.putSubmitAnswers(strPhone, new JSONArray(answerList), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                putSubmitAnswersSuccess(success.toString());
            }
        });
    }

    private void putSubmitAnswersSuccess(String success) {
        RegisterCheckCorrectBean registerCheckCorrectBean = new Gson().fromJson(success, RegisterCheckCorrectBean.class);
        if (registerCheckCorrectBean.isPassTest()) {//答案正确, 进入修改密码界面
            gotoNextActivity();
        } else {//答案错误，向服务器请求是否还有答题机会
            requestAllowExam();
        }
    }

    //跳转到注册界面
    private void gotoNextActivity() {
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.PHONE, getActivity().getIntent().getStringExtra(ExtraPass.PHONE));
        intent.putExtra(ExtraPass.ID, getActivity().getIntent().getStringExtra(ExtraPass.ID));
        intent.putExtra(ExtraPass.CODE, getActivity().getIntent().getStringExtra(ExtraPass.CODE));
        intent.setClass(getActivity(), RegisterPasswordActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    //医生一天之内只有3次回答问题的机会
    private void requestAllowExam() {
        RegisterRequest.getAllowExam(strPhone, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getAllowExamSuccess(success.toString());
            }
        });
    }

    private void getAllowExamSuccess(String success) {
        RegisterAllowExamBean registerAllowExamBean = new Gson().fromJson(success, RegisterAllowExamBean.class);
        if (registerAllowExamBean.isAllowAnswer()) {//还有答题机会，继续答题
            onDialogExamAgain();
        } else {//没有机会了，直接跳到登录界面
            onDialogExamFinish();
        }
    }

    private void onDialogExamAgain() {
        AlertDialogBuilder.onCreate(getActivity(),
                "提示",
                "抱歉，您未答对以上题目，是否重新答题",
                true, new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        tvTime.setText("");
                        getHttpData();
                    }
                });
    }

    private void onDialogExamFinish() {
        AlertDialogBuilder.onCreate(getActivity(),
                "提示",
                "抱歉，本日三次答题机会已用完，请明日再试，点确认进入登录界面，您可以随便逛逛",
                true, new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                });
    }


}
