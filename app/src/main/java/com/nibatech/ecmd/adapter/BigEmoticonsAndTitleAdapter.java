package com.nibatech.ecmd.adapter;

import android.content.Context;
import android.view.View;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.utils.Constants;
import com.nibatech.ecmd.utils.ImageLoadUtils;
import com.nibatech.ecmd.utils.UIUtils;

import java.io.IOException;

import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.data.EmoticonPageEntity;
import sj.keyboard.interfaces.EmoticonClickListener;

public class BigEmoticonsAndTitleAdapter extends BigEmoticonsAdapter {

    protected final double DEF_HEIGHTMAXTATIO = 1.6;

    public BigEmoticonsAndTitleAdapter(Context context, EmoticonPageEntity emoticonPageEntity, EmoticonClickListener onEmoticonClickListener) {
        super(context, emoticonPageEntity, onEmoticonClickListener);
        this.mItemHeight = UIUtils.dip2px(60);
        this.mItemHeightMaxRatio = DEF_HEIGHTMAXTATIO;
    }

    protected void bindView(int position, ViewHolder viewHolder) {
        final boolean isDelBtn = isDelBtn(position);
        final EmoticonEntity emoticonEntity = mData.get(position);
        if (isDelBtn) {
            viewHolder.iv_emoticon.setImageResource(R.mipmap.icon_del);
            viewHolder.iv_emoticon.setBackgroundResource(com.keyboard.view.R.drawable.bg_emoticon);
        } else {
            if (emoticonEntity != null) {
                try {
                    ImageLoadUtils.getInstance(viewHolder.iv_emoticon.getContext()).displayImage(emoticonEntity.getIconUri(), viewHolder.iv_emoticon);
                    viewHolder.tv_content.setVisibility(View.VISIBLE);
                    viewHolder.tv_content.setText(emoticonEntity.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewHolder.iv_emoticon.setBackgroundResource(com.keyboard.view.R.drawable.bg_emoticon);
            }
        }

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEmoticonClickListener != null) {
                    mOnEmoticonClickListener.onEmoticonClick(emoticonEntity, Constants.EMOTICON_CLICK_BIGIMAGE, isDelBtn);
                }
            }
        });
    }
}