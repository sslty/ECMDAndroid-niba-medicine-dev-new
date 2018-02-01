package com.nibatech.ecmd.fragment.guide.base;


import com.nibatech.ecmd.common.bean.guide.GuideOrderListTouristBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 游客端   guide-挂单区／进行中 父类
 */
public class TouristGuideFragment extends BaseGuideFragment {
    protected void getHttpData() {
        CommonRequest.getUrlDataForPage(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    public List<Map<String, Object>> getDataFromJson(String json) {
        GuideOrderListTouristBean guideOrderListTouristBean = UIUtils.fromJson(json, GuideOrderListTouristBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (guideOrderListTouristBean != null) {
            setNextUrl(guideOrderListTouristBean.getPages().getNextUrl());
            list = setDataToList(guideOrderListTouristBean.getOnlineTreatments());
        }

        return list;
    }

    protected List<Map<String, Object>> setDataToList(List<GuideOrderListTouristBean.OnlineTreatment> onlineTreatments) {
        //父类，子类实现
        return null;
    }
}
