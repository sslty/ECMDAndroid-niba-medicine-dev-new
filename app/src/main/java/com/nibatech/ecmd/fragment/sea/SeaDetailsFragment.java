package com.nibatech.ecmd.fragment.sea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.sea.SeaDetailBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.CommentView;

import org.json.JSONObject;


public class SeaDetailsFragment extends BaseFragment {

    private LinearLayout layoutComment;
    private Button btnSend;
    private SeaDetailBean seaDetailBean;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sea_details, container, false);
        getAllController(view);
        getHttpData();
        return view;
    }

    private void getAllController(View view) {
        layoutComment = (LinearLayout) view.findViewById(R.id.ll_comment);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        webView = (WebView) view.findViewById(R.id.web_view);
    }

    private void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    private void getUrlDataSuccess(String success) {
        seaDetailBean = new Gson().fromJson(success, SeaDetailBean.class);

        showWebView();
        showComment();
    }

    private void showWebView() {
        UIUtils.loadWebViewSetting(webView, seaDetailBean.getDetailUrl());
    }

    private void showComment() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentView commentView = new CommentView(getActivity());
                commentView.setName("ggggg")
                        .setTime("fff")
                        .setComment("ffffffff");
                layoutComment.setVisibility(View.INVISIBLE);
                layoutComment.addView(commentView);
            }
        });

    }


}
