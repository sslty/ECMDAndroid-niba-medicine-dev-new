package com.nibatech.ecmd.fragment.mydoctor;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.ChatNormalActivity;
import com.nibatech.ecmd.common.bean.chat.ChatFansDoctorListBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.realm.database.DoctorChatRealm;
import com.nibatech.ecmd.common.realm.object.DoctorChatObject;
import com.nibatech.ecmd.common.request.FansRequest;
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
 * 患者端   我的医生-医生对话
 */
public class DoctorChatListFragment extends MessageListViewFragment
        implements MessageListViewFragment.ShowMessageListViewDataListener, Observer {
    private static final String SEARCH_KEY_CD_NUMBER = "cdNumber";
    private static final String SEARCH_KEY_UPDATED_TIME = "updatedTime";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        initMessageListView((InformationCustomListView) view.findViewById(R.id.information_recycle_view), this);
    }

    protected void getHttpData() {
        FansRequest.getOfflineDoctorList(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    public void getHostUrlDataSuccess(String json) {
        ChatFansDoctorListBean chatFansDoctorListBean = new Gson().fromJson(json, ChatFansDoctorListBean.class);

        if (chatFansDoctorListBean != null && isSetDataToView()) {
            saveDataToRealm(chatFansDoctorListBean.getOfflineDoctors());
            MessageEvent.getInstance().addObserver(this);
            showSimpleListData();
        }
    }

    @Override
    public void clickViewItem(RecyclerView.ViewHolder viewHolder, List<Map<String, Object>> adapterList, int position) {
        setMessageHasRead((String) adapterList.get(position).get(DataKey.KEY_CD_NUMBER), position);

        startActivityBindData(ChatNormalActivity.class,
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
                .setHospital((String) adapterList.get(position).get(DataKey.KEY_TYPE))
                .setIntentData((String) adapterList.get(position).get(DataKey.KEY_HOMEPAGE), BroadCast.NOT_REFRESH_DOCTORS_CHAT)
                .showVIP(true);
    }

    private void saveDataToRealm(List<ChatFansDoctorListBean.OfflineDoctor> doctors) {
        /*for (int i = 0; i < chats.size(); i++) {
            FriendsChatObject friendsChatObject = new FriendsChatObject();
            friendsChatObject.setCharUrl(chats.get(i).getSelfUrl());
            friendsChatObject.setDoctor(chats.get(i).getDoctorProfile());
            friendsChatObject.setUpdatedTime(chats.get(i).getMessageInfo().getTime());
            friendsChatObject.setUnReadNumber((int) new ChatConversation(chats.get(i).
                    getDoctorProfile().getPeer()).getUnreadMessageNum());
            friendsChatObject.setPeer(chats.get(i).getDoctorProfile().getPeer());
            friendsChatObject.setChatId(chats.get(i).getChatId());
            //把服务器的数据与本地对比，如果cd-number存在，只修改内容，如果不存在，新建一条数据
            DoctorChatRealm.insert(friendsChatObject, SEARCH_KEY_CD_NUMBER);
        }*/
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            return;
        }
        String peer = ((TIMMessage) data).getConversation().getPeer();

        //如果list列表中没有此人对聊天消息，就新增一条item
        if (DoctorChatRealm.search(SEARCH_KEY_CD_NUMBER, peer).size() == 0) {
            insertMessageList(peer);
        } else {
            modifyMessageList(((TIMMessage) data));
        }
    }

    @Override
    public List<Map<String, Object>> sortRealmToList() {
        RealmResults<DoctorChatObject> result = DoctorChatRealm.sort(SEARCH_KEY_UPDATED_TIME);
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
        DoctorChatRealm.setItemUnLockRead();

        return list;
    }

    private void addItemToRealm() {
       /* FriendsChatObject friendsChatObject = new FriendsChatObject();

        friendsChatObject.setCharUrl(chat.getSelfUrl());
        friendsChatObject.setDoctor(chat.getDoctorProfile());
        friendsChatObject.setUpdatedTime(chat.getMessageInfo().getTime());
        friendsChatObject.setUnReadNumber((int) new ChatConversation(chat.
                getDoctorProfile().getPeer()).getUnreadMessageNum());
        friendsChatObject.setPeer(chat.getDoctorProfile().getPeer());
        friendsChatObject.setChatId(chat.getChatId());

        DoctorChatRealm.add(friendsChatObject);*/
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
        //DoctorChatListBean.Chat chat = UIUtils.fromJson(json, DoctorChatListBean.Chat.class);
        //addItemToRealm(chat);

        showSimpleListData();
    }

    private void modifyMessageList(TIMMessage message) {
        DoctorChatRealm.setItemUnReadNumber(SEARCH_KEY_CD_NUMBER,
                message.getConversation().getPeer(),
                UIUtils.getTimeNow(),
                (int) message.getConversation().getUnreadMessageNum());
        showSimpleListData();
    }

    private void setMessageHasRead(String peer, int position) {
        DoctorChatRealm.setItemHasReadLocked(SEARCH_KEY_CD_NUMBER, peer);
        notifyDataChanged(position);
    }
}
