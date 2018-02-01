package com.nibatech.ecmd.activity.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.fragment.follow.PatientFollowFragment;
import com.nibatech.ecmd.fragment.homepage.HomePageFragment;
import com.nibatech.ecmd.fragment.homepage.PatientHomePageFragment;
import com.nibatech.ecmd.fragment.mine.MineFragment;
import com.nibatech.ecmd.fragment.mydoctor.MyDoctorViewPageFragment;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * 患者端   首页
 */
public class PatientHomePageActivity extends HomePageActivity implements Observer {
    private String[] mBottomTitles = {"首页", "关注", "医生", "我的"};
    private int[] mIconUnSelectIds = {
            R.drawable.bottom_bar_home_hide, R.drawable.bottom_bar_encyclopedia_hide,
            R.drawable.bottom_bar_patient_hide, R.drawable.bottom_bar_mine_hide};
    private int[] mIconSelectIds = {
            R.drawable.bottom_bar_home_show, R.drawable.bottom_bar_encyclopedia_show,
            R.drawable.bottom_bar_patient_show, R.drawable.bottom_bar_mine_show};
    private ArrayList<Fragment> bottomFragmentList = new ArrayList<>();
    private MineFragment mineFragment;
    private MyDoctorViewPageFragment myDoctorViewPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageEvent.getInstance().addObserver(this);

        mineFragment = new MineFragment();
        myDoctorViewPageFragment = new MyDoctorViewPageFragment();

        bottomFragmentList.add(new PatientHomePageFragment());
        bottomFragmentList.add(new PatientFollowFragment());
        bottomFragmentList.add(myDoctorViewPageFragment);
        bottomFragmentList.add(mineFragment);
        addHomePageBottomTab(bottomFragmentList, mBottomTitles, mIconUnSelectIds, mIconSelectIds);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (ChatConversation.getConversationTotalUnreadNum() > 0) {
//            showMessageDot(3);
//        } else {
//            showMessageDot(3);
//        }
    }


    @Override
    public void update(Observable observable, Object data) {
        if (data == null) {
            return;
        }
//        if (((TIMMessage) data).getConversation().getUnreadMessageNum() > 0) {
//            showMessageDot(3);
//        } else {
//            showMessageDot(3);
//        }
    }


    @Override
    public void onTabSelect(int position) {
        super.onTabSelect(position);

        String title = getString(R.string.app_name);

        switch (position) {
            case 0:
                setPatientNameVisible();
                break;
            case 1:
                title = getString(R.string.following);
                break;
            case 2:
                title = getString(R.string.doctors);
                myDoctorViewPageFragment.setStrSelfUrl(HomePageFragment.getAPIsUrl(this).getEntranceMyDoctors());
                break;
            case 3:
                mineFragment.setStrSelfUrl(HomePageFragment.getAPIsUrl(this).getEntrancePatientMine());
                title = getString(R.string.mine);
                break;
        }

        setToolBarText(title);
    }

    private void initView() {
        //显示消息按钮
        findMessageButton().setVisibility(View.VISIBLE);
        //去掉后退功能
        hideBackOnToolbar();
        //只有在患者界面，显示姓名或者手机号码
        setPatientNameVisible();
    }

    //只有在患者界面，显示姓名或者手机号码
    private void setPatientNameVisible() {
        String strName = BaseVolleyRequest.getLogin().getUser().getFullName();
        String strPhone = BaseVolleyRequest.getLogin().getUser().getCellPhone();
        if (strName != null) {//姓名只显示4个字符
            findPatientNameButton().setMaxEms(4);
            findPatientNameButton().setMaxLines(1);
        }

        findPatientNameButton().setText(strName != null ? strName : strPhone);
        findPatientNameButton().setVisibility(View.VISIBLE);
    }
}