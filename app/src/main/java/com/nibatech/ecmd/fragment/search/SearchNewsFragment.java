package com.nibatech.ecmd.fragment.search;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.news.NewsArticleActivity;
import com.nibatech.ecmd.common.bean.news.NewsListBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.LoadMoreListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.listview.PictureCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生端／患者端／游客端   搜索-news
 */
public class SearchNewsFragment extends LoadMoreListViewFragment implements
        LoadMoreListViewFragment.ShowLoadMoreListViewDataListener {
    private String strNextUrl;


    @Override
    protected void onCreateSuccessView(View successView) {
        initView(successView);
    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.fragment_search_news;
    }

    private void initView(View view) {
        initLoadMoreListView((PictureCustomListView) view.findViewById(R.id.picture_recycle_view), this);
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
        strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(NewsArticleActivity.class, (String) adapterList.get(position).get(DataKey.KEY_URL));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        UIUtils.loadNetworkImage(getActivity(), (String) adapterList.get(position).get(DataKey.KEY_IMG),
                viewHolder.getImageView(R.id.iv_photo));
        viewHolder.getTextView(R.id.tv_middle).setText((String) adapterList.get(position).get(DataKey.KEY_TITLE));
        viewHolder.getTextView(R.id.tv_right).setText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME));
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        NewsListBean newsListBean = new Gson().fromJson(json, NewsListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (newsListBean != null) {
            setNextUrl(newsListBean.getPages().getNextUrl());
            list = setDataToList(newsListBean.getDynamics());
        }

        return list;
    }

    private List<Map<String, Object>> setDataToList(List<NewsListBean.Dynamic> dynamics) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < dynamics.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            //图片
            map.put(DataKey.KEY_IMG, dynamics.get(i).getCoverUrl());
            //标题
            map.put(DataKey.KEY_TITLE, dynamics.get(i).getTitle());
            //时间
            String strUpdatedTime = UIUtils.timeCountDownBeforeNow(
                    UIUtils.timeISO8601RemoveT(dynamics.get(i).getTime()));
            map.put(DataKey.KEY_UPDATE_TIME, strUpdatedTime);
            //url
            map.put(DataKey.KEY_URL, dynamics.get(i).getSelfUrl());
            list.add(map);
        }

        return list;
    }
}
