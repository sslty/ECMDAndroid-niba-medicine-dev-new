package com.nibatech.ecmd.fragment.register.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.register.register.RegisterPasswordActivity;
import com.nibatech.ecmd.activity.register.register.RegisterPhoneActivity;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.config.ExtraPass;


/**
 * 医生端／患者端   选择身份
 */
public class IdentityFragment extends Fragment implements View.OnClickListener {
    private ImageView ivDoctor, ivPatient;
    private String strIdentity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_identity, container, false);

        initView(view);
        setViewListener();

        return view;
    }

    private void initView(View view) {
        ivDoctor = (ImageView) view.findViewById(R.id.iv_identity_doctor);
        ivPatient = (ImageView) view.findViewById(R.id.iv_identity_patient);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_identity_doctor:
                strIdentity = BaseVolleyRequest.JSON_VALUE_DOCTOR;
                break;
            case R.id.iv_identity_patient:
                strIdentity = BaseVolleyRequest.JSON_VALUE_PATIENT;
                break;
        }

        Intent intent = new Intent();
        intent.setClass(getActivity(), RegisterPhoneActivity.class);
        intent.putExtra(ExtraPass.ID, strIdentity);
        startActivity(intent);
    }

    private void setViewListener() {
        ivDoctor.setOnClickListener(this);
        ivPatient.setOnClickListener(this);
    }
}
