package com.nibatech.ecmd.fragment.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.news.NewsItemBean;
import com.nibatech.ecmd.fragment.mien.CommentFragment;
import com.nibatech.ecmd.utils.UIUtils;


/**
 * 医生端／患者端／游客端   news-详情
 */
public class NewsArticleFragment extends CommentFragment implements CommentFragment.ShowCommentDataListener {
    private NewsItemBean newsItemBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        initView(view);
        setCommentListener(this);
        return view;
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        newsItemBean = new Gson().fromJson(json, NewsItemBean.class);
        if (isSetDataToView()) {
            //设置评论列表url，发表评论url
            setUrl(newsItemBean.getCommentList(), newsItemBean.getComment());
            //请求获取以前的评论列表
            requestCommentList();
        }
    }

    @Override
    public void setViewData() {
        //标题-作者-时间-内容
        setAuthorData(null, newsItemBean.getTitle(), newsItemBean.getTime(), "", null);
        //web-view
        UIUtils.loadWebViewSetting(webView, newsItemBean.getDetailUrl());
        webView.setVisibility(View.VISIBLE);
        //评论列表
        setCommentListData(newsItemBean.getCommentCount());
    }
}
