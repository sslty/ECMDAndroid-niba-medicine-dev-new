package com.nibatech.ecmd.fragment.guide.base;


import com.nibatech.ecmd.common.bean.guide.GuideOrderListBean;
import com.nibatech.ecmd.common.bean.guide.GuidePatientTreatmentBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 患者端   guide-挂单区／进行中的父类
 */
public class PatientGuideFragment extends BaseGuideFragment {
    public static final int LIST_VIEW_MINE = 0;
    public static final int LIST_VIEW_OTHERS = 1;
    private List<Map<String, Object>> othersList;
    private List<Map<String, Object>> myList;
    private int successCount;

    //我的挂单-挂单曲/进行中
    protected void requestUrlDataOfMine() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getSuccessOfMine(success.toString());
            }
        });
    }

    //其他人的挂单-进行中
    protected void requestUrlDataOfOthers() {
        CommonRequest.getUrlDataForPage(getIntentDetailsUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getSuccessOfOthers(success.toString());
            }
        });
    }

    private void getSuccessOfMine(String success) {
        successCount++;
        GuidePatientTreatmentBean treatment = UIUtils.fromJson(success, GuidePatientTreatmentBean.class);
        if (treatment != null) {
            if (treatment.isSuccess()) {
                List<OnlineTreatmentBean> guideOnlineTreatmentBeans = new ArrayList<>();
                guideOnlineTreatmentBeans.add(treatment.getOnlineTreatment());
                myList = setListToData(guideOnlineTreatmentBeans, LIST_VIEW_MINE);
            }
            setTogetherAdapter();
        }
    }

    private void getSuccessOfOthers(String success) {
        successCount++;
        GuideOrderListBean guideOrderListBean = UIUtils.fromJson(success, GuideOrderListBean.class);
        if (guideOrderListBean != null) {
            List<OnlineTreatmentBean> guidePatientMyTreatmentBeen = guideOrderListBean.getOnlineTreatments();
            setNextUrl(guideOrderListBean.getPages().getNextUrl());
            othersList = setListToData(guidePatientMyTreatmentBeen, LIST_VIEW_OTHERS);
            setTogetherAdapter();
        }
    }

    private void setTogetherAdapter() {
        if (getSuccessCount() == 2) {
            //我的挂单，他人挂单全部组合成新的list，然后绑定适配器
            List<Map<String, Object>> list = new ArrayList<>();

            if (myList != null) {
                for (int i = 0; i < myList.size(); i++) {
                    list.add(myList.get(i));
                }
                myList.clear();
            }

            if (othersList != null) {
                for (int i = 0; i < othersList.size(); i++) {
                    list.add(othersList.get(i));
                }
                othersList.clear();
            }

            addDataToRefreshRecycleView(list);
        }
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    protected List<Map<String, Object>> setListToData(List<OnlineTreatmentBean> treatments, int type) {
        //父类，子类实现
        return null;
    }
}
