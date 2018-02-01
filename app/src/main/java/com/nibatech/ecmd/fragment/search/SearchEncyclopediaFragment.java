package com.nibatech.ecmd.fragment.search;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mien.MienArticleActivity;
import com.nibatech.ecmd.common.bean.mien.MienEncyclopediaListBean;
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
 * 患者端/游客端   搜索-encyclopedia
 */
public class SearchEncyclopediaFragment extends LoadMoreListViewFragment implements
        LoadMoreListViewFragment.ShowLoadMoreListViewDataListener {
    private String strNextUrl;


    @Override
    protected void onCreateSuccessView(View successView) {
        initView(successView);
    }

    @Override
    protected int getSuccessViewLayoutId() {
       return R.layout.fragment_search_encyclopeadic;
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
        this.strNextUrl = url;
    }

    @Override
    public String getNextUrl() {
        return strNextUrl;
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        startActivityBindData(MienArticleActivity.class, (String) adapterList.get(position).get(DataKey.KEY_URL));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        //图片
        UIUtils.loadNetworkImage(getActivity(), (String) adapterList.get(position).get(DataKey.KEY_IMG),
                viewHolder.getImageView(R.id.iv_photo));
        //标题：百科标题
        viewHolder.getTextView(R.id.tv_middle).setText((String) adapterList.get(position).get(DataKey.KEY_TITLE));
        //时间：刚刚
        viewHolder.getTextView(R.id.tv_right).setText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME));

    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        MienEncyclopediaListBean mienEncyclopediaListBean = UIUtils.fromJson(json, MienEncyclopediaListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (mienEncyclopediaListBean != null) {
            setNextUrl(mienEncyclopediaListBean.getPages().getNextUrl());
            list = setDataToList(mienEncyclopediaListBean.getArticles());
        }

        return list;
    }

    private List<Map<String, Object>> setDataToList(List<MienEncyclopediaListBean.Article> articles) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            MienEncyclopediaListBean.Article article = articles.get(i);
            //图片
            map.put(DataKey.KEY_IMG, article.getCoverUrl());
            //标题
            map.put(DataKey.KEY_TITLE, article.getTitle());
            //时间
            String strTime = UIUtils.timeCountDownBeforeNow(
                    UIUtils.timeISO8601RemoveT(article.getTime()));
            map.put(DataKey.KEY_UPDATE_TIME, strTime);
            //url
            map.put(DataKey.KEY_URL, article.getSelfUrl());
            list.add(map);
        }

        return list;
    }
}
