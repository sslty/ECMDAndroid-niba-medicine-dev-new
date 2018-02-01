package com.nibatech.ecmd.fragment.guide.introduce;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.ChatPatientCustomActivity;
import com.nibatech.ecmd.activity.guide.pay.PaymentActivity;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.guide.GuideOrderSeeDoctorBean;
import com.nibatech.ecmd.common.bean.guide.GuideStartTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.preferences.PaymentSharePreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.HeadView;

import org.json.JSONObject;


/**
 * 患者端   guide-挂单区-医生介绍
 */
public class GuideIntroduceDoctorFragment extends BaseFragment
        implements NormalViewFragment.ShowNormalViewDataListener {
    private TextView mTxtNameAndGender;
    private RoundTextView rtvHospital;
    private TextView mTxtCase, mTxtExpect, mTxtPrice;
    private TextView mTxtFree;
    private Button mBtnBack, mBtnStart;
    private HeadView headView;
    private String acceptUrl;
    private String doctorName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_introduce_doctor, container, false);

        initView(view);
        setViewListener();
        getHostUrlData();

        return view;
    }

    @Override
    public void initView(View view) {
        //性别与姓名
        mTxtNameAndGender = (TextView) view.findViewById(R.id.id_txt_name_gender);
        //医院
        rtvHospital = (RoundTextView) view.findViewById(R.id.rtv_hospital);
        //病情分析
        mTxtCase = (TextView) view.findViewById(R.id.id_txt_case);
        //效果预期
        mTxtExpect = (TextView) view.findViewById(R.id.id_txt_expectation);
        //价格
        mTxtPrice = (TextView) view.findViewById(R.id.id_txt_price);
        //首次免费
        mTxtFree = (TextView) view.findViewById(R.id.id_txt_free);
        //返回
        mBtnBack = (Button) view.findViewById(R.id.id_btn_back);
        //开始诊疗
        mBtnStart = (Button) view.findViewById(R.id.id_btn_start);

        headView = (HeadView) view.findViewById(R.id.id_image_avatar);
    }

    @Override
    public void setViewListener() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStartTreatment();//开始诊疗
            }
        });
    }

    @Override
    public void getHostUrlData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        GuideOrderSeeDoctorBean guideOrderSeeDoctorBean = UIUtils.fromJson(json, GuideOrderSeeDoctorBean.class);
        if (guideOrderSeeDoctorBean != null) {
            acceptUrl = guideOrderSeeDoctorBean.getAcceptUrl();
            doctorName = guideOrderSeeDoctorBean.getDoctorProfile().getFullName();
            setViewData(guideOrderSeeDoctorBean);
        }
    }

    @Override
    public void setViewData(Object object) {
        GuideOrderSeeDoctorBean guideOrderSeeDoctorBean = (GuideOrderSeeDoctorBean) object;
        DoctorProfileBean doctorProfileBean = guideOrderSeeDoctorBean.getDoctorProfile();
        //姓名
        String mStrName = doctorProfileBean.getFullName();
        //性别
        String mStrGender = doctorProfileBean.getGender();
        mTxtNameAndGender.setText(UIUtils.getNotNullString(mStrName) + "  " + UIUtils.getNotNullString(mStrGender));
        //头像
        String mStrAvatar = doctorProfileBean.getAvatarUrl();
        headView.setHeadPhotoAndGender(mStrAvatar, mStrGender);

        //医院
        String mStrHospital = doctorProfileBean.getDoctorType();
        if (mStrHospital != null) {
            rtvHospital.setVisibility(View.VISIBLE);
            rtvHospital.setText(UIUtils.getNotNullString(mStrHospital));
        }
        //病情分析
        String mStrCase = guideOrderSeeDoctorBean.getViewPoint();
        mTxtCase.setText(UIUtils.getNotNullString(mStrCase));
        //效果预期
        String mStrExpectation = guideOrderSeeDoctorBean.getExpectation();
        mTxtExpect.setText(UIUtils.getNotNullString(mStrExpectation));
        //价格
        String mStrPrice = String.valueOf(guideOrderSeeDoctorBean.getExpense());
        String mStrFee = String.format(getString(R.string.consultation_fee), mStrPrice);
        mTxtPrice.setText(UIUtils.getNotNullString(mStrFee));
        //是否免费
        boolean bFree = guideOrderSeeDoctorBean.isFreeFirstTime();
        mTxtFree.setVisibility(bFree ? View.VISIBLE : View.INVISIBLE);
    }

    private void requestStartTreatment() {
        if (acceptUrl != null) {
            //请求开始诊疗
            String name = String.format("您正在与%s医生开始诊疗阶段，再次确认开始后不能随意取消，请了解", doctorName);
            AlertDialogBuilder.onCreate(getActivity(), "警告", name, true, new AlertDialogListener() {
                @Override
                public void selectPositive() {
                    CommonRequest.putUrlData(getActivity(), acceptUrl, new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject success) {
                            putUrlDataSuccess(success.toString());
                        }
                    });
                }
            });
        }
    }

    private void putUrlDataSuccess(String json) {
        //成功开始诊疗
        GuideStartTreatmentBean guideStartTreatmentBean = UIUtils.fromJson(json, GuideStartTreatmentBean.class);
        if (guideStartTreatmentBean != null) { //首次免费
            if (guideStartTreatmentBean.isFreeFirstTime()) {
                startActivityBindData(ChatPatientCustomActivity.class,
                        guideStartTreatmentBean.getChatUrl());
            } else {//开始支付
                //保存url
                PaymentSharePreferences.save(getActivity(), guideStartTreatmentBean);
                startActivityBindData(PaymentActivity.class, guideStartTreatmentBean.getWeixinPayUnifiedOrderUrl());
            }
        }

        //广播通知，通知医生挂单区和病人进行中list必须刷新，病例详情界面必须销毁
        BroadCast.send(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ONGOING);
        BroadCast.send(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ORDER);
        BroadCast.send(getActivity(), BroadCast.REFRESH_GUIDE_DOCTOR_ORDER);
        BroadCast.send(getActivity(), BroadCast.DESTROY_GUIDE_DETAILS);
        //本界面销毁
        getActivity().finish();
    }
}
