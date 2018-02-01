package com.nibatech.ecmd.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mine.ModifyProfileActivity;
import com.nibatech.ecmd.activity.mine.certify.CertifiedActivity;
import com.nibatech.ecmd.activity.mine.certify.UnCertifyActivity;
import com.nibatech.ecmd.activity.photo.PhotoModifyActivity;
import com.nibatech.ecmd.common.bean.login.LoginBean;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.view.LineControllerView;


/**
 * 医生端/患者端   我的-个人资料
 */
public class ProfileFragment extends BaseFragment implements View.OnClickListener {
    private LineControllerView lcvName, lcvGender, lcvAge, lcvHead, lcvPhone;
    private LineControllerView lcvCertify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        setViewData();
        return view;
    }

    private void initView(View view) {
        lcvName = (LineControllerView) view.findViewById(R.id.lcv_name);
        lcvGender = (LineControllerView) view.findViewById(R.id.lcv_gender);
        lcvAge = (LineControllerView) view.findViewById(R.id.lcv_age);
        lcvHead = (LineControllerView) view.findViewById(R.id.lcv_head);
        lcvPhone = (LineControllerView) view.findViewById(R.id.lcv_phone);
        lcvCertify = (LineControllerView) view.findViewById(R.id.lcv_certify);
    }

    private void setViewData() {
        lcvName.setOnClickListener(this);
        lcvGender.setOnClickListener(this);
        lcvAge.setOnClickListener(this);
        lcvHead.setOnClickListener(this);
        lcvCertify.setOnClickListener(this);
    }

    private void setUserInformation() {
        //得到个人信息
        LoginBean loginBean = BaseVolleyRequest.getLogin();
        UserBean userBean = loginBean.getUser();
        //姓名
        String mStrName = userBean.getFullName();
        lcvName.setContent(mStrName != null ? mStrName : "");
        //性别
        String mStrGender = userBean.getGender();
        lcvGender.setContent(mStrGender != null ? mStrGender : "");
        //年龄
        int mAge = userBean.getAge();
        lcvAge.setContent(String.valueOf(mAge));
        //电话
        String mStrPhone = userBean.getCellPhone();
        lcvPhone.setContent(mStrPhone);
        //医生才有认证信息
        if (userBean.getDoctor() != null) {
            lcvCertify.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.lcv_name:
                intent.putExtra(ExtraPass.EXTRA_INFORMATION, lcvName.getContent());
                intent.putExtra(ExtraPass.KEY, BaseVolleyRequest.JSON_KEY_FULL_NAME);
                intent.setClass(getActivity(), ModifyProfileActivity.class);
                break;
            case R.id.lcv_gender:
                intent.putExtra(ExtraPass.EXTRA_INFORMATION, lcvGender.getContent());
                intent.putExtra(ExtraPass.KEY, BaseVolleyRequest.JSON_KEY_GENDER);
                intent.setClass(getActivity(), ModifyProfileActivity.class);
                break;
            case R.id.lcv_age:
                intent.putExtra(ExtraPass.EXTRA_INFORMATION, lcvAge.getContent());
                intent.putExtra(ExtraPass.KEY, BaseVolleyRequest.JSON_KEY_AGE);
                intent.setClass(getActivity(), ModifyProfileActivity.class);
                break;
            case R.id.lcv_head:
                intent.setClass(getActivity(), PhotoModifyActivity.class);
                break;
            case R.id.lcv_certify:
                intent.putExtra(ExtraPass.SELF_URL, getIntentSelfUrl());
                if (BaseVolleyRequest.getLogin().getUser().getDoctor().isVerified()) {
                    intent.setClass(getActivity(), CertifiedActivity.class);
                } else {
                    intent.setClass(getActivity(), UnCertifyActivity.class);
                }
                break;
        }

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        setUserInformation();
    }
}
