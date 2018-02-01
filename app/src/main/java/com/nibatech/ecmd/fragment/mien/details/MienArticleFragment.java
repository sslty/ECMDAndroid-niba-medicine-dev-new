package com.nibatech.ecmd.fragment.mien.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.mien.MienArticleItemBean;
import com.nibatech.ecmd.fragment.mien.CommentFragment;
import com.nibatech.ecmd.utils.UIUtils;


/**
 * 医生端   mien-风采-文章
 */
public class MienArticleFragment extends CommentFragment implements CommentFragment.ShowCommentDataListener {
    private MienArticleItemBean mienArticleItemBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);

        initView(view);
        setCommentListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        mienArticleItemBean = new Gson().fromJson(json, MienArticleItemBean.class);

        if (isSetDataToView()) {
            //设置评论列表url，发表评论url
            setUrl(mienArticleItemBean.getCommentList(), mienArticleItemBean.getComment());
            //请求获取以前的评论列表
            requestCommentList();
        }
    }

    @Override
    public void setViewData() {
        //名医视频，中医基础理论知识，2016.08.08，作者
        setAuthorData(mienArticleItemBean.getType(), mienArticleItemBean.getTitle(),
                mienArticleItemBean.getTime(), mienArticleItemBean.getAuthor(), null);

        //web-view
        UIUtils.loadWebViewSetting(webView, mienArticleItemBean.getDetailUrl());
        webView.setVisibility(View.VISIBLE);

        //评论列表
        setCommentListData(mienArticleItemBean.getCommentCount());
    }
}
