package com.nibatech.ecmd.fragment.guide;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.details.TouristCaseDetailsActivity;
import com.nibatech.ecmd.common.bean.guide.GuideOrderListTouristBean;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.TouristGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游客端   guide-进行中
 */
public class TouristOngoingFragment extends TouristGuideFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initView(view);
        return view;
    }

    protected List<Map<String, Object>> setDataToList(List<GuideOrderListTouristBean.OnlineTreatment> onlineTreatments) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < onlineTreatments.size(); i++) {
            GuideOrderListTouristBean.OnlineTreatment onlineTreatment = onlineTreatments.get(i);
            Map<String, Object> map = new HashMap<>();

            //状态
            String mStrState = PatientOngoingFragment.getStageCount(
                    onlineTreatment.getFormattedInfoBean().getStageCount());
            //时间
            String mStrDate = UIUtils.timeISO8601ConvertToNormal(onlineTreatment.getCreatedTime());
            //倒计时
            String mStrUpdatedTime = UIUtils.timeISO8601RemoveT(onlineTreatment.getUpdatedTime());
            String mShowTime = String.format(STRING_COUNT_TIME,
                    UIUtils.timeCountDownIn24(mStrUpdatedTime));
            //描述
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //医生姓名
            String mStrDoctorName = String.format(STRING_DOCTOR_NAME,
                    UIUtils.getNotNullString(onlineTreatment.getDoctor().getFullName()));
            //中医指导天数
            String mStrStartTime = UIUtils.timeISO8601RemoveT(onlineTreatment.getStartTime());
            String mStrGuideDate = String.format(STRING_TREATMENT_DAYS,
                    UIUtils.timeCountDownOnToday(mStrStartTime));
            //url
            String mStrUrl = onlineTreatment.getSelfUrl();

            map.put(DataKey.KEY_AVATAR, null);
            map.put(DataKey.KEY_GENDER, null);
            map.put(DataKey.KEY_STATE, mStrState);
            map.put(DataKey.KEY_CREATE_TIME, mShowTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorName);
            map.put(DataKey.KEY_MAX_PRICE, mStrGuideDate);
            map.put(DataKey.KEY_URL, mStrUrl);

            list.add(map);
        }

        return list;
    }

    @Override
    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        String url = (String) adapterList.get(position).get(DataKey.KEY_URL);
        if (url != null) {
            startActivityBindData(TouristCaseDetailsActivity.class,
                    (String) adapterList.get(position).get(DataKey.KEY_URL),
                    null, GUIDE_TOURIST_ONGOING);
        }
    }
}
