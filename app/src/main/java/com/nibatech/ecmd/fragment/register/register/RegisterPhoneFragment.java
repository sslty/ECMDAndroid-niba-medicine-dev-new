package com.nibatech.ecmd.fragment.register.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.activity.register.register.ExamActivity;
import com.nibatech.ecmd.activity.register.register.RegisterPasswordActivity;
import com.nibatech.ecmd.common.bean.register.RegisterAllowExamBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.RegisterRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.register.SendCodeFragment;

import org.json.JSONObject;


/**
 * 医生端/患者端   注册-发送验证码
 */
public class RegisterPhoneFragment extends SendCodeFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    //重载父类的方法
    public void onClickNext() {
        String id = getActivity().getIntent().getStringExtra(ExtraPass.ID);
        if (id.compareTo(BaseVolleyRequest.JSON_VALUE_DOCTOR) == 0) {
            requestAllowExam();//医生进入回答问题界面
        } else {
            gotoNextActivity(RegisterPasswordActivity.class);//患者直接进入设置密码界面
        }
    }

    //医生注册回答问题，一天不能超过3次
    private void requestAllowExam() {
        RegisterRequest.getAllowExam(editPhone.getText().toString(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getAllowExamSuccess(success.toString());
            }
        });
    }

    private void getAllowExamSuccess(String success) {
        RegisterAllowExamBean registerAllowExamBean = new Gson().fromJson(success, RegisterAllowExamBean.class);

        if (registerAllowExamBean.isAllowAnswer()) {//可以注册
            gotoNextActivity(ExamActivity.class);
        } else {//回答问题已经超过3次，已经超出限制，回到登录界面
            onDialogExamFinish();
        }
    }

    private void onDialogExamFinish() {
        AlertDialogBuilder.onCreate(getActivity(),
                "提示",
                "抱歉，本日三次答题机会已用完，请明日再试，点确认进入登录界面，您可以随便逛逛",
                true,
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                });
    }

    //进入下一个界面
    private void gotoNextActivity(Class cls) {
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.PHONE, editPhone.getText().toString());
        intent.putExtra(ExtraPass.CODE, editCode.getText().toString());
        intent.putExtra(ExtraPass.ID, getActivity().getIntent().getStringExtra(ExtraPass.ID));
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }
}