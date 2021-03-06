package com.nibatech.ecmd.fragment.guide;


import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.details.DoctorCaseDetailsActivity;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.base.DoctorGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端   guide-求高手
 */
public class DoctorDifficultFragment extends DoctorGuideFragment {
    public static final String STRING_JOINED = "已参与";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBroadCast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_GUIDE_DOCTOR_DIFFICULT,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_GUIDE_DOCTOR_DIFFICULT)) {
                            getHttpData();
                        }
                    }
                });
    }

    protected List<Map<String, Object>> setDataToList(List<OnlineTreatmentBean> onlineTreatments) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < onlineTreatments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            OnlineTreatmentBean onlineTreatment = onlineTreatments.get(i);
            PatientBean patient = onlineTreatment.getPatient();
            FormattedInfoBean formattedInfo = onlineTreatment.getFormattedInfo();

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

            //日期
            String mStrUpdatedTime = UIUtils.timeISO8601ConvertToNormal(
                    UIUtils.getNotNullString(onlineTreatment.getUpdatedTime()));
            //已发布天数
            String mStrPublishedTime = UIUtils.getNotNullString(onlineTreatment.getUpdatedTime());
            String mShowTime = String.format(STRING_PUBLISH_DAYS,
                    UIUtils.timeCountDownOnToday(UIUtils.timeISO8601RemoveT(mStrPublishedTime)));
            //描述
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //医生参与数量
            String mStrDoctorNumber = String.format(STRING_DOCTOR_JOINED,
                    String.valueOf(formattedInfo.getDoctorCount()));
            //费用区间
            String mStrPrice = String.format(STRING_PRICE_RANGE,
                    String.valueOf(formattedInfo.getMinExpense()),
                    String.valueOf(formattedInfo.getMaxExpense()));
            //url
            String mStrUrl = onlineTreatment.getSelfUrl();

            //是否参与
            boolean bJoin = formattedInfo.isParticipated();
            String mStrJoin = null;
            if (bJoin) {
                mStrJoin = STRING_JOINED;
            }

            //定向挂单 0-没有 1-有定向挂单
            int intOrderType = onlineTreatment.getOrderType();
            if (intOrderType == 1) {
                mStrUpdatedTime = String.format("%s  定向挂单", mStrUpdatedTime);
            }

            map.put(DataKey.KEY_UPDATE_TIME, mShowTime);
            map.put(DataKey.KEY_CREATE_TIME, mStrUpdatedTime);
            map.put(DataKey.KEY_AVATAR, mStrAvatar);
            map.put(DataKey.KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_AGE, mStrAge);
            map.put(DataKey.KEY_NAME, mStrGender + " " + mStrAge + "岁");
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorNumber);
            map.put(DataKey.KEY_MAX_PRICE, mStrPrice);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_JOIN, mStrJoin);
            map.put(DataKey.KEY_STATE, mStrJoin);

            list.add(map);
        }

        return list;
    }

    @Override
    protected void onListItemClick(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), viewHolder.getListItemView(R.id.list_item_view).getHeadView(), getString(R.string.transition));

        startActivityBindData(DoctorCaseDetailsActivity.class,
                (String) adapterList.get(position).get(DataKey.KEY_URL),
                GUIDE_DOCTOR_DIFFICULT, (String) adapterList.get(position).get(DataKey.KEY_JOIN),transitionActivityOptions);

    }


}



