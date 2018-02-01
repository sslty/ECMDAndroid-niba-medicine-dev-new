package com.nibatech.ecmd.factory;

import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.bean.common.EmotionsImageMessageInformation;
import com.nibatech.ecmd.common.bean.common.ImageMessageInformation;
import com.nibatech.ecmd.common.bean.common.MessageInformation;
import com.nibatech.ecmd.common.bean.common.TextMessageInformation;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.tencent.TIMMessage;

/**
 * Authored by sslty on 2017/1/18.
 */
public class MessageFactory {

    public static MessageInformation getMessage(ChatConversation chatConversation, TIMMessage message, ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        switch (chatMaterialBean.getType()) {
            case ChatConversation.MSG:
            case ChatConversation.MSG_SUPPLY:
            case ChatConversation.MSG_PUT_GUIDE:
            case ChatConversation.MSG_END_GUIDE:
            case ChatConversation.MSG_AGAIN:
                return new TextMessageInformation(chatConversation, message, identityBean, chatMaterialBean, isMe);
            case ChatConversation.MSG_EMOTIONS_IMAGE:
                return new EmotionsImageMessageInformation(chatConversation, message, identityBean, chatMaterialBean, isMe);
            case ChatConversation.MSG_IMAGE:
                return new ImageMessageInformation(chatConversation, message, identityBean, chatMaterialBean, isMe);
            default:
                return null;
        }
    }

    public static MessageInformation getMessage(ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        switch (chatMaterialBean.getType()) {//type,max最长不能超过8个字符，超过会走
            case ChatConversation.MSG:
            case ChatConversation.MSG_SUPPLY:
            case ChatConversation.MSG_PUT_GUIDE:
            case ChatConversation.MSG_END_GUIDE:
            case ChatConversation.MSG_AGAIN:
                return new TextMessageInformation(identityBean, chatMaterialBean, isMe);
            case ChatConversation.MSG_EMOTIONS_IMAGE:
                return new EmotionsImageMessageInformation(identityBean, chatMaterialBean, isMe);
            case ChatConversation.MSG_IMAGE:
                return new ImageMessageInformation(identityBean, chatMaterialBean, isMe);
            default:
                return null;
        }
    }

}
