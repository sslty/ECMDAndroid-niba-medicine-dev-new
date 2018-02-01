package com.nibatech.ecmd.fragment.mien;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.comment.CommentListBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.RecycleListViewFragment;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.UniformCustomListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端／患者端／游客端   mien-encyclopedia／风采／moment／news-查看更多评论
 */
public class MoreCommentsFragment extends RecycleListViewFragment
        implements RecycleListViewFragment.ShowRecycleListViewDataListener {
    private String strNextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_comments, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initRecycleListView((UniformCustomListView) view.findViewById(R.id.uniform_recycle_view), this);
    }

    @Override
    protected void getHttpData() {
        CommonRequest.getCommonMoreList(getIntentSelfUrl(), new VolleyCallback() {
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
        //评论不能点击
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getListItemView(R.id.list_item_view)
                .setHeadViewImageAndGender((String) adapterList.get(position).get(DataKey.KEY_AVATAR),
                        (String) adapterList.get(position).get(DataKey.KEY_GENDER))
                .setContentPartTopLeftText((String) adapterList.get(position).get(DataKey.KEY_NAME))
                .setContentPartTopRightText((String) adapterList.get(position).get(DataKey.KEY_UPDATE_TIME))
                .setContentPartMiddleLeftText((String) adapterList.get(position).get(DataKey.KEY_CONTENT));
    }

    @Override
    public List<Map<String, Object>> getDataFromJson(String json) {
        CommentListBean commentListBean = new Gson().fromJson(json, CommentListBean.class);
        //下一页的url
        setNextUrl(commentListBean.getPages().getNextUrl());
        //获取数据
        return setDataToList(commentListBean.getComments());
    }

    private List<Map<String, Object>> setDataToList(List<CommentListBean.Comment> comments) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            //头像
            String strAvatarUrl = comments.get(i).getUser().getAvatarUrl();
            //性别
            String strGender = comments.get(i).getUser().getGender();
            //姓名
            String strName = comments.get(i).getUser().getName();
            //评论内容
            String strContent = comments.get(i).getComment();
            //发表时间
            String strCountDownTime = comments.get(i).getTime();

            map.put(DataKey.KEY_AVATAR, strAvatarUrl);
            map.put(DataKey.KEY_GENDER, strGender);
            map.put(DataKey.KEY_NAME, strName);
            map.put(DataKey.KEY_CONTENT, strContent);
            map.put(DataKey.KEY_UPDATE_TIME, strCountDownTime);
            list.add(map);
        }

        return list;
    }
}
