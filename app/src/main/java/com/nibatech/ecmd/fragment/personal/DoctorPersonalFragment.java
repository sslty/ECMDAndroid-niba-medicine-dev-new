package com.nibatech.ecmd.fragment.personal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.EffectsBean;
import com.nibatech.ecmd.common.bean.personal.DoctorPersonalBean;
import com.nibatech.ecmd.common.follow.FollowingButton;
import com.nibatech.ecmd.common.follow.FollowingCallBack;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.utils.Constants;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.CustomPieChartView;
import com.nibatech.ecmd.view.HeadItemView;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 医生端/患者端   医生的个人主页
 */
public class DoctorPersonalFragment extends BaseFragment implements NormalViewFragment.ShowNormalViewDataListener {
    private CustomPieChartView pieChartView;
    private TextView mTxtFollowing;
    private HeadItemView headItemView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_introduce, container, false);
        initView(view);
        getHostUrlData();
        return view;
    }

    @Override
    public void initView(View view) {
        //图形
        pieChartView = (CustomPieChartView) view.findViewById(R.id.pie_chart);
        //头像
        headItemView = (HeadItemView) view.findViewById(R.id.headItemView);
        //关注
        mTxtFollowing = (TextView) view.findViewById(R.id.id_txt_following);
    }

    @Override
    public void setViewListener() {

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
        DoctorPersonalBean doctorPersonalBean = UIUtils.fromJson(json, DoctorPersonalBean.class);
        if (doctorPersonalBean != null && doctorPersonalBean.isSuccess()) {
            setViewData(doctorPersonalBean);
        }
    }

    @Override
    public void setViewData(Object object) {
        DoctorPersonalBean doctorPersonalBean = (DoctorPersonalBean) object;
        DoctorProfileBean doctorProfileBean = doctorPersonalBean.getDoctorProfile();
        String mStrName = UIUtils.getNotNullString(doctorProfileBean.getFullName());
        String mStrGender = doctorProfileBean.getGender();
        String mStrLocation = doctorProfileBean.getDoctorType();
        String mStrAvatar = doctorProfileBean.getAvatarUrl();

        headItemView.setHeadViewImageAndGender(mStrAvatar, mStrGender, mStrName)
                .setHospital(mStrLocation)
                .showVIP(true);
        //关注
        new FollowingButton(getActivity(), mTxtFollowing,
                doctorPersonalBean.getRelations(),
                getIntentStringEntrance(),
                new FollowingCallBack() {
                    @Override
                    public void onClickButton() {
                    }
                });

        //疗效饼状图
        if (doctorPersonalBean.getEffects() != null) {
            setDataToMap(doctorPersonalBean.getEffects());
        }
    }


    private void setDataToMap(EffectsBean effectBeen) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(Constants.RECOVER, effectBeen.getRecover());
        map.put(Constants.REMARKABLE, effectBeen.getRemarkable());
        map.put(Constants.VALID, effectBeen.getValid());
        map.put(Constants.INVALID, effectBeen.getInvalid());
        map.put(Constants.PROCESSING, effectBeen.getProcessing());
        //分析图表
        setChartData(map);
    }

    private void setChartData(Map<String, Object> map) {
        pieChartView.setData(map);
    }
}
