package com.nibatech.ecmd.common.follow;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.common.bean.personal.RelationsBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;

import org.json.JSONObject;


public class FollowingButton {
    private static final String FOLLOWING = "关注";
    private static final String FOLLOWING_CANCEL = "取消关注";
    private Activity activity;
    private TextView button;
    private RelationsBean relationsBean;
    private String entrance;
    private FollowingCallBack followingCallBack;
    private boolean status;//true-已经关注 false-没有关注

    public FollowingButton(Activity activity, TextView button, RelationsBean relationsBean, String entrance,
                           FollowingCallBack followingCallBack) {
        this.button = button;
        this.relationsBean = relationsBean;
        this.activity = activity;
        this.entrance = entrance;
        this.followingCallBack = followingCallBack;

        initButton();
    }

    private void initButton() {
        //可以关注
        if (relationsBean != null) {
            //显示button
            button.setVisibility(View.VISIBLE);
            //关注或者取消关注
            button.setText(getButtonText(relationsBean.isIsFollowing()));
            //关注状态
            status = relationsBean.isIsFollowing();
            //监听事件
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (status) {
                        confirmCancelFollow();
                    } else {
                        clickButton();
                    }
                }
            });
        }
    }

    private void confirmCancelFollow() {
        AlertDialogBuilder.onCreate(activity, "提示", "您是否需要取消关注？", true,
                new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        clickButton();
                    }
                });
    }

    private void clickButton() {
        CommonRequest.postUrlData(getButtonUrl(status), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                //提示消息
                showMessage(status);
                //状态改变
                status = !status;
                //button上的字体改变
                button.setText(getButtonText(status));
                //广播通知医生联系人， 我的关注列表刷新数据
                sendBroadRefreshList();
                //button被改变
                followingCallBack.onClickButton();
            }
        });
    }

    //button上的文字
    private String getButtonText(boolean bl) {
        return bl ? FOLLOWING_CANCEL : FOLLOWING;
    }

    //button的url
    private String getButtonUrl(boolean bl) {
        return bl ? relationsBean.getUnfollowUrl() : relationsBean.getFollowUrl();
    }

    //toast message
    private void showMessage(boolean bl) {
        Toast.makeText(activity, String.format("您已%s!", getButtonText(bl)), Toast.LENGTH_SHORT).show();
    }

    private void sendBroadRefreshList() {
        if (entrance != null) {
            switch (entrance) {
                case BroadCast.NOT_REFRESH_FRIENDS_CHAT:
                case BroadCast.NOT_REFRESH_DOCTORS_CHAT:
                    break;
                default:
                    BroadCast.send(activity, entrance);
                    break;
            }
            BroadCast.send(activity, BroadCast.REFRESH_DOCTOR_PERSONAL);
        }
    }
}
