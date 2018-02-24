package com.nibatech.ecmd.fragment.mine;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.MainActivity;
import com.nibatech.ecmd.activity.mine.ContactActivity;
import com.nibatech.ecmd.activity.mine.FeedBackActivity;
import com.nibatech.ecmd.activity.mine.HistoryActivity;
import com.nibatech.ecmd.activity.mine.JoinActivity;
import com.nibatech.ecmd.activity.mine.MoneyActivity;
import com.nibatech.ecmd.activity.mine.ProfileActivity;
import com.nibatech.ecmd.activity.mine.SettingActivity;
import com.nibatech.ecmd.activity.mine.VersionActivity;
import com.nibatech.ecmd.activity.mine.follow.FollowedActivity;
import com.nibatech.ecmd.activity.mine.follow.FollowerViewPageActivity;
import com.nibatech.ecmd.activity.photo.PhotoModifyActivity;
import com.nibatech.ecmd.activity.register.profile.UpdateDoctorActivity;
import com.nibatech.ecmd.activity.register.profile.UpdatePatientActivity;
import com.nibatech.ecmd.common.bean.login.LoginBean;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.bean.mine.MineBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.HeadView;
import com.nibatech.ecmd.view.LineControllerView;

import org.json.JSONObject;


/**
 * 医生端   我的
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout linearLayoutMoney;
    private LineControllerView lcvExit, lcvJoin, lcvFollowed, lcvFollower, lcvInformation,
            lcvHistory, lcvFeedBack, lcvContact, lcvSetting, lcvVersion;
    private TextView mTxtName;
    private HeadView imgAvatar;
    private MineBean mineBean;
    private String strSelfUrl;
    private ScrollView scrollView;

    @Override
    protected void onCreateSuccessView(View successView) {
        initView(successView);
        setViewListener();
    }

    @Override
    protected int getSuccessViewLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lcv_exit:
                //用户安全退出
                getActivity().finish();
                UIUtils.logout();
                startActivity(new Intent(getActivity(), MainActivity.class));//跳转到用户注册界面
                break;
            case R.id.lcv_setting://设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.lcv_contact://联系我们
                startActivity(new Intent(getActivity(), ContactActivity.class));
                break;
            case R.id.lcv_feedback://反馈意见
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.lcv_history://历史数据
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                break;
            case R.id.lcv_information://个人资料
                gotoModifyProfile();
                break;
            case R.id.lcv_followed://我的关注
//                startActivityBindData(FollowedActivity.class, mineBean.getFollowed().getSelfUrl());
                startActivityNotBindData(FollowedActivity.class);
                break;
            case R.id.lcv_follower://关注我的
                startActivityBindData(FollowerViewPageActivity.class, mineBean.getFollowers().getSelfUrl());
                break;
            case R.id.lcv_join://我参与
                startActivity(new Intent(getActivity(), JoinActivity.class));
                break;
            case R.id.layout_my_money://财富
                startActivity(new Intent(getActivity(), MoneyActivity.class));
                break;
            case R.id.lcv_version://版本
                startActivity(new Intent(getActivity(), VersionActivity.class));
                break;
            case R.id.image_head://头像
                startActivity(new Intent(getActivity(), PhotoModifyActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getHostUrlData();
    }

    private void getHostUrlData() {
//        CommonRequest.getUrlData(getStrSelfUrl(), new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getHostUrlDataSuccess(success.toString());
//            }
//        });
        getHostUrlDataSuccess("");
    }

    private void gotoModifyProfile() {
//        final UserBean userBean = BaseVolleyRequest.getLogin().getUser();
//
//        if (userBean.getCdNumber() == null) {//如果没有填写任何资料
//            Toast.makeText(getActivity(), "请完善您的个人信息", Toast.LENGTH_SHORT).show();
//            if (userBean.getDoctor() != null) {//直接进入补充医生资料界面
//                startActivityNotBindData(UpdateDoctorActivity.class);
//            } else if (userBean.getPatient() != null) {
//                startActivityNotBindData(UpdatePatientActivity.class);
//            }
//        } else {//进入修改个人界面
//            startActivityBindData(ProfileActivity.class, new Gson().toJson(mineBean));
//        }
        startActivityNotBindData(ProfileActivity.class);
    }

    public void initView(View view) {
        //主界面布局
        scrollView = (ScrollView) view.findViewById(R.id.scroll_view);
        //我的名字
        mTxtName = (TextView) view.findViewById(R.id.id_txt_name);
        //财富
        linearLayoutMoney = (LinearLayout) view.findViewById(R.id.layout_my_money);
        //头像
        imgAvatar = (HeadView) view.findViewById(R.id.image_head);
        //退出登录
        lcvExit = (LineControllerView) view.findViewById(R.id.lcv_exit);
        //设置
        lcvSetting = (LineControllerView) view.findViewById(R.id.lcv_setting);
        //联系我们
        lcvContact = (LineControllerView) view.findViewById(R.id.lcv_contact);
        //反馈意见
        lcvFeedBack = (LineControllerView) view.findViewById(R.id.lcv_feedback);
        //历史数据
        lcvHistory = (LineControllerView) view.findViewById(R.id.lcv_history);
        //个人资料
        lcvInformation = (LineControllerView) view.findViewById(R.id.lcv_information);
        //关注我的
        lcvFollower = (LineControllerView) view.findViewById(R.id.lcv_follower);
        //我的关注
        lcvFollowed = (LineControllerView) view.findViewById(R.id.lcv_followed);
        //我的参加
        lcvJoin = (LineControllerView) view.findViewById(R.id.lcv_join);
        //版本号
        lcvVersion = (LineControllerView) view.findViewById(R.id.lcv_version);
    }

    public void getHostUrlDataSuccess(String json) {
//        mineBean = UIUtils.fromJson(json, MineBean.class);
        setViewData();
    }

    public void setViewData() {
//        //用户信息
//        LoginBean loginInfo = BaseVolleyRequest.getLogin();
//        UserBean userProfile = loginInfo.getUser();
//        //名字
//        String mStrName = userProfile.getFullName();
//        String strSpace = "      ";
//        //名字
//        if (mStrName != null) {
//            mTxtName.setVisibility(View.VISIBLE);
//            mTxtName.setText(mStrName);
//        }
//
//        //头像
//        imgAvatar.setHeadPhotoAndGender(userProfile.getAvatarUrl(), userProfile.getGender());
//
//        if (BaseVolleyRequest.getIdentity() == BaseVolleyRequest.IDENTITY_DOCTOR) {
//            //我的关注
//            String strFollowed = String.valueOf(mineBean.getFollowed().getCount());
//            lcvFollowed.setTitle(String.format("我的关注:%1s%2s", strSpace, strFollowed));
//            lcvFollowed.setVisibility(View.VISIBLE);
//
//            //关注我的
//            String strFollower = String.valueOf(mineBean.getFollowers().getCount());
//            lcvFollower.setTitle(String.format("关注我的:%1s%2s", strSpace, strFollower));
//            lcvFollower.setVisibility(View.VISIBLE);
//        }

        mTxtName.setVisibility(View.VISIBLE);
        mTxtName.setText("name");

        imgAvatar.setHeadPhotoAndGender(null, null);

        String strSpace = "      ";
        String strFollowed = String.valueOf(30);
        lcvFollowed.setTitle(String.format("我的关注:%1s%2s", strSpace, strFollowed));
        lcvFollowed.setVisibility(View.VISIBLE);

        //有数据才显示
        scrollView.setVisibility(View.VISIBLE);
    }

    public void setViewListener() {
        //退出登录
        lcvExit.setOnClickListener(this);
        //设置
        lcvSetting.setOnClickListener(this);
        //联系我们
        lcvContact.setOnClickListener(this);
        //反馈意见
        lcvFeedBack.setOnClickListener(this);
        //历史数据
        lcvHistory.setOnClickListener(this);
        //个人资料
        lcvInformation.setOnClickListener(this);
        //关注我的
        lcvFollower.setOnClickListener(this);
        //我的关注
        lcvFollowed.setOnClickListener(this);
        //我的参加
        lcvJoin.setOnClickListener(this);
        //财富
        linearLayoutMoney.setOnClickListener(this);
        //头像
        imgAvatar.setOnClickListener(this);
        //版本
        lcvVersion.setOnClickListener(this);
    }

    public String getStrSelfUrl() {
        return strSelfUrl;
    }

    public void setStrSelfUrl(String strSelfUrl) {
        this.strSelfUrl = strSelfUrl;

        getHostUrlData();
    }
}
