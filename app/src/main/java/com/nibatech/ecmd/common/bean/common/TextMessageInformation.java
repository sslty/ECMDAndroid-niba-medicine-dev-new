package com.nibatech.ecmd.common.bean.common;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.utils.ChatUtils;
import com.nibatech.ecmd.view.MsgIncomingView;
import com.nibatech.ecmd.view.MsgOutgoingView;
import com.tencent.TIMMessage;

public class TextMessageInformation extends MessageInformation {
    private String mMsg;//信息内容
    private boolean mCustom = false;
    private String type;

    public TextMessageInformation(ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(identityBean, chatMaterialBean, isMe);
        this.mMsg = chatMaterialBean.getName();
        this.type = chatMaterialBean.getType();
        if (!this.type.equals(ChatConversation.MSG)) {
            this.mCustom = true;
        }
    }


    public TextMessageInformation(ChatConversation chatConversation, TIMMessage message, ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(chatConversation, message, identityBean, chatMaterialBean, isMe);
        this.mMsg = chatMaterialBean.getName();
        this.type = chatMaterialBean.getType();
        if (!this.type.equals(ChatConversation.MSG)) {
            this.mCustom = true;
        }

    }


    @Override
    public void showMessage(MsgAdapter.ViewHolder viewHolder, Context context) {
        LinearLayout llChatView = getChatView(viewHolder, context);
        if (IsMyMsg()) {
            llChatView.removeViews(1, llChatView.getChildCount() - 1);
            MsgOutgoingView msgOutgoingView = new MsgOutgoingView(context);
            ChatUtils.spannableEmoticonFilter(msgOutgoingView.getMsgTextView(), mMsg);
            if (IsCustom()) {
                msgOutgoingView.getCustomImageView().setVisibility(View.VISIBLE);
            } else {
                msgOutgoingView.getCustomImageView().setVisibility(View.GONE);
            }
            llChatView.addView(msgOutgoingView);
        } else {
            llChatView.removeAllViews();
            MsgIncomingView msgIncomingView = new MsgIncomingView(context);
            ChatUtils.spannableEmoticonFilter(msgIncomingView.getMsgTextView(), mMsg);
            if (IsCustom()) {
                msgIncomingView.getCustomImageView().setVisibility(View.VISIBLE);
            } else {
                msgIncomingView.getCustomImageView().setVisibility(View.GONE);
            }
            llChatView.addView(msgIncomingView);
        }
    }

    private boolean IsCustom() {
        if (mCustom) {
            return true;
        } else {
            return false;
        }
    }


}
