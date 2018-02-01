package com.nibatech.ecmd.fragment.search.guide;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.LoadMoreListViewFragment;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 医生端/患者端/游客端   搜索-中医指导-父类
 */
public class SearchGuideFragment extends LoadMoreListViewFragment implements
        LoadMoreListViewFragment.ShowLoadMoreListViewDataListener {
    private String strNextUrl;

    protected void initView(View view) {
        initLoadMoreListView((UniformCustomListView) view.findViewById(R.id.uniform_recycle_view), this);
    }

    public void getHttpData(String key) {
        CommonRequest.getUrlData(getIntentSelfUrl() + key, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    @Override
    public void setNextUrl(String url) {
        this.strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        onListItemClick(viewHolder, adapterList, position);
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setHeadViewImageAndGender((String) adapterList.get(position).get(DataKey.KEY_AVATAR),
                        (String) adapterList.get(position).get(DataKey.KEY_GENDER))
                .setHeadPartTopText((String) adapterList.get(position).get(DataKey.KEY_STATE))
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_CREATE_TIME))
                .setContentPartTopRightText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_CONTENT))
                .setContentPartBottomLeftText((String) adapterList.get(position).get(DataKey.KEY_DOCTOR_COUNT))
                .setContentPartBottomRightText((String) adapterList.get(position).get(DataKey.KEY_MAX_PRICE))
                .setHeadPartBottomText((String) adapterList.get(position).get(DataKey.KEY_NAME));

    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        return null;
    }

    protected void onListItemClick(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        //父类，子类实现
    }
}

