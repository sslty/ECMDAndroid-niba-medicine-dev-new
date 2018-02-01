package com.nibatech.ecmd.common.bean.common;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.utils.ImageLoadUtils;
import com.nibatech.ecmd.utils.UIUtils;
import com.tencent.TIMMessage;

import java.io.IOException;

public class EmotionsImageMessageInformation extends MessageInformation {
    private String emotionsImageUri;//信息内容.为EmotionsImageUri

    public EmotionsImageMessageInformation(ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(identityBean, chatMaterialBean, isMe);
        this.emotionsImageUri = chatMaterialBean.getName();

    }

    public EmotionsImageMessageInformation(ChatConversation chatConversation, TIMMessage message, ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        super(chatConversation, message, identityBean, chatMaterialBean, isMe);
        this.emotionsImageUri = chatMaterialBean.getName();

    }

    @Override
    public void showMessage(MsgAdapter.ViewHolder viewHolder, Context context) {
        try {
            LinearLayout llChatView = getChatView(viewHolder, context);
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, UIUtils.dip2px(10));
            imageView.setLayoutParams(params);
            ImageLoadUtils.getInstance(context).displayImage(emotionsImageUri, imageView);
            if (IsMyMsg()) {
                llChatView.removeViews(1, llChatView.getChildCount() - 1);
                llChatView.addView(imageView);
            } else {
                llChatView.removeAllViews();
                llChatView.addView(imageView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
