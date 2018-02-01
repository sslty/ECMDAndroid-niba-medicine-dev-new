package com.nibatech.ecmd.fragment.mine.follow;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.RefreshListViewFragment;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.listview.InformationCustomListView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;


/**
 * 医生端   我的-关注我的-父类
 */
public class FollowerFragment extends RefreshListViewFragment implements
        RefreshListViewFragment.ShowRefreshListViewDataListener {
    protected BroadCast broadCast;

    protected void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    protected void initView(View view) {
        initRefreshListView((InformationCustomListView) view.findViewById(R.id.contact_refresh_view), this);
    }

    @Override
    protected void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        //父类，子类实现
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        //父类，子类实现
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        return getJsonToMap(json);
    }

    protected List<Map<String, Object>> getJsonToMap(String json) {
        //父类，子类实现
        return null;
    }
}
