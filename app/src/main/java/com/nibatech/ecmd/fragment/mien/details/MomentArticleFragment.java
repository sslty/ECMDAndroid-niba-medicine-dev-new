package com.nibatech.ecmd.fragment.mien.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.mien.MomentArticleItemBean;
import com.nibatech.ecmd.fragment.mien.CommentFragment;

import java.util.ArrayList;

/**
 * 医生端   mien-moment-文章
 */
public class MomentArticleFragment extends CommentFragment implements CommentFragment.ShowCommentDataListener {
    private MomentArticleItemBean momentArticleItemBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        initView(view);
        setCommentListener(this);
        return view;
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        momentArticleItemBean = new Gson().fromJson(json, MomentArticleItemBean.class);

        if (isSetDataToView()) {
            //设置评论列表url，发表评论url
            setUrl(momentArticleItemBean.getCommentList(), momentArticleItemBean.getComment());
            //请求获取以前的评论列表
            requestCommentList();
        }
    }

    @Override
    public void setViewData() {
        //名医视频，中医基础理论知识，2016.08.08，作者
        setAuthorData(null, momentArticleItemBean.getTitle(), momentArticleItemBean.getTime(),
                momentArticleItemBean.getDoctor().getName(), momentArticleItemBean.getContent());
        //头像
        headView.setHeadPhotoAndGender(momentArticleItemBean.getDoctor().getAvatarUrl(),
                momentArticleItemBean.getDoctor().getGender());
        headView.setVisibility(View.VISIBLE);

        //图片列表
        ArrayList<String> pathList = new ArrayList<>();
        for (int i = 0; i < momentArticleItemBean.getImages().size(); i++) {
            pathList.add(momentArticleItemBean.getImages().get(i).getImageUrl());
        }
        autoGridImageView.addImages(pathList);

        //评论列表
        setCommentListData(momentArticleItemBean.getCommentCount());
    }
}
