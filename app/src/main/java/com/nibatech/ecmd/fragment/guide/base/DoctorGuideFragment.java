package com.nibatech.ecmd.fragment.guide.base;


import com.nibatech.ecmd.common.bean.guide.GuideOrderListBean;
import com.nibatech.ecmd.common.bean.guide.OnlineTreatmentBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 医生端   guide-挂单区／求高手／进行中的父类
 */
public class DoctorGuideFragment extends BaseGuideFragment {

    private boolean ok;

    protected void getHttpData() {
//        CommonRequest.getUrlDataForPage(getIntentSelfUrl(), new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getRefreshListSuccess(success.toString());
//            }
//        });
        getRefreshListSuccess("");
    }

    public List<Map<String, Object>> getDataFromJson(String json) {
//        GuideOrderListBean guideOrderListBean = UIUtils.fromJson(json, GuideOrderListBean.class);
//        List<Map<String, Object>> list = new ArrayList<>();
//        if (guideOrderListBean != null) {
//            setNextUrl(guideOrderListBean.getPages().getNextUrl());
//            list = setDataToList(guideOrderListBean.getOnlineTreatments());
//        }

        List<Map<String, Object>> list = new ArrayList<>();
        int num;
        if(ok){
            num = 1;
            ok =false;
        }else {
            num = 3;
            ok =true;
        }
        list = setDataToList(num);
        return list;
    }


    protected List<Map<String, Object>> setDataToList(List<OnlineTreatmentBean> onlineTreatments) {
        //父类，子类实现
        return null;
    }

    protected List<Map<String,Object>> setDataToList(int num){
        return null;
    }
}
