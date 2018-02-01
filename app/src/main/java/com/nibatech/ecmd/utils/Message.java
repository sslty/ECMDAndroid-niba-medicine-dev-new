package com.nibatech.ecmd.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lixing on 16/8/17.
 */

public class Message extends BroadcastReceiver {
    /**
     * 调用系统发短信界面 不需要用户自己输入接收方的电话号码
     *
     * @param context     Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessageByIntent(Context context, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }

    /**
     * 调用系统发短信界面,需要用户自己输入接收方的电话号码
     * <p>
     * 示例：SMSUtil.sendMessageByIntent(MainActivity.this,"你好");
     *
     * @param context
     * @param message
     */
    public static void sendMessageByIntent(Context context, String message) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body", message);
        intent.setType("vnd.android-dir/mms-sms");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * 直接调用短信接口发短信
     *
     * @param ActivityOrSetvice Activity
     * @param phoneNumber
     * @param smsContent
     */
    public static void sendMessageBySysterm(Context ActivityOrSetvice, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(smsContent);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

    private String address = null; //需要监听的号码
    private TextView text;

    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle bundle = intent.getExtras();
        SmsMessage[] smsMessages;
        Object[] objects = null;
        if (bundle != null) {
            objects = (Object[]) bundle.get("objects");
        }
        if (objects != null) {
            smsMessages = new SmsMessage[objects.length];
            String sender;
            String content;


            for (int i = 0; i < objects.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
                sender = smsMessages[i].getOriginatingAddress(); // 获取短信的发送者
                content = smsMessages[i].getMessageBody(); // 获取短信的内容

                if (sender.equals(address)) {          //如果收到信息的号码和指定的号码相同
                    text.setText(content);                 //返回信息内容
                    break;
                }
            }
        }
    }


}
