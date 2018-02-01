package com.nibatech.ecmd.fragment.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.register.register.RegisterPhoneActivity;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.config.ExtraPass;


/**
 * 医生端/患者端   注册-选择身份
 */
public class SelectIdentityFragment extends Fragment implements View.OnClickListener {

    private RadioButton rbDoctor, rbPatient;
    private RadioGroup radioGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_identity, container, false);
        //获取所有的控件名
        getAllController(view);
        //设置监听事件
        setControllerListener();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_doctor:
                gotoRegister(BaseVolleyRequest.JSON_VALUE_DOCTOR);
                break;
            case R.id.rb_patient:
                gotoRegister(BaseVolleyRequest.JSON_VALUE_PATIENT);
                break;
        }
    }

    private void getAllController(View view) {
        //我是医生
        rbDoctor = (RadioButton) view.findViewById(R.id.rb_doctor);
        //我是患者
        rbPatient = (RadioButton) view.findViewById(R.id.rb_patient);
        //单选框
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_group);
    }

    private void setControllerListener() {
        rbDoctor.setOnClickListener(this);
        rbPatient.setOnClickListener(this);
    }

    private void gotoRegister(String strId) {
        Intent intent = new Intent();
        intent.putExtra(ExtraPass.ID, strId);
        intent.setClass(getActivity(), RegisterPhoneActivity.class);
        startActivity(intent);
    }

}