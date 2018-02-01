package com.nibatech.ecmd.fragment.search.guide;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.details.DoctorCaseDetailsActivity;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.bean.guide.FormattedInfoBean;
import com.nibatech.ecmd.common.bean.guide.GuideOrderListBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.guide.DoctorDifficultFragment;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端   搜索-中医指导
 */
public class DoctorSearchGuideFragment extends SearchGuideFragment {


    @Override
    protected void onCreateSuccessView(View successView) {
        initView(successView);
    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.fragment_search_guide;
    }

    public List<Map<String, Object>> getDataFromJson(String json) {
        GuideOrderListBean guideOrderListBean = UIUtils.fromJson(json, GuideOrderListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (guideOrderListBean != null) {
            setNextUrl(guideOrderListBean.getPages().getNextUrl());
            list = setDataToList(guideOrderListBean.getOnlineTreatments());
        }
        return list;
    }

    private List<Map<String, Object>> setDataToList(List<OnlineTreatmentBean> onlineTreatments) {
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
            //倒计时
            String mStrCountTime = UIUtils.getNotNullString(onlineTreatment.getUpdatedTime());
            String mShowTime = String.format(BaseGuideFragment.STRING_COUNT_TIME,
                    UIUtils.timeCountDownIn24(UIUtils.timeISO8601RemoveT(mStrCountTime)));
            //描述
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //15位医生已经参与
            String mStrDoctorCount = String.format(BaseGuideFragment.STRING_DOCTOR_JOINED,
                    String.valueOf(formattedInfo.getDoctorCount()));
            //费用区间：5-10元
            String mStrPrice = String.format(BaseGuideFragment.STRING_PRICE_RANGE,
                    String.valueOf(formattedInfo.getMinExpense()),
                    String.valueOf(formattedInfo.getMaxExpense()));
            //url
            String mStrUrl = onlineTreatment.getSelfUrl();

            //是否参与
            boolean bJoin = formattedInfo.isParticipated();
            String mStrJoin = null;
            if (bJoin) {
                mStrJoin = DoctorDifficultFragment.STRING_JOINED;
            }
            //定向挂单 0-没有 1-有定向挂单
            int intOrderType = onlineTreatment.getOrderType();
            if (intOrderType == 1) {
                mStrUpdatedTime = String.format(BaseGuideFragment.STRING_DIRECTION_ORDER, mStrUpdatedTime);
            }

            map.put(DataKey.KEY_AVATAR, mStrAvatar);
            map.put(DataKey.KEY_GENDER, mStrGender);
            map.put(DataKey.KEY_AGE, mStrAge);
            map.put(DataKey.KEY_NAME, mStrGender + " " + mStrAge + "岁");
            map.put(DataKey.KEY_CREATE_TIME, mStrUpdatedTime);
            map.put(DataKey.KEY_UPDATE_TIME, mShowTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorCount);
            map.put(DataKey.KEY_MAX_PRICE, mStrPrice);
            map.put(DataKey.KEY_URL, mStrUrl);
            map.put(DataKey.KEY_JOIN, mStrJoin);
            map.put(DataKey.KEY_STATE, mStrJoin);

            list.add(map);
        }

        return list;
    }

    protected void onListItemClick(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(DoctorCaseDetailsActivity.class,
                (String) adapterList.get(position).get(DataKey.KEY_URL),
                BaseGuideFragment.GUIDE_DOCTOR_ORDER, (String) adapterList.get(position).get(DataKey.KEY_JOIN));

    }
}
