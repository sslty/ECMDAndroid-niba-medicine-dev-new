package com.nibatech.ecmd.fragment.friends;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.friends.ChatFriendsActivity;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.friends.FriendChatContactBean;
import com.nibatech.ecmd.common.bean.friends.FriendListBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.broadcast.BroadCastCallBack;
import com.nibatech.ecmd.common.contact.Contact;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.ViewPageLoadingFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.ContactView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生端   好友-通讯录
 */

public class FriendsContactListFragment extends ViewPageLoadingFragment
        implements ContactView.ContactListener {

    private ArrayList<Contact> contactList = new ArrayList<>();
    private ContactView contactView;
    private BroadCast broadCast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_contact_list, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerBroadCast();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unRegisterBroadCast();
    }

    private void registerBroadCast() {
        broadCast = new BroadCast(getActivity(), BroadCast.REFRESH_FRIENDS_CONTACT,
                new BroadCastCallBack() {
                    @Override
                    public void onRefresh(String action) {
                        if (action.equals(BroadCast.REFRESH_FRIENDS_CONTACT)) {
                            getHttpData();
                        }
                    }
                });
    }

    private void unRegisterBroadCast() {
        if (broadCast != null) {
            broadCast.unRegister();
        }
    }

    @Override
    protected void getHttpData() {
//        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getHostUrlDataSuccess(success.toString());
//            }
//        });
        getHostUrlDataSuccess("");
    }

    private void initView(View view) {
        contactView = (ContactView) view.findViewById(R.id.contactView);
        contactView.setContactListener(this);
    }

    public void getHostUrlDataSuccess(String json) {
//        FriendListBean friendListBean = new Gson().fromJson(json, FriendListBean.class);
//        loadingContacts(friendListBean.getFriends());
        List<FriendListBean.Friend> friendList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FriendListBean.Friend friend = new FriendListBean.Friend();
            friend.setCreateChatUrl("");

            DoctorProfileBean doctorProfileBean = new DoctorProfileBean();
            doctorProfileBean.setAge(10);
            doctorProfileBean.setAvatarUrl("");
            doctorProfileBean.setCdNumber("2332");
            doctorProfileBean.setExcellent(true);
            String chars = "abcdjkl";
            doctorProfileBean.setFullName("" + chars.charAt((int) (Math.random() * 7)));
            doctorProfileBean.setGender("男");
            doctorProfileBean.setVerified(true);
            friend.setDoctorProfile(doctorProfileBean);
            friendList.add(friend);
        }

        loadingContacts(friendList);
    }

    private void loadingContacts(List<FriendListBean.Friend> friends) {
        contactList.clear();
        for (FriendListBean.Friend friend : friends) {
            Contact contact = new Contact();
            contact.setDoctorProfile(friend.getDoctorProfile(), friend.getCreateChatUrl());
            contactList.add(contact);
        }
        contactView.addContacts(contactList);
    }

    @Override
    public void onItemClick(List<Contact> contactList, int position) {
        requestCreateChat(contactList.get(position).getChatUrl(),
                contactList.get(position).getDoctorProfile().getFullName());
    }


    private void requestCreateChat(String url, final String name) {
//        CommonRequest.postUrlData(url, new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                postCreateChatSuccess(success.toString(), name);
//            }
//        });
        postCreateChatSuccess("", name);
    }

    private void postCreateChatSuccess(String json, String name) {
//        FriendChatContactBean friendChatBean = UIUtils.fromJson(json, FriendChatContactBean.class);
//        //医生信息
//        friendChatBean.getDoctorProfile();
//        friendChatBean.getChatId();
//        friendChatBean.getMessageInfo();
//        friendChatBean.getSelfUrl();
//
//        startActivityBindData(ChatFriendsActivity.class,
//                friendChatBean.getSelfUrl(), name);
        startActivityNotBindData(ChatFriendsActivity.class);
    }
}
