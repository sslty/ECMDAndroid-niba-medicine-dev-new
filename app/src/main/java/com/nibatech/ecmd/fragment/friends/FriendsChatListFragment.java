package com.nibatech.ecmd.fragment.friends;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.friends.ChatFriendsActivity;
import com.nibatech.ecmd.common.bean.friends.FriendChatListBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.realm.database.FriendsChatRealm;
import com.nibatech.ecmd.common.realm.object.FriendsChatObject;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.FriendRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.MessageListViewFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.BaseCustomRecycleListView;
import com.nibatech.ecmd.view.listview.InformationCustomListView;
import com.tencent.TIMMessage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.realm.RealmResults;

/**
 * 医生端   好友-聊天
 */
public class FriendsChatListFragment extends MessageListViewFragment
        implements MessageListViewFragment.ShowMessageListViewDataListener, Observer {
    private static final String SEARCH_KEY_CD_NUMBER = "cdNumber";
    private static final String SEARCH_KEY_UPDATED_TIME = "updatedTime";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        initMessageListView((InformationCustomListView) view.findViewById(R.id.information_recycle_view), this);
    }

    protected void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    public void getHostUrlDataSuccess(String json) {
        FriendChatListBean friendChatListBean = UIUtils.fromJson(json, FriendChatListBean.class);

        if (friendChatListBean != null && isSetDataToView()) {
            saveDataToRealm(friendChatListBean.getChats());
            MessageEvent.getInstance().addObserver(this);
            showSimpleListData();
        }
    }

    private void saveDataToRealm(List<FriendChatListBean.Chat> chats) {
        for (int i = 0; i < chats.size(); i++) {
            FriendsChatObject friendsChatObject = new FriendsChatObject();
            friendsChatObject.setCharUrl(chats.get(i).getSelfUrl());
            friendsChatObject.setDoctor(chats.get(i).getDoctorProfile());
            friendsChatObject.setUpdatedTime(chats.get(i).getMessageInfo().getTime());
            friendsChatObject.setUnReadNumber((int) new ChatConversation(chats.get(i).
                    getDoctorProfile().getCdNumber()).getUnreadMessageNum());
            friendsChatObject.setCdNumber(chats.get(i).getDoctorProfile().getCdNumber());
            friendsChatObject.setChatId(chats.get(i).getChatId());
            //把服务器的数据与本地对比，如果cd-number存在，只修改内容，如果不存在，新建一条数据
            FriendsChatRealm.insert(friendsChatObject, SEARCH_KEY_CD_NUMBER);
        }
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        setMessageHasRead((String) adapterList.get(position).get(DataKey.KEY_CD_NUMBER), position);

        startActivityBindData(ChatFriendsActivity.class,
                (String) adapterList.get(position).get(DataKey.KEY_CHAT_URL),
                (String) adapterList.get(position).get(DataKey.KEY_NAME));
    }

    @Override
    public void showViewItem(BaseCustomRecycleListView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        viewHolder.getHeadItemView(R.id.headItemView)
                .setHeadViewImageAndGender(
                        (String) adapterList.get(position).get(DataKey.KEY_AVATAR),
                        (String) adapterList.get(position).get(DataKey.KEY_GENDER),
                        (String) adapterList.get(position).get(DataKey.KEY_NAME))
                .setHeadViewTip((int) adapterList.get(position).get(DataKey.KEY_UN_READ_NUMBER))
                .showEmptyTopView()
                .setHospital((String) adapterList.get(position).get(DataKey.KEY_TYPE))
                .setIntentData((String) adapterList.get(position).get(DataKey.KEY_HOMEPAGE), BroadCast.NOT_REFRESH_FRIENDS_CHAT)
                .showVIP(true);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            return;
        }
        String peer = ((TIMMessage) data).getConversation().getPeer();

        //如果list列表中没有此人对聊天消息，就新增一条item
        if (FriendsChatRealm.search(SEARCH_KEY_CD_NUMBER, peer).size() == 0) {
            insertMessageList(peer);
        } else {
            modifyMessageList(((TIMMessage) data));
        }
    }

    @Override
    public List<Map<String, Object>> sortRealmToList() {
        RealmResults<FriendsChatObject> result = FriendsChatRealm.sort(SEARCH_KEY_UPDATED_TIME);
        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            setDoctorProfileToMap(result.get(i).getDoctor(), map);
            map.put(DataKey.KEY_CHAT_URL, result.get(i).getCharUrl());
            map.put(DataKey.KEY_UN_READ_NUMBER, result.get(i).getUnReadNumber());
            map.put(DataKey.KEY_ID, result.get(i).getChatId());
            list.add(map);
        }

        //清除已读状态的锁
        FriendsChatRealm.setItemUnLockRead();

        return list;
    }

    private void addItemToRealm(FriendChatListBean.Chat chat) {
        FriendsChatObject friendsChatObject = new FriendsChatObject();

        friendsChatObject.setCharUrl(chat.getSelfUrl());
        friendsChatObject.setDoctor(chat.getDoctorProfile());
        friendsChatObject.setUpdatedTime(chat.getMessageInfo().getTime());
        friendsChatObject.setUnReadNumber((int) new ChatConversation(chat.
                getDoctorProfile().getCdNumber()).getUnreadMessageNum());
        friendsChatObject.setCdNumber(chat.getDoctorProfile().getCdNumber());
        friendsChatObject.setChatId(chat.getChatId());

        FriendsChatRealm.add(friendsChatObject);
    }

    private void insertMessageList(String peer) {
        FriendRequest.getChatItemByCdNumber(getIntentDetailsUrl(), peer, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getChatItemSuccess(success.toString());
            }
        });
    }

    private void getChatItemSuccess(String json) {
        FriendChatListBean.Chat chat = UIUtils.fromJson(json, FriendChatListBean.Chat.class);
        addItemToRealm(chat);

        showSimpleListData();
    }

    private void modifyMessageList(TIMMessage message) {
        FriendsChatRealm.setItemUnReadNumber(SEARCH_KEY_CD_NUMBER,
                message.getConversation().getPeer(),
                UIUtils.getTimeNow(),
                (int) message.getConversation().getUnreadMessageNum());
        showSimpleListData();
    }

    private void setMessageHasRead(String peer, int position) {
        FriendsChatRealm.setItemHasReadLocked(SEARCH_KEY_CD_NUMBER, peer);
        notifyDataChanged(position);
    }
}
