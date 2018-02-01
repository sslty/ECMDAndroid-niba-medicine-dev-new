package com.nibatech.ecmd.activity.personal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.floatactionbutton.guide.PatientSubmitOrderActivity;
import com.nibatech.ecmd.activity.guide.PatientGuideActivity;
import com.nibatech.ecmd.common.bean.guide.GuidePatientTreatingStatusBean;
import com.nibatech.ecmd.common.bean.personal.DoctorPersonalBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.floatactionbutton.guide.PatientSubmitOrderFragment;
import com.nibatech.ecmd.fragment.personal.DoctorPersonalFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;


/**
 * 医生端/患者端/游客端   医生的个人主页
 */
public class DoctorPersonalActivity extends BaseActivity {
    private BroadCast broadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBarText("个人主页");
        addDefaultFragment(new DoctorPersonalFragment());
        getHostUrlData();
        registerBroadCast();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(this, BroadCast.REFRESH_DOCTOR_PERSONAL,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_DOCTOR_PERSONAL)) {
                            getHostUrlData();
                        }
                    }
                });
    }

    private void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    protected void getHostUrlDataSuccess(final String json) {
        DoctorPersonalBean doctorPersonalBean = UIUtils.fromJson(json, DoctorPersonalBean.class);
        if (doctorPersonalBean != null && doctorPersonalBean.isSuccess()) {
            setOrderStatus(doctorPersonalBean);
        }
    }

    private void setOrderStatus(final DoctorPersonalBean doctorPersonalBean) {
        if (doctorPersonalBean.getAssignedOlt() != null) {
            CommonRequest.getUrlData(doctorPersonalBean.getAssignedOlt().getTreatingStatus(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    final GuidePatientTreatingStatusBean guidePatientTreatingStatusBean = UIUtils.fromJson(success.toString(),
                            GuidePatientTreatingStatusBean.class);
                    setFloatingButtonVisible(true);
                    setFloatingButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showToastMessage(guidePatientTreatingStatusBean.getStatus());
                            if (guidePatientTreatingStatusBean.getStatus().equals(PatientGuideActivity.TREATING_STATE_NULL)) {
                                startActivityBindDataForResult(PatientSubmitOrderActivity.class,
                                        doctorPersonalBean.getAssignedOlt().getCreateAssignedOltUrl(),
                                        PatientSubmitOrderFragment.ACTIVITY_RESULT_SUBMIT_ORDER);
                            }
                        }
                    });
                }
            });
        }
    }

    private void showToastMessage(String status) {
        String message = null;
        switch (status) {
            case PatientGuideActivity.TREATING_STATE_NULL:
                break;
            case PatientGuideActivity.TREATING_STATE_ONGOING:
                message = "您现在已经在诊疗阶段，无法再次定向挂单";
                break;
            case PatientGuideActivity.TREATING_STATE_ORDER:
                message = "您现在已经挂单，请先结束才能定向挂单";
                break;
        }

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PatientSubmitOrderFragment.ACTIVITY_RESULT_SUBMIT_ORDER) {//定向挂单成功
                getHostUrlData();
            }
        }
    }

}
