package com.nibatech.ecmd.fragment.guide;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.details.DoctorCaseDetailsActivity;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.DoctorGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端   guide-进行中
 */
public class DoctorOngoingFragment extends DoctorGuideFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initView(view);
        return view;
    }

    protected List<Map<String, Object>> setDataToList(List<OnlineTreatmentBean> onlineTreatments) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < onlineTreatments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            OnlineTreatmentBean onlineTreatment = onlineTreatments.get(i);
            PatientBean patient = onlineTreatment.getPatient();
            FormattedInfoBean formattedInfo = onlineTreatment.getFormattedInfo();
            DoctorProfileBean doctorProfile = onlineTreatment.getDoctorProfile();

            String mStrAvatar = null;
            String mStrGender = null;
            String mStrAge = null;
            if (patient != null) {
                //头像
                mStrAvatar = patient.getAvatarUrl();
                //性别
                mStrGender = patient.getGender();
                //年龄
                mStrAge = String.valueOf(patient.getAge());
            }

            //状态
            String mStrState = PatientOngoingFragment.getStageCount(formattedInfo.getStageCount());
            //日期
            String mStrDate = UIUtils.getNotNullString(onlineTreatment.getUpdatedTime());
            String mStrUpdatedTime = UIUtils.timeISO8601ConvertToNormal(mStrDate);
            //描述
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //医生姓名
            String mStrDoctorName = String.format(STRING_DOCTOR_NAME, UIUtils.getNotNullString(doctorProfile.getFullName()));
            //康复指导第几天
            String mStrCountTime = UIUtils.getNotNullString(onlineTreatment.getUpdatedTime());
            String mStrGuideDate = String.format(STRING_TREATMENT_DAYS,
                    UIUtils.timeCountDownOnToday(UIUtils.timeISO8601RemoveT(mStrCountTime)));
            //url
            String mStrUrl = onlineTreatment.getSelfUrl();
            //定向挂单 0-没有 1-有定向挂单
            int intOrderType = onlineTreatment.getOrderType();
            if (intOrderType == 1) {
                mStrUpdatedTime = String.format(STRING_DIRECTION_ORDER, mStrUpdatedTime);
            }

            map.put(DataKey.KEY_STATE, mStrState);
            map.put(DataKey.KEY_AVATAR, mStrAvatar);
            map.put(DataKey.KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_AGE, mStrAge);
            map.put(DataKey.KEY_NAME, mStrGender + " " + mStrAge + "岁");
            map.put(DataKey.KEY_MAX_PRICE, mStrGuideDate);
            map.put(DataKey.KEY_CREATE_TIME, mStrUpdatedTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorName);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_JOIN, "此处不需要参加");

            list.add(map);
        }

        return list;
    }

    @Override
    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), viewHolder.getListItemView(R.id.list_item_view).getHeadView(), getString(R.string.transition));
        startActivityBindData(DoctorCaseDetailsActivity.class,
                (String) adapterList.get(position).get(DataKey.KEY_URL),
                GUIDE_DOCTOR_ONGOING, (String) adapterList.get(position).get(DataKey.KEY_JOIN), transitionActivityOptions);
    }
}



