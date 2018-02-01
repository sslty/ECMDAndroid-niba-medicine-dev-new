package com.nibatech.ecmd.activity.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.message.ChatConversation;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.fragment.friends.FriendsViewPageFragment;
import com.nibatech.ecmd.fragment.homepage.DoctorHomePageFragment;
import com.nibatech.ecmd.fragment.homepage.HomePageFragment;
import com.nibatech.ecmd.fragment.mine.MineFragment;
import com.nibatech.ecmd.fragment.mypatient.MyPatientFragment;
import com.tencent.TIMConversation;
import com.tencent.TIMMessage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * 医生端   首页
 */
public class DoctorHomePageActivity extends HomePageActivity implements Observer {
    private String[] mBottomTitles = {"首页", "好友", "患者", "我的"};
    private int[] mIconUnSelectIds = {
            R.drawable.bottom_bar_home_hide, R.drawable.bottom_bar_friends_hide,
            R.drawable.bottom_bar_patient_hide, R.drawable.bottom_bar_mine_hide};
    private int[] mIconSelectIds = {
            R.drawable.bottom_bar_home_show, R.drawable.bottom_bar_friends_show,
            R.drawable.bottom_bar_patient_show, R.drawable.bottom_bar_mine_show};
    private ArrayList<Fragment> bottomFragmentList = new ArrayList<>();
    private FriendsViewPageFragment friendsViewPageFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageEvent.getInstance().addObserver(this);

        addFragmentsToHomePage();

        //显示搜索按钮
        findMessageButton().setVisibility(View.VISIBLE);
        //去掉后退功能
        hideBackOnToolbar();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ChatConversation.getConversationTotalUnreadNum(PEER_DOCTOR) > 0) {
            showMessageDot(1);
        } else {
            hideMessageDot(1);
        }
        if (ChatConversation.getConversationTotalUnreadNum(PEER_PATIENT) > 0) {
            showMessageDot(2);
        } else {
            hideMessageDot(2);
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            return;
        }
        TIMConversation conversation = ((TIMMessage) data).getConversation();
        if (conversation.getPeer().substring(0, 1).toUpperCase().equals(PEER_DOCTOR)) {
            if (conversation.getUnreadMessageNum() > 0) {
                showMessageDot(1);
            } else {
                hideMessageDot(1);
            }
        } else {
            if (conversation.getUnreadMessageNum() > 0) {
                showMessageDot(2);
            } else {
                hideMessageDot(2);
            }
        }
    }


    @Override
    public void onTabSelect(int position) {
        super.onTabSelect(position);

        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                break;
            case 1:
                friendsViewPageFragment.setStrSelfUrl(HomePageFragment.getAPIsUrl(this).getEntranceDoctorFriends());
                title = getString(R.string.friends);
                break;
            case 2:
                title = getString(R.string.patient);
                break;
            case 3:
                mineFragment.setStrSelfUrl(HomePageFragment.getAPIsUrl(this).getEntranceDoctorMine());
                title = getString(R.string.mine);
                break;
        }

        setToolBarText(title);
    }


    public void addFragmentsToHomePage() {
        friendsViewPageFragment = new FriendsViewPageFragment();
        mineFragment = new MineFragment();

        bottomFragmentList.add(new DoctorHomePageFragment());//首页
        bottomFragmentList.add(friendsViewPageFragment);//好友
        bottomFragmentList.add(new MyPatientFragment());//病人
        bottomFragmentList.add(mineFragment);//我的
        addHomePageBottomTab(bottomFragmentList, mBottomTitles, mIconUnSelectIds, mIconSelectIds);
    }
}