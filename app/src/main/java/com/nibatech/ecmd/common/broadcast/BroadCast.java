package com.nibatech.ecmd.common.broadcast;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadCast {
    //医生端 接单成功后，需要刷新病人的中医指导挂单区的列表；患者端，开始诊疗后，需要刷新病人端中医指导挂单区列表
    public static final String REFRESH_GUIDE_PATIENT_ORDER = "refresh.patient.order";//患者端，guide，挂单区
    //患者端 开始诊疗后，需要刷新中医指导进行中的列表
    public static final String REFRESH_GUIDE_PATIENT_ONGOING = "refresh.patient.ongoing";//患者端，guide，进行中
    //医生端 接单成功后，需要刷新中医指导挂单区的列表
    public static final String REFRESH_GUIDE_DOCTOR_ORDER = "refresh.doctor.order";//医生端，guide，挂单区
    //医生端 接单成功后，需要刷新中医指导求高手的列表
    public static final String REFRESH_GUIDE_DOCTOR_DIFFICULT = "refresh.doctor.difficult";//医生端，guide，求高手
    //患者端 结束中医指导后后，需要刷新我的患者，中医指导的列表
    public static final String REFRESH_MY_PATIENT_GUIDE_LIST = "refresh.my.patient.guide";//医生端，我的患者，guide，列表
    //医生端/患者端 关注和取消关注button，需要刷新的界面
    public static final String REFRESH_DOCTOR_PERSONAL = "refresh.doctor.personal";//医生个人首页
    public static final String REFRESH_MY_DOCTOR_FOLLOWED = "refresh.my.doctor.followed";//患者端，我的医生，我关注的医生
    public static final String REFRESH_FRIENDS_CONTACT = "refresh.friends.contact";//医生端，好友，联系人
    public static final String REFRESH_MINE_FOLLOWED_DOCTOR = "refresh.mine.followed.doctor";//医生端，我的，我的关注，医生
    public static final String REFRESH_MINE_FOLLOWER_DOCTOR = "refresh.mine.follower.doctor";//医生端，我的，关注我的医生
    //医生端/患者端 关注和取消关注button，不需要刷新的界面
    public static final String NOT_REFRESH_FRIENDS_CHAT = "not.refresh.friends.chat";//医生端，好友，会话，不用刷新
    public static final String NOT_REFRESH_DOCTORS_CHAT = "not.refresh.doctors.chat";//患者端，我的医生，会话，不用刷新
    //患者端 开始诊疗后，需要销毁病例详情界面
    public static final String DESTROY_GUIDE_DETAILS = "destroy.guide.details";//患者端，病例详情
    //患者端 支付完毕后，需要销毁支付界面
    public static final String DESTROY_GUIDE_PAYMENT = "destroy.guide.payment";//患者端，支付

    private Activity activity;
    private String action;
    private BroadCastCallBack broadCastCallBack;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (action.contains("refresh")) {
                broadCastCallBack.onRefresh(intent.getAction());
            } else if (action.contains("destroy")) {
                broadCastCallBack.onDestroy(intent.getAction());
            }
        }
    };

    /**
     * BroadCast
     * 参数：Activity activity 上下文
     * String action 行为
     * BroadCastCallBack broadCastCallBack 回调函数
     * 功能：
     * 新建广播行为
     */
    public BroadCast(Activity activity, String action, BroadCastCallBack broadCastCallBack) {
        this.activity = activity;
        this.action = action;
        this.broadCastCallBack = broadCastCallBack;
        register();
    }

    /**
     * registerBroadcast
     * 参数：无
     * 功能：
     * 在activity注册广播消息
     */
    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        activity.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * unRegisterBroadcast
     * 参数：无
     * 功能：
     * 在activity注销广播消息
     */
    public void unRegister() {
        activity.unregisterReceiver(broadcastReceiver);
    }

    /**
     * sendBroadcast
     * 参数：
     * Activity activity activity名
     * String action 动作
     * 功能：
     * 往activity发送广播消息
     */
    public static void send(Activity activity, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        activity.sendBroadcast(intent);
    }
}
