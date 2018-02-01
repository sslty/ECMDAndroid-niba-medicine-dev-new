package com.nibatech.ecmd.fragment.mien.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mien.MienArticleActivity;
import com.nibatech.ecmd.common.bean.mien.MienEncyclopediaListBean;
import com.nibatech.ecmd.common.request.MienRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.RecycleListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.listview.PictureCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端   mien-encyclopedia
 */
public class EncyclopediaListFragment extends RecycleListViewFragment
        implements RecycleListViewFragment.ShowRecycleListViewDataListener {
    private String strNextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encyclopedia_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecycleListView((PictureCustomListView) view.findViewById(R.id.picture_recycle_view), this);
    }

    @Override
    protected void getHttpData() {
        MienRequest.getEncyclopediaList(new VolleyCallback() {
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
    public void clickViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
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
