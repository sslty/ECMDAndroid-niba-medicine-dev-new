package com.nibatech.ecmd.fragment.mien.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.floatactionbutton.mien.PublishArticleActivity;
import com.nibatech.ecmd.activity.mien.MomentArticleActivity;
import com.nibatech.ecmd.common.bean.mien.MienMomentListBean;
import com.nibatech.ecmd.common.request.MienRequest;
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
 * 医生端   中医风采-中医圈
 */
public class MomentListFragment extends RecycleListViewFragment
        implements RecycleListViewFragment.ShowRecycleListViewDataListener {
    public static final int ACTIVITY_PUBLISH_ARTICLE = 1;
    private FloatingActionButton floatButton;
    private String strNextUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moment_list, container, false);
        initView(view);
        setViewListener();
        return view;
    }

    private void initView(View view) {
        floatButton = (FloatingActionButton) view.findViewById(R.id.id_float_action_button);
        initRecycleListView((UniformCustomListView) view.findViewById(R.id.uniform_recycle_view), this);
    }

    @Override
    protected void getHttpData() {
        MienRequest.getChineseMedicineMomentList(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getRefreshListSuccess(success.toString());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ACTIVITY_PUBLISH_ARTICLE) {
                getHttpData();
            }
        }
    }

    private void setViewListener() {
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublishArticleActivity.class);
                startActivityForResult(intent, ACTIVITY_PUBLISH_ARTICLE);
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

    @Override
    public void onResume() {
        super.onResume();
        floatButton.setVisibility(View.VISIBLE);
    }
}
