package com.nibatech.ecmd.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.adapter.MsgAdapter;
import com.nibatech.ecmd.common.bean.common.MessageInformation;
import com.nibatech.ecmd.utils.ChatUtils;

import java.util.ArrayList;

import sj.keyboard.adpater.PageSetAdapter;
import sj.keyboard.data.PageSetEntity;
import sj.keyboard.widget.EmoticonsEditText;
import sj.keyboard.widget.FuncLayout;


public class ChatView extends LinearLayout implements ChatUtils.ChatUtilsListener, FuncLayout.OnFuncKeyBoardListener {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    private RelativeLayout rlChat;
    private ListView lvMsg;
    private CustomEmoticonsKeyBoard ekBar;
    private ChatUtils chatUtils;
    private boolean customChat;
    private final Activity activity;
    private PageSetAdapter pageSetAdapter;
    private ChatViewListener listener;
    private MsgAdapter msgAdapter;
    private View chatSendView;
    private PageSetEntity chatPageEntity;

    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_chat, this);
        initView();
    }

    private void initView() {
        //聊天限时界面
        rlChat = (RelativeLayout) findViewById(R.id.rl_chat);
        //消息
        lvMsg = (ListView) findViewById(R.id.lv_msg);
        ekBar = (CustomEmoticonsKeyBoard) findViewById(R.id.ek_bar);

        ArrayList<MessageInformation> mArray = new ArrayList<>();
        msgAdapter = new MsgAdapter(activity, mArray);
        lvMsg.setAdapter(msgAdapter);

        chatUtils = new ChatUtils(this);
        chatUtils.initEmoticonsEditText(ekBar.getEtChat());
        ekBar.addOnFuncKeyBoardListener(this);

        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSendBtnClick(ekBar.getEtChat().getText().toString());
                }
                ekBar.getEtChat().setText("");
            }
        });
        ekBar.getEmoticonsToolBarView().addFixedToolItemView(false, R.mipmap.icon_add, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAddClicked();
                }
            }
        });
        ekBar.getEmoticonsToolBarView().addFixedToolItemView(true, R.mipmap.icon_setting, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSettingClicked();
                }
            }
        });

    }

    public void setChatViewListener(ChatViewListener listener) {
        this.listener = listener;
    }

    public void setCustomChat(boolean customChat) {
        this.customChat = customChat;
        if (customChat && listener != null) {
            chatSendView = listener.getChatSendView();
            chatPageEntity = listener.getCustomPageSetEntity();
        }
        if (!checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            showPermissionDialog(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            setEmotionAdapter(customChat);
        }

    }

    public ListView getListView() {
        return lvMsg;
    }

    public MsgAdapter getListAdapter() {
        return msgAdapter;
    }

    public CustomEmoticonsKeyBoard getEkBar(){
        return ekBar;
    }

    public boolean isCustom() {
        return customChat;
    }

    public void setChatShow() {
        rlChat.setVisibility(View.VISIBLE);
    }


    private static boolean checkPermission(final Activity activity, final String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int storagePermission = ActivityCompat.checkSelfPermission(activity, permission);
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private static void showPermissionDialog(final Activity activity, String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        ActivityCompat.requestPermissions(activity, new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
    }


    public void setEmotionAdapter(boolean customChat) {
        pageSetAdapter = chatUtils.getPageSetAdapter(activity, chatUtils.getCommonEmoticonClickListener(ekBar.getEtChat()));
        chatUtils.addFilesEmotions(activity, chatUtils.getCommonEmoticonClickListener(ekBar.getEtChat()));
        if (customChat && listener != null && chatSendView != null && chatPageEntity != null) {
            ekBar.addFuncView(chatSendView);
            ekBar.setViewHeight(listener.getCustomSendViewHeight());
            pageSetAdapter.add(chatPageEntity);
        }
        ekBar.setAdapter(pageSetAdapter);
    }

    private void scrollToBottom() {
        lvMsg.requestLayout();
        lvMsg.post(new Runnable() {
            @Override
            public void run() {
                lvMsg.setSelection(lvMsg.getBottom());
            }
        });
    }

    public void reset() {
        ekBar.reset();
    }

    public interface ChatViewListener {
        View getChatSendView();

        PageSetEntity getCustomPageSetEntity();

        int getCustomSendViewHeight();

        void onAddClicked();

        void onSettingClicked();

        void onSendBtnClick(String text);

        void onSendEmotionsImage(String emotionUri);
    }


    @Override
    public void onSendImageEmotion(String imageUri) {
        if (listener != null) {
            listener.onSendEmotionsImage(imageUri);
        }
    }

    @Override
    public void OnFuncPop(int height) {
        scrollToBottom();
    }

    @Override
    public void OnFuncClose() {

    }

}
