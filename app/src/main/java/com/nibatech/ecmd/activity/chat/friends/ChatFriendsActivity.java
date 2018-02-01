package com.nibatech.ecmd.activity.chat.friends;


import android.os.Bundle;

import com.nibatech.ecmd.activity.chat.ChatPaddingActivity;
import com.nibatech.ecmd.fragment.friends.FriendsChatFragment;


/**
 * 医生端   好友-聊天
 */
public class ChatFriendsActivity extends ChatPaddingActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText(getIntentTitle());
        addPageFragmentBindData(new FriendsChatFragment(), getIntentSelfUrl());
    }

}
