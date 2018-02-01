package com.nibatech.ecmd.fragment.mien;


import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.mien.MoreCommentsActivity;
import com.nibatech.ecmd.common.bean.comment.CommentListBean;
import com.nibatech.ecmd.common.bean.comment.CommentResultBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.MienRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.ViewPageLoadingFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageView;
import com.nibatech.ecmd.view.CommentView;
import com.nibatech.ecmd.view.HeadView;

import org.json.JSONObject;

/**
 * 医生端／患者端／游客端   mien-moment／encyclopedia／风采／news-评论功能
 */
public class CommentFragment extends ViewPageLoadingFragment {
    public interface ShowCommentDataListener {
        void getHostUrlDataSuccess(String json);

        void setViewData();
    }

    private LinearLayout llTopContent;
    private static final int MAX_COMMENT_SHOW_MORE = 5;
    protected AutoGridImageView autoGridImageView;
    protected HeadView headView;
    private LinearLayout layoutComment, layoutReply;
    private Button btnReply;
    private ScrollView scrollView;
    private Handler handler;
    private TextView tvTitle, tvTime, tvContent, tvComment, tvType;
    private CommentListBean commentListBean;
    private EditText editMessage;
    private TextView tvShowMore;
    private String listUrl, postUrl;
    protected WebView webView;
    private ShowCommentDataListener listener;

    //初始化控件
    protected void initView(View view) {
        //类型：名医视频
        llTopContent = (LinearLayout) view.findViewById(R.id.ll_top_content);
        tvType = (TextView) view.findViewById(R.id.tv_type);
        //标题：中医基础理论知识
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        //时间：2016.08.08 作者：xxx
        tvTime = (TextView) view.findViewById(R.id.tv_time_name);
        //作者头像
        headView = (HeadView) view.findViewById(R.id.id_image_avatar);
        //正文
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        //正文中的图片
        autoGridImageView = (AutoGridImageView) view.findViewById(R.id.auto_image_container);
        //web-view
        webView = (WebView) view.findViewById(R.id.web_view);
        //评论：18
        tvComment = (TextView) view.findViewById(R.id.tv_comment);
        //评论列表
        layoutComment = (LinearLayout) view.findViewById(R.id.ll_comment);
        //回复框
        layoutReply = (LinearLayout) view.findViewById(R.id.ll_reply);
        //回复
        btnReply = (Button) view.findViewById(R.id.btn_reply);
        scrollView = (ScrollView) view.findViewById(R.id.sv_scroll);
        handler = new Handler();
        //回复内容
        editMessage = (EditText) view.findViewById(R.id.et_msg);
        //查看更多
        tvShowMore = (TextView) view.findViewById(R.id.tv_show_more);
    }

    //子类初始化的时候设置监听
    protected void setCommentListener(ShowCommentDataListener showCommentDataListener) {
        listener = showCommentDataListener;
        setCreateView(true);
    }

    @Override
    protected void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    protected void getHostUrlDataSuccess(String json) {
        //父类，子类实现
    }

    //设置评论列表url和发表评论的url
    protected void setUrl(String listUrl, String postUrl) {
        this.listUrl = listUrl;
        this.postUrl = postUrl;
    }

    //请求评论列表数据
    protected void requestCommentList() {
        CommonRequest.getCommonMoreList(listUrl, BaseVolleyRequest.PAGES_PER_PAGE_FOR_COMMENT, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getCommentListSuccess(success.toString());
            }
        });
    }

    //成功获取评论列表数据
    protected void getCommentListSuccess(String json) {
        commentListBean = new Gson().fromJson(json, CommentListBean.class);

        listener.setViewData();
        setViewListener();
    }

    //设置控件数据：类型，标题，时间，作者，内容
    protected void setAuthorData(String type, String title, String time, String author, String content) {
        //类型：名医视频
        if (type != null) {
            tvType.setText(type);
            tvType.setVisibility(View.VISIBLE);
        }

        //标题
        tvTitle.setText(title);
        //时间
        String strTime = UIUtils.timeISO8601RemoveT(time);
        //作者+时间
        tvTime.setText(strTime + "  作者: " + author);

        //内容
        if (tvContent != null) {
            tvContent.setText(content);
            tvContent.setVisibility(View.VISIBLE);
        }
    }

    //每一条的评论明细
    protected void setCommentListData(int count) {
        //评论
        tvComment.setText(String.format("评论: %s ", String.valueOf(count)));
        //评论列表数据
        for (int i = 0; i < commentListBean.getComments().size(); i++) {
            String strAvatarUrl = commentListBean.getComments().get(i).getUser().getAvatarUrl();
            String strGender = commentListBean.getComments().get(i).getUser().getGender();
            String strName = commentListBean.getComments().get(i).getUser().getName();
            String strContent = commentListBean.getComments().get(i).getComment();
            String strCountDownTime = commentListBean.getComments().get(i).getTime();

            CommentView commentView = new CommentView(getActivity());
            commentView.setName(strName)
                    .setTime(UIUtils.timeCountDownBeforeNow(UIUtils.timeISO8601RemoveT(strCountDownTime)))
                    .setHeadPhotoAndGender(strAvatarUrl, strGender)
                    .setComment(strContent);
            layoutComment.addView(commentView, layoutComment.getChildCount() - 1);
        }

        //显示评论列表
        layoutComment.setVisibility(View.VISIBLE);
        //显示回复框，游客不能参加评论
        if (BaseVolleyRequest.getIdentity() != BaseVolleyRequest.IDENTITY_TOURIST) {
            layoutReply.setVisibility(View.VISIBLE);
        }
        //显示showMore
        if (commentListBean.getComments().size() > MAX_COMMENT_SHOW_MORE) {
            tvShowMore.setVisibility(View.VISIBLE);
            tvShowMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityBindData(MoreCommentsActivity.class, listUrl);
                }
            });
        }
    }

    //恢复评论功能
    private void setViewListener() {
        btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//回复功能
                if (editMessage.getText().length() != 0) {
                    requestPostComment();
                }
            }
        });
    }

    //向服务器请求回复评论
    private void requestPostComment() {
        MienRequest.postComment(this.postUrl, editMessage.getText().toString(),
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        postCommentSuccess(success.toString());
                    }
                });
    }

    //发表评论成功
    private void postCommentSuccess(String json) {
        CommentResultBean commentResultBean = new Gson().fromJson(json, CommentResultBean.class);
        CommentView commentView = new CommentView(getActivity());
        commentView.setName(commentResultBean.getUser().getName())
                .setHeadPhotoAndGender(commentResultBean.getUser().getAvatarUrl(),
                        commentResultBean.getUser().getGender())
                .setTime(UIUtils.timeCountDownBeforeNow(UIUtils.timeISO8601RemoveT(commentResultBean.getTime())))
                .setComment(commentResultBean.getComment());

        layoutComment.addView(commentView, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, llTopContent.getMeasuredHeight());
            }
        }, 300);
        //清除发表区
        editMessage.getText().clear();
    }
}
