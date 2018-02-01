package com.nibatech.ecmd.fragment.sea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.sea.SeaDetailActivity;
import com.nibatech.ecmd.common.bean.sea.SeaListBean;
import com.nibatech.ecmd.common.request.SeaRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.RecycleListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端/游客端   首页-sea
 */
public class SeaListFragment extends RecycleListViewFragment implements RecycleListViewFragment.ShowRecycleListViewDataListener {
    private String strNextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sea, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecycleListView((UniformCustomListView) view.findViewById(R.id.uniform_recycle_view), this);
    }

    @Override
    protected void getHttpData() {
        SeaRequest.getSeaList(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    @Override
    public void setNextUrl(String url) {
        strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(SeaDetailActivity.class, (String) adapterList.get(position).get(DataKey.KEY_URL),
                (String) adapterList.get(position).get(DataKey.KEY_TITLE));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_TITLE))
                .setContentPartTopRightText((String) adapterList.get(position).get(DataKey.KEY_TIME))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_CONTENT))
                .setContentPartBottomLeftText((String) adapterList.get(position).get(DataKey.KEY_FROM))
                .setContentPartBottomRightText((String) adapterList.get(position).get(DataKey.KEY_COMMENT));
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        SeaListBean seaListBean = new Gson().fromJson(json, SeaListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (seaListBean != null) {
            setNextUrl(seaListBean.getPages().getNextUrl());
            list = setDataToList(seaListBean.getMedicineLegacy());
        }

        return list;
    }

    private List<Map<String, Object>> setDataToList(List<SeaListBean.MedicineLegacy> medicineLegacies) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < medicineLegacies.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            //静脉曲张方剂
            map.put(DataKey.KEY_TITLE, medicineLegacies.get(i).getTitle());
            //2016.03.10
            map.put(DataKey.KEY_TIME, UIUtils.timeISO8601ConvertToNormal(medicineLegacies.get(i).getTime()));
            //肝主筋，肝气郁结，耗血使筋
            map.put(DataKey.KEY_CONTENT, medicineLegacies.get(i).getShortDesc());
            //来源: <<长生不老>>
            String strFrom = String.format("来源: %s", medicineLegacies.get(i).getQuote());
            map.put(DataKey.KEY_FROM, strFrom);
            //33 条评论
            String strComment = String.format("%s 条评论", String.valueOf(0));
            map.put(DataKey.KEY_COMMENT, strComment);
            //url
            map.put(DataKey.KEY_URL, medicineLegacies.get(i).getSelfUrl());
            list.add(map);
        }

        return list;
    }
}
