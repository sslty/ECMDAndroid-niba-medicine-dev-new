package com.nibatech.ecmd.fragment.friends;


import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.common.bean.friends.FriendUrlBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生端   好友
 */
public class FriendsViewPageFragment extends BaseFragment {
    private String[] mTopTitles = new String[]{
            "聊天", "通讯录"
    };
    ArrayList<Fragment> fragments;


    @Override
    protected void onCreateSuccessView(View successView) {
        super.onCreateSuccessView(successView);
        fragments = new ArrayList<>();
        fragments.add(new FriendsChatListFragment());
        fragments.add(new FriendsContactListFragment());
    }

    public void setStrSelfUrl(String url) {
//        CommonRequest.getUrlData(url, new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getHostUrlDataSuccess(success.toString());
//            }
//        });
        getHostUrlDataSuccess();
    }

    private void getHostUrlDataSuccess(String json) {
        if (fragments != null && fragments.size() > 0) {
            FriendUrlBean friendUrlBean = UIUtils.fromJson(json, FriendUrlBean.class);
            //self-urls
            List<String> selfUrls = new ArrayList<>();
            selfUrls.add(friendUrlBean.getChatListUrl());//聊天
            selfUrls.add(friendUrlBean.getContactUrl());//通讯录
            //details-urls
            List<String> detailsUrls = new ArrayList<>();
            detailsUrls.add(friendUrlBean.getGetChatUrl());//聊天
            detailsUrls.add(friendUrlBean.getGetChatUrl());//通讯录

            addPageFragmentBindData(fragments, mTopTitles, selfUrls, detailsUrls);
        }
    }

    private void getHostUrlDataSuccess() {
        if (fragments != null && fragments.size() > 0) {
            //self-urls
            List<String> selfUrls = new ArrayList<>();
            selfUrls.add("");//聊天
            selfUrls.add("");//通讯录
            //details-urls
            List<String> detailsUrls = new ArrayList<>();
            detailsUrls.add("");//聊天
            detailsUrls.add("");//通讯录

            addPageFragmentBindData(fragments, mTopTitles, selfUrls, detailsUrls);
        }
    }
}
