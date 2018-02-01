package com.nibatech.ecmd.fragment.search;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mien.MomentArticleActivity;
import com.nibatech.ecmd.common.bean.mien.MienMomentListBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.LoadMoreListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医生端   搜索-moment
 */
public class SearchMomentFragment extends LoadMoreListViewFragment implements
        LoadMoreListViewFragment.ShowLoadMoreListViewDataListener {
    private String strNextUrl;


    @Override
    protected void onCreateSuccessView(View successView) {
        initView(successView);
    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.fragment_search_moment;
    }

    private void initView(View view) {
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
        strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(MomentArticleActivity.class, (String) adapterList.get(position).get(DataKey.KEY_URL));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_TITLE))
                .setContentPartTopRightText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_FROM))
                .setContentPartBottomRightText((String) adapterList.get(position).get(DataKey.KEY_COMMENT));
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        MienMomentListBean mienMomentListBean = UIUtils.fromJson(json, MienMomentListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (mienMomentListBean != null) {
            setNextUrl(mienMomentListBean.getPages().getNextUrl());
            list = setDataToList(mienMomentListBean.getArticles());
        }

        return list;
    }

    private List<Map<String, Object>> setDataToList(List<MienMomentListBean.Article> articles) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            MienMomentListBean.Article article = articles.get(i);
            //标题
            String strTitle = article.getTitle();
            //时间
            String strTime = UIUtils.timeCountDownBeforeNow(
                    UIUtils.timeISO8601RemoveT(article.getTime()));
            //作者
            String strName = String.format("作者:  %s", article.getDoctor().getName());
            //浏览：10 评论：5
            String strBrowse = String.valueOf(article.getViewCount());
            String strComment = String.valueOf(article.getCommentCount());
            String strTogether = String.format("浏览: %1$s  评论: %2$s", strBrowse, strComment);
            //url
            String strUrl = article.getSelfUrl();

            map.put(DataKey.KEY_TITLE, strTitle);
            map.put(DataKey.KEY_UPDATE_TIME, strTime);
            map.put(DataKey.KEY_FROM, strName);
            map.put(DataKey.KEY_COMMENT, strTogether);
            map.put(DataKey.KEY_URL, strUrl);
            list.add(map);
        }

        return list;
    }
}
