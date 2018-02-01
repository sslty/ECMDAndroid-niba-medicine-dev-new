package com.nibatech.ecmd.common.bean.common;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.chat.ChatIdentityBean;
import com.nibatech.ecmd.common.bean.chat.ChatMaterialBean;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.utils.TimeUtil;
import com.nibatech.ecmd.utils.UIUtils;
import com.tencent.TIMMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class MessageInformation {
    private String mTime;
    private String mName;
    private boolean mAgain = false;
    private boolean hasTime;
    private boolean isMe;
    private String mHeadUrl;
    private String mGender;
    private String type;
    private TIMMessage message;
    private ChatMaterialBean chatMaterialBean;
    private ChatConversation chatConversation;
    private long mTimeStemp;

    public MessageInformation(ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        this.mName = identityBean.getName();
        this.mGender = identityBean.getGender();
        this.mHeadUrl = identityBean.getHeadUrl();
        this.mTime = chatMaterialBean.getTime() != null ?
                UIUtils.timeISO8601RemoveT(chatMaterialBean.getTime())
                : UIUtils.timeISO8601RemoveT(UIUtils.getTimeNow());
        try {
            this.mTimeStemp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(this.mTime).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.isMe = isMe;
        this.type = chatMaterialBean.getType();
        if (this.type.equals(ChatConversation.MSG_AGAIN)) {
            this.mAgain = true;
        }

    }

    public MessageInformation(ChatConversation chatConversation, TIMMessage message, ChatIdentityBean identityBean, ChatMaterialBean chatMaterialBean, boolean isMe) {
        this.chatConversation = chatConversation;
        this.message = message;
        this.chatMaterialBean = chatMaterialBean;

        this.mName = identityBean.getName();
        this.mGender = identityBean.getGender();
        this.mHeadUrl = identityBean.getHeadUrl();
        this.mTime = chatMaterialBean.getTime() != null ?
                UIUtils.timeISO8601RemoveT(chatMaterialBean.getTime())
                : UIUtils.timeISO8601RemoveT(UIUtils.getTimeNow());
        try {
            this.mTimeStemp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(this.mTime).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.isMe = isMe;
        this.type = chatMaterialBean.getType();
        if (this.type.equals(ChatConversation.MSG_AGAIN)) {
            this.mAgain = true;
        }

    }


    public String getTime() {
        return this.mTime;
    }

    public TIMMessage getMessage() {
        return this.message;
    }

    private ChatMaterialBean getChatMaterialBean() {
        return this.chatMaterialBean;
    }

    private ChatConversation getChatConversation() {
        return this.chatConversation;
    }

    private long getmTimeStemp() {
        return this.mTimeStemp;
    }


    protected boolean IsMyMsg() {
        if (isMe) {
            return true;
        } else {
            return false;
        }
    }

    private boolean IsAgain() {
        if (mAgain) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isHasTime() {
        return hasTime;
    }

    public void setHasTime(MessageInformation msgInfo) {
        if (msgInfo == null) {
            hasTime = true;
            return;
        }
        hasTime = Math.abs(this.mTimeStemp - msgInfo.getmTimeStemp()) > 60;
    }

    public abstract void showMessage(MsgAdapter.ViewHolder viewHolder, Context context);


    LinearLayout getChatView(MsgAdapter.ViewHolder viewHolder, Context context) {
        if (IsMyMsg()) {
            //显示我的信息
            viewHolder.llyMy.setVisibility(View.VISIBLE);
            viewHolder.tvNameMy.setText(mName);
            viewHolder.ivHeadMy.setHeadPhotoAndGender(mHeadUrl, mGender);
            viewHolder.lly.setVisibility(View.GONE);

            if (IsAgain()) {
                viewHolder.llAgainMy.setVisibility(View.VISIBLE);
            } else {
                viewHolder.llAgainMy.setVisibility(View.GONE);
            }

            if (isHasTime()) {
                viewHolder.tvTimeMy.setVisibility(View.VISIBLE);
                viewHolder.tvTimeMy.setText(TimeUtil.getChatTimeStr(getmTimeStemp()));
            } else {
                viewHolder.tvTimeMy.setVisibility(View.GONE);
            }

            showStatus(viewHolder, context);
            return viewHolder.llOutgoing;

        } else {
            viewHolder.lly.setVisibility(View.VISIBLE);
            viewHolder.tvName.setText(mName);
            viewHolder.ivHead.setHeadPhotoAndGender(mHeadUrl, mGender);
            viewHolder.llyMy.setVisibility(View.GONE);

            if (IsAgain()) {
                viewHolder.llAgain.setVisibility(View.VISIBLE);
            } else {
                viewHolder.llAgain.setVisibility(View.GONE);
            }

            if (isHasTime()) {
                viewHolder.tvTime.setVisibility(View.VISIBLE);
                viewHolder.tvTime.setText(TimeUtil.getChatTimeStr(getmTimeStemp()));
            } else {
                viewHolder.tvTime.setVisibility(View.GONE);
            }

            return viewHolder.llIncoming;
        }

    }

    private void showStatus(MsgAdapter.ViewHolder viewHolder, final Context context) {
        if (getMessage() == null) {
            viewHolder.ivSendErrorMy.setVisibility(View.GONE);
            viewHolder.pbSendingMy.setVisibility(View.GONE);
            return;
        }
        switch (getMessage().status()) {
            case Sending:
                viewHolder.ivSendErrorMy.setVisibility(View.GONE);
                viewHolder.pbSendingMy.setVisibility(View.VISIBLE);
                break;
            case SendSucc:
                viewHolder.ivSendErrorMy.setVisibility(View.GONE);
                viewHolder.pbSendingMy.setVisibility(View.GONE);
                break;
            case SendFail:
                viewHolder.ivSendErrorMy.setVisibility(View.VISIBLE);
                viewHolder.pbSendingMy.setVisibility(View.GONE);
                break;
        }

        viewHolder.ivSendErrorMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogBuilder.onCreate(context, "提示", "您是否要重新发送?",
                        true, new AlertDialogListener() {
                            @Override
                            public void selectPositive() {
                                getChatConversation().remove(MessageInformation.this);
                                getChatConversation().sendMessage(getChatMaterialBean());
                            }
                        });
            }
        });
    }

}
