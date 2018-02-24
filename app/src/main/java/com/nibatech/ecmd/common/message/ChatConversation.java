package com.nibatech.ecmd.common.message;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.chat.ChatConversationBean;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.bean.common.MessageInformation;
import com.nibatech.ecmd.common.request.ChatRequest;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.factory.MessageFactory;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.ChatView;
import com.nibatech.ecmd.view.CustomEmoticonsKeyBoard;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.nibatech.ecmd.common.request.ChatRequest.getMessageFromHost;

public class ChatConversation {
    private static String TAG = "ChatConversation";
    private CustomEmoticonsKeyBoard ekBar;
    private ListView lvMsg;
    private MsgAdapter msgAdapter;
    private TIMConversation conversation;
    private Refresh refresh;
    private String selfUrl;
    private boolean isFirstButReload = false;
    private ChatIdentityBean mineBean;
    private ChatIdentityBean peerBean;
    private static final int PER_PAGE = 10;
    public static final String MSG_AGAIN = "again";
    public static final String MSG_PUT_GUIDE = "putGuide";
    public static final String MSG_END_GUIDE = "endGuide";
    public static final String MSG_SUPPLY = "supply";
    public static final String MSG_EMOTIONS_IMAGE = "eImage";
    public static final String MSG_IMAGE = "image";
    public static final String MSG = "msg";
    private ChatConversationBean chatConversationBean;


    public ChatConversation(ChatView chatView, ChatIdentityBean mineBean, ChatIdentityBean peerBean, String url) {
        this.lvMsg = chatView.getListView();
        this.msgAdapter = chatView.getListAdapter();
        this.ekBar = chatView.getEkBar();
        this.selfUrl = url;
        this.mineBean = mineBean;
        this.peerBean = peerBean;
        //建立会话
        conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, peerBean.getPeer());
        conversation.setReadMessage();
        //从服务器一次性拉取聊天记录
//        CommonRequest.getCommonMoreList(url, PER_PAGE, new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                chatConversationBean = new Gson().fromJson(success.toString(), ChatConversationBean.class);
//                CommonRequest.getUrlData(chatConversationBean.getPages().getLastUrl(), new VolleyCallback() {
//                    @Override
//                    public void onSuccessResponse(JSONObject success) {
//                        getConversationSuccess(success.toString(), true);
//                    }
//                });
//            }
//        });
//
//        this.lvMsg.setOnScrollListener(new AbsListView.OnScrollListener() {
//            private int firstItem;
//
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
//                if (scrollState == SCROLL_STATE_IDLE && firstItem == 0) {
//                    getMessageFromHost(chatConversationBean.getPages().getPrevUrl(), new VolleyCallback() {
//                        @Override
//                        public void onSuccessResponse(JSONObject success) {
//                            getConversationSuccess(success.toString(), false);
//                        }
//                    });
//                }
//                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
//                    ekBar.reset();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                firstItem = firstVisibleItem;
//            }
//        });
    }

    public ChatConversation(String peer) {
        //建立会话
        conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, peer);
    }

    private void getConversationSuccess(String success, boolean isFirst) {
        chatConversationBean = new Gson().fromJson(success, ChatConversationBean.class);
        List<ChatConversationBean.Conversation> conversations = chatConversationBean.getConversations();
        for (int i = 0; i < conversations.size(); i++) {
            //消息内容
            ChatConversationBean.Conversation conversation = conversations.get(i);
            try {
                ChatMaterialBean chatMaterialBean = new ChatMaterialBean();
                chatMaterialBean.setName(conversation.getMessage());
                chatMaterialBean.setTime(conversation.getCreatedTime());
                chatMaterialBean.setType(conversation.getMsgType());
                //我还是对方
                MessageInformation messageInformation;
                if (BaseVolleyRequest.getLogin().getUser().getUserId() == conversations.get(i).getFromId()) {
                    messageInformation = MessageFactory.getMessage(mineBean, chatMaterialBean, true);
                    if (isFirst) {
                        messageInformation.setHasTime(msgAdapter.getArray().size() > 0 ? msgAdapter.getArray().get(msgAdapter.getArray().size() - 1) : null);
                        msgAdapter.getArray().add(messageInformation);
                    } else {
                        msgAdapter.getArray().add(i, messageInformation);
                        if (i == 0) {
                            messageInformation.setHasTime(null);
                        }
                    }
                } else {
                    messageInformation = MessageFactory.getMessage(peerBean, chatMaterialBean, false);
                    if (isFirst) {
                        messageInformation.setHasTime(msgAdapter.getArray().size() > 0 ? msgAdapter.getArray().get(msgAdapter.getArray().size() - 1) : null);
                        msgAdapter.getArray().add(messageInformation);
                    } else {
                        msgAdapter.getArray().add(i, messageInformation);
                        if (i == 0) {
                            messageInformation.setHasTime(null);
                        }
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "bean change to Gson error");
            }
        }

        if (isFirst && conversations.size() < PER_PAGE) {
            isFirstButReload = true;
            if (chatConversationBean.getPages().getPrevUrl() != null) {
                getMessageFromHost(chatConversationBean.getPages().getPrevUrl(), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        getConversationSuccess(success.toString(), false);
                    }
                });
            } else {
                msgAdapter.notifyDataSetChanged();
                lvMsg.setSelection(lvMsg.getBottom());
            }
            return;
        }

        msgAdapter.notifyDataSetChanged();
        if (isFirst || isFirstButReload) {
            isFirstButReload = false;
            lvMsg.setSelection(lvMsg.getBottom());
        } else {
            lvMsg.setSelection(conversations.size());
        }
    }

    public void sendMessage(ChatMaterialBean bean) {
//        if (!BaseVolleyRequest.getLogin().getUser().getCdNumber().equals(TIMManager.getInstance().getLoginUser())) {
//            Toast.makeText(UIUtils.getContext(), "消息系统未连接，请稍等片刻重新发送或重新登陆！", Toast.LENGTH_SHORT).show();
//        } else {
//            if (!bean.getName().isEmpty()) {
//                sendHttpHost(bean);//host断网时，没有发送到host，所以一次拉取不到，显示不到界面上
//                sendToTIM(bean);//腾讯断网时，会走onError,并且会显示在界面上
//            }
//        }
        sendToTIM(bean);
    }


    private void sendHttpHost(ChatMaterialBean bean) {
        ChatRequest.postMessageToHost(selfUrl, bean.getName(), bean.getType(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
            }
        });
    }

    private void sendToTIM(final ChatMaterialBean bean) {
        final TIMMessage message = new TIMMessage();
        String data = new Gson().toJson(bean);
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData(data.getBytes());
        message.addElement(elem);
        //发送消息
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {//发送消息回调
            @Override
            public void onError(int code, String desc) {
                Log.d(TAG, "send message failed. code: " + code + " errmsg: " + desc);
                onSendMessageFail(code, message);
            }

            @Override
            public void onSuccess(TIMMessage msg) {//发送消息成功
                Log.e(TAG, "SendMsg ok");
                MessageEvent.getInstance().onNewMessage(null);
                if (refresh != null && !bean.getType().equals(MSG)) {
                    refresh.refreshButton(bean);
                }
            }
        });

        MessageEvent.getInstance().onNewMessage(message);
    }

    public void remove(MessageInformation messageInformation) {
        msgAdapter.getArray().remove(messageInformation);
    }

    private void onSendMessageFail(int code, TIMMessage message) {
        long id = message.getMsgUniqueId();
        for (MessageInformation msgInfo : msgAdapter.getArray()) {
            if (msgInfo.getMessage() != null && msgInfo.getMessage().getMsgUniqueId() == id) {
                switch (code) {
                    case 80001://发送内容包含敏感词
                    case 6200://断网情况
                        msgAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }


    public void showMessage(TIMMessage msg) {
        //只显示和当前的用户的聊天记录
        if (msg.getConversation().getPeer().equals(getPeer())) {
            TIMElem elem = msg.getElement(0);
            TIMCustomElem customElem = (TIMCustomElem) elem;
            byte[] bytes = customElem.getData();
            String dataJson = new String(bytes);
            ChatMaterialBean chatMaterialBean = new Gson().fromJson(dataJson, ChatMaterialBean.class);
            MessageInformation messageInformation;
            if (msg.isSelf()) {
                messageInformation = MessageFactory.getMessage(this, msg, mineBean, chatMaterialBean, true);
            } else {
                messageInformation = MessageFactory.getMessage(this, msg, peerBean, chatMaterialBean, false);
            }
            messageInformation.setHasTime(msgAdapter.getArray().size() > 0 ? msgAdapter.getArray().get(msgAdapter.getArray().size() - 1) : null);
            msgAdapter.getArray().add(messageInformation);

            msgAdapter.notifyDataSetChanged();
            lvMsg.setSelection(lvMsg.getBottom());
            if (refresh != null) {
                refresh.refreshButton(chatMaterialBean);
            }
        }
    }


    public static ArrayList<Integer> getUnReadMessageNumList(List<String> peerList) {
        ArrayList<Integer> unReadMessageNum = new ArrayList<>();
        for (int i = 0; i < peerList.size(); i++) {
            unReadMessageNum.add((int) new ChatConversation(peerList.get(i)).getUnreadMessageNum());
        }
        return unReadMessageNum;
    }

    public void setRefreshButton(Refresh refresh) {
        this.refresh = refresh;
    }

    public interface Refresh {
        void refreshButton(ChatMaterialBean chatMaterialBean);
    }

    public void setReadMessage() {
        conversation.setReadMessage();
    }

    private String getPeer() {
        return conversation.getPeer();
    }

    public long getUnreadMessageNum() {
        return conversation.getUnreadMessageNum();
    }

    public static long getConversationTotalUnreadNum() {
        return getConversationTotalUnreadNum(null);
    }

    public static long getConversationTotalUnreadNum(String peer) {
        List<TIMConversation> conversationList = new ArrayList<>();
        long cnt = TIMManager.getInstance().getConversationCount();
        Log.d(TAG, "get " + cnt + " conversations");
        for (long i = 0; i < cnt; ++i) {
            final TIMConversation conversation = TIMManager.getInstance().getConversationByIndex(i);
            if (conversation.getType() == TIMConversationType.System) continue;
            if (peer != null && !conversation.getPeer().substring(0, 1).toUpperCase().equals(peer))
                continue;
            conversationList.add(conversation);
        }
        long num = 0;
        for (TIMConversation conversation : conversationList) {
            num += conversation.getUnreadMessageNum();
        }
        return num;
    }
}