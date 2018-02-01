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
 * 游客端   guide-挂单区
 */
public class TouristOrderFragment extends TouristGuideFragment {
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
            GuideOrderListTouristBean.OnlineTreatment onlineTreatment =
                    onlineTreatments.get(i);
            Map<String, Object> map = new HashMap<>();

            //时间
            String mStrDate = UIUtils.timeISO8601ConvertToNormal(onlineTreatment.getCreatedTime());
            //倒计时:23小时
            String mStrUpdatedTime = UIUtils.timeISO8601RemoveT(onlineTreatment.getUpdatedTime());
            String mShowTime = String.format(STRING_COUNT_TIME, UIUtils.timeCountDownIn24(mStrUpdatedTime));
            //描述
            String mStrDescription = UIUtils.getNotNullString(onlineTreatment.getDescription());
            //医生参与数
            String mStrDoctorCount = String.format(STRING_DOCTOR_JOINED,
                    String.valueOf(onlineTreatment.getFormattedInfoBean().getDoctorCount()));
            //最低价格
            String mStrPrice = String.format(STRING_PRICE_RANGE,
                    String.valueOf(onlineTreatment.getFormattedInfoBean().getMinExpense()),
                    String.valueOf(onlineTreatment.getFormattedInfoBean().getMaxExpense()));
            //URL
            String mStrUrl = onlineTreatment.getSelfUrl();

            map.put(DataKey.KEY_AVATAR, null);
            map.put(DataKey.KEY_GENDER, null);
            map.put(DataKey.KEY_CREATE_TIME, mStrDate);
            map.put(DataKey.KEY_UPDATE_TIME, mShowTime);
            map.put(DataKey.KEY_CONTENT, mStrDescription);
            map.put(DataKey.KEY_DOCTOR_COUNT, mStrDoctorCount);
            map.put(DataKey.KEY_MAX_PRICE, mStrPrice);
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
                    null, GUIDE_TOURIST_ORDER);
        }
    }
}
