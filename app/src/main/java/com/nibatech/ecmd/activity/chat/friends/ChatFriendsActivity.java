package com.nibatech.ecmd.activity.chat.friends;


import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.chat.ChatPaddingActivity;
import com.nibatech.ecmd.fragment.friends.FriendsChatFragment;


/**
 * 医生端   好友-聊天
 */
public class ChatFriendsActivity extends ChatPaddingActivity {
    FriendsChatFragment friendsChatFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText(getIntentTitle());
        addPageFragmentBindData(friendsChatFragment = new FriendsChatFragment(), getIntentSelfUrl());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        friendsChatFragment.onActivityResult(requestCode, resultCode, data);
    }
}
