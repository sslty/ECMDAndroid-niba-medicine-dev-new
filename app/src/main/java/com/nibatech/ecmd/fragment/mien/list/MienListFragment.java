package com.nibatech.ecmd.fragment.mien.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mien.MienArticleActivity;
import com.nibatech.ecmd.common.bean.mien.MienListBean;
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
 * 医生端/患者端   mien-风采
 */
public class MienListFragment extends RecycleListViewFragment
        implements RecycleListViewFragment.ShowRecycleListViewDataListener {
    private String strNextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mien_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecycleListView((PictureCustomListView) view.findViewById(R.id.picture_recycle_view), this);
    }

    @Override
    protected void getHttpData() {
        MienRequest.getMienList(new VolleyCallback() {
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
        //类型：名医视频
        viewHolder.getTextView(R.id.tv_left).setText((String) adapterList.get(position).get(DataKey.KEY_TYPE));
        viewHolder.getTextView(R.id.tv_left).setVisibility(View.VISIBLE);
        //标题：中医基础理论
        viewHolder.getTextView(R.id.tv_middle).setText((String) adapterList.get(position).get(DataKey.KEY_TITLE));
        //时间：刚刚
        viewHolder.getTextView(R.id.tv_right).setText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME));
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        MienListBean mienListBean = UIUtils.fromJson(json, MienListBean.class);
        List<Map<String, Object>> list = new ArrayList<>();
        if (mienListBean != null) {
            setNextUrl(mienListBean.getPages().getNextUrl());
            list = setDataToList(mienListBean.getArticles());
        }

        return list;
    }

    private List<Map<String, Object>> setDataToList(List<MienListBean.Article> articles) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            //图片
            map.put(DataKey.KEY_IMG, articles.get(i).getCoverUrl());
            //名医视频
            map.put(DataKey.KEY_TYPE, articles.get(i).getType());
            //中医基础理论知识
            map.put(DataKey.KEY_TITLE, articles.get(i).getTitle());
            //5分钟前
            String strTime = UIUtils.timeCountDownBeforeNow(UIUtils.timeISO8601RemoveT(articles.get(i).getTime()));
            map.put(DataKey.KEY_UPDATE_TIME, strTime);
            //浏览：10 评论: 5
            int intBrowse = articles.get(i).getViewCount();
            int intComment = articles.get(i).getCommentCount();
            String strComment = String.format("浏览: %1$s  评论: %2$s",
                    String.valueOf(intBrowse), String.valueOf(intComment));
            map.put(DataKey.KEY_COMMENT, strComment);
            //url
            map.put(DataKey.KEY_URL, articles.get(i).getSelfUrl());

            list.add(map);
        }

        return list;
    }
}
