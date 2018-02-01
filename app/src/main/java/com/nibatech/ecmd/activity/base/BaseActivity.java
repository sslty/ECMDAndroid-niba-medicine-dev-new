package com.nibatech.ecmd.activity.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.view.BaseView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends SlidingTabActivity {
    //网络错误
    private void connectToHostFailure() {
        Toast.makeText(this, "网络连接失败，请检查网络设置!", Toast.LENGTH_SHORT).show();
    }

    //跳转下一个activity，并传递url
    protected void startActivityBindData(Class cls, String url) {
        if (url != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, url);
            intent.setClass(this, cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindDataForResult
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * int requestCode
     * 功能：进入下一个界面，并绑定数据，而且需要回传数据
     */
    protected void startActivityBindDataForResult(Class cls, String strSelfUrl, int requestCode) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.setClass(this, cls);
            startActivityForResult(intent, requestCode);
        } else {
            connectToHostFailure();
        }
    }

    //添加fragment到view-page，并传入url
    protected void addPageFragmentBindData(Fragment fragment, String selfUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ExtraPass.SELF_URL, selfUrl);
        fragment.setArguments(bundle);
        addDefaultFragment(fragment);
    }

    //添加fragment到view-page，并传入url和floating-action-button
    protected void addPageFragmentBindData(Fragment fragment, String selfUrl, String fabUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ExtraPass.SELF_URL, selfUrl);
        bundle.putString(ExtraPass.FLOATING_ACTION_BUTTON, fabUrl);
        fragment.setArguments(bundle);
        addDefaultFragment(fragment);
    }

    //添加fragment到view-page，并传入self-url, details-url和floating-action-button, button-name
    protected void addPageFragmentBindData(Fragment fragment, String selfUrl, String detailsUrl,
                                           String fabUrl, String name) {
        Bundle bundle = new Bundle();
        bundle.putString(ExtraPass.SELF_URL, selfUrl);
        bundle.putString(ExtraPass.DETAILS_URL, detailsUrl);
        bundle.putString(ExtraPass.FLOATING_ACTION_BUTTON, fabUrl);
        bundle.putString(ExtraPass.NAME, name);
        fragment.setArguments(bundle);
        addDefaultFragment(fragment);
    }

    //添加fragment到view-page，并传入url
    protected void addPageFragmentBindData(ArrayList<Fragment> fragments, String[] titles,
                                           List<String> selfUrls) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);

            Bundle bundle = new Bundle();
            bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));
            fragment.setArguments(bundle);
        }

        addMultiTabFragments(fragments, titles);
    }

    //添加fragment到view-page，每次传入新的url
    protected void addPageFragmentBindNewData(ArrayList<Fragment> fragments, String[] titles,
                                              List<String> selfUrls) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            Bundle bundle = fragment.getArguments();
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));
                fragment.setArguments(bundle);
            } else {
                bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));
            }
        }

        addMultiTabFragments(fragments, titles);
    }

    //添加fragment到view-page，并传入self-url和details-url, floating-action-button, 
    protected void addPageFragmentBindData(ArrayList<Fragment> fragments, String[] titles,
                                           List<String> selfUrls, List<String> detailsUrls) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);

            Bundle bundle = new Bundle();
            bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));
            bundle.putString(ExtraPass.DETAILS_URL, detailsUrls.get(i));
            fragment.setArguments(bundle);
        }

        addMultiTabFragments(fragments, titles);
    }

    //显示floating-action-button，并设置监听事件
    protected void setFABClickStatus(final Class cls, final String url) {
        if (url != null) {
            setFloatingButtonVisible(true);
            setFloatingButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityBindData(cls, url);
                }
            });
        }
    }

    //获取上一层数据传过来的floating-action-button
    protected String getFloatingActionButtonUrl() {
        return getIntent().getStringExtra(ExtraPass.FLOATING_ACTION_BUTTON);
    }


    protected void getHttpSelfUrlData(String url) {
        CommonRequest.getUrlData(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHttpSuccess(success.toString());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setVisibility(BaseView.Visibility.ERROR);
            }
        });
    }

    protected void getHttpSelfUrlDataForPage(String url) {
        CommonRequest.getUrlDataForPage(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHttpSuccess(success.toString());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                setVisibility(BaseView.Visibility.ERROR);
            }
        });
    }


    protected void getHttpSuccess(String json) {
    }

    //根据url获取服务器数据
    public void getHostUrlData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }


    protected void getHostUrlDataSuccess(String json) {
    }


}
