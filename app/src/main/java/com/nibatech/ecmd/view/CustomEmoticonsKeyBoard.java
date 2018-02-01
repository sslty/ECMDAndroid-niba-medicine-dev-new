package com.nibatech.ecmd.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nibatech.ecmd.R;

import sj.keyboard.XhsEmoticonsKeyBoard;
import sj.keyboard.utils.EmoticonsKeyboardUtils;

public class CustomEmoticonsKeyBoard extends XhsEmoticonsKeyBoard {

    private int apps_height = 0;
    private boolean isFaceClicked = false;
    private boolean isAppsClicked = false;

    public CustomEmoticonsKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void inflateKeyboardBar() {
        mInflater.inflate(R.layout.view_keyboard_userdef, this);
    }

    @Override
    protected View inflateFunc() {
        return mInflater.inflate(R.layout.view_func_emoticon_userdef, null);
    }

    @Override
    public void reset() {
        EmoticonsKeyboardUtils.closeSoftKeyboard(getContext());
        mLyKvml.hideAllFuncView();
        mBtnFace.setImageResource(R.mipmap.chatting_emoticons);
    }

    @Override
    public void onFuncChange(int key) {
        if (FUNC_TYPE_EMOTION == key) {
            mBtnFace.setImageResource(R.mipmap.chatting_softkeyboard);
        } else {
            mBtnFace.setImageResource(R.mipmap.chatting_emoticons);
            isFaceClicked = false;
            if (!(FUNC_TYPE_APPPS == key)) {
                isAppsClicked = false;
            }
        }
        checkVoice();
    }

    @Override
    public void OnSoftClose() {
        super.OnSoftClose();
        if (mLyKvml.getCurrentFuncKey() == FUNC_TYPE_APPPS && isAppsClicked) {
            setFuncViewHeight(EmoticonsKeyboardUtils.dip2px(getContext(), apps_height));
        } else if (mLyKvml.getCurrentFuncKey() == FUNC_TYPE_EMOTION && isFaceClicked) {
        } else {
            setFuncViewHeight(0);
        }
    }

    @Override
    protected void showText() {
        mEtChat.setVisibility(VISIBLE);
        mBtnFace.setVisibility(VISIBLE);
        mBtnVoice.setVisibility(GONE);
    }

    @Override
    protected void showVoice() {
        mEtChat.setVisibility(GONE);
        mBtnFace.setVisibility(GONE);
        mBtnVoice.setVisibility(VISIBLE);
        reset();
    }

    @Override
    protected void checkVoice() {
        if (mBtnVoice.isShown()) {
            mBtnVoiceOrText.setImageResource(R.mipmap.chatting_softkeyboard);
        } else {
            mBtnVoiceOrText.setImageResource(R.mipmap.chatting_vodie);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == com.keyboard.view.R.id.btn_voice_or_text) {
            if (mEtChat.isShown()) {
                mBtnVoiceOrText.setImageResource(R.mipmap.chatting_softkeyboard);
                showVoice();
            } else {
                showText();
                mBtnVoiceOrText.setImageResource(R.mipmap.chatting_vodie);
                EmoticonsKeyboardUtils.openSoftKeyboard(mEtChat);
            }
        } else if (i == com.keyboard.view.R.id.btn_face) {
            toggleFuncView(FUNC_TYPE_EMOTION);
            isFaceClicked = true;
        } else if (i == com.keyboard.view.R.id.btn_multimedia) {
            toggleFuncView(FUNC_TYPE_APPPS);
            setFuncViewHeight(EmoticonsKeyboardUtils.dip2px(getContext(), apps_height));
            isAppsClicked = true;
        }
    }

    protected void initEditView() {
        this.mEtChat.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if(!CustomEmoticonsKeyBoard.this.mEtChat.isFocused()) {
                    CustomEmoticonsKeyBoard.this.mEtChat.setFocusable(true);
                    CustomEmoticonsKeyBoard.this.mEtChat.setFocusableInTouchMode(true);
                }

                return false;
            }
        });
        this.mEtChat.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s)) {
                    CustomEmoticonsKeyBoard.this.mBtnSend.setVisibility(VISIBLE);
                    CustomEmoticonsKeyBoard.this.mBtnMultimedia.setVisibility(GONE);
                    CustomEmoticonsKeyBoard.this.mBtnSend.setBackgroundResource(R.drawable.shape_button_circle_nomal);
                } else {
                    CustomEmoticonsKeyBoard.this.mBtnMultimedia.setVisibility(VISIBLE);
                    CustomEmoticonsKeyBoard.this.mBtnSend.setVisibility(GONE);
                }

            }
        });
    }


    public void setViewHeight(int height) {
        apps_height = height;
    }
}
