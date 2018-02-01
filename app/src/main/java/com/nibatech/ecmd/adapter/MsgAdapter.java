package com.nibatech.ecmd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.common.MessageInformation;
import com.nibatech.ecmd.view.HeadView;

import java.util.ArrayList;


public class MsgAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<MessageInformation> mArray;

    public MsgAdapter(Context context, ArrayList<MessageInformation> arr) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mArray = arr;
    }

    public ArrayList<MessageInformation> getArray() {
        return mArray;
    }

    @Override
    public int getCount() {
        if (mArray == null) {
            return 0;
        } else {
            return mArray.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if (mArray == null) {
            return null;
        } else {
            return mArray.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.msg_item, null);
            viewHolder = new ViewHolder();

            viewHolder.llyMy = (LinearLayout) convertView.findViewById(R.id.lly_my);
            viewHolder.tvTimeMy = (TextView) convertView.findViewById(R.id.tv_time_my);
            viewHolder.tvNameMy = (TextView) convertView.findViewById(R.id.tv_name_my);
            viewHolder.ivHeadMy = (HeadView) convertView.findViewById(R.id.iv_head_my);
            viewHolder.llAgainMy = (LinearLayout) convertView.findViewById(R.id.ll_again_my);
            viewHolder.pbSendingMy = (ProgressBar) convertView.findViewById(R.id.pb_sending_my);
            viewHolder.ivSendErrorMy = (ImageView) convertView.findViewById(R.id.iv_sendError_my);
            viewHolder.llOutgoing = (LinearLayout) convertView.findViewById(R.id.ll_outgoing);

            viewHolder.lly = (LinearLayout) convertView.findViewById(R.id.lly);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.txt_time);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.ivHead = (HeadView) convertView.findViewById(R.id.iv_head);
            viewHolder.llAgain = (LinearLayout) convertView.findViewById(R.id.ll_again);
            viewHolder.llIncoming = (LinearLayout) convertView.findViewById(R.id.ll_incoming);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageInformation msgInfo = (MessageInformation) getItem(position);
        msgInfo.showMessage(viewHolder, mContext);

        return convertView;
    }

    public class ViewHolder {
        /***
         * 显示我的信息的控件;
         */
        public LinearLayout llyMy;
        public TextView tvTimeMy;
        public TextView tvNameMy;
        public HeadView ivHeadMy;
        public LinearLayout llAgainMy;
        public ProgressBar pbSendingMy;
        public ImageView ivSendErrorMy;
        public LinearLayout llOutgoing;


        /***
         * 显示别人的信息的控件
         */
        public LinearLayout lly;
        public TextView tvTime;
        public TextView tvName;
        public HeadView ivHead;
        public LinearLayout llAgain;
        public LinearLayout llIncoming;

    }


}
