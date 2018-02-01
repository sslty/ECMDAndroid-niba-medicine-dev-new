package com.nibatech.ecmd.fragment.guide.chat.details;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatPatientCaseListBean;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.inflate;


/**
 * 医生端/患者端   guide-聊天-查看基本资料
 */
public class ChatCaseDetailsViewFragment extends Fragment {

    private ExpandableListView expandableListView;
    private List<Map<String, Object>> list;
    private MyAdapter myAdapter;
    private ChatPatientCaseListBean chatPatientCaseListBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_case_details_view, container, false);

        LinearLayout llHeadPicture = (LinearLayout) view.findViewById(R.id.ll_head_picture);
        llHeadPicture.setVisibility(View.GONE);

        //获取上一层传过来的数据
        getIntentData();
        //获取控件对象
        getAllController(view);
        //设置控件监听事件
        setControllerListener();

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    //上一层传过来的数据
    private void getIntentData() {
        String url = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_CASE_URL);
        //向服务器请求数据
        getHttpData(url);
    }

    private void getHttpData(String url) {
        CommonRequest.getUrlData(url, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    //成功
    private void getUrlDataSuccess(String json) {
        chatPatientCaseListBean = new Gson().fromJson(json, ChatPatientCaseListBean.class);
        //设置控件数据
        setControllerData();
        //刷新数据
        myAdapter.notifyDataSetChanged();
    }

    //
    private void getAllController(View view) {
        //展开，缩小控件
        expandableListView = (ExpandableListView) view.findViewById(R.id.id_expandable_list_view);
        //初始化数据
        list = new ArrayList<>();
        //适配器
        myAdapter = new MyAdapter();
        expandableListView.setAdapter(myAdapter);
        expandableListView.expandGroup(0);
    }

    private void setControllerListener() {
    }

    private void setControllerData() {
        //年龄, 头像, 性别
        String mStrAvatar, mStrGender;
        Integer mIntAge;
        if (chatPatientCaseListBean.getPatient() != null) {
            mIntAge = chatPatientCaseListBean.getPatient().getAge();
            mStrAvatar = chatPatientCaseListBean.getPatient().getAvatarUrl();
            mStrGender = chatPatientCaseListBean.getPatient().getGender();
        } else {
            mIntAge = null;
            mStrAvatar = null;
            mStrGender = null;
        }

        //更新时间
        String mStrUpdateTime = chatPatientCaseListBean.getUpdatedTime();
        //描述
        String mStrDescription = chatPatientCaseListBean.getDescription();
        //症状和特征
        String mStrSymptom = chatPatientCaseListBean.getSymptom();

        Map<String, Object> map = new HashMap<>();
        map.put(DataKey.KEY_TITLE, "基本信息");
        map.put(DataKey.KEY_AVATAR, mStrAvatar);
        map.put(DataKey.KEY_AGE, mIntAge);
        map.put(DataKey.KEY_GENDER, mStrGender);
        map.put(DataKey.KEY_UPDATE_TIME, UIUtils.timeISO8601ConvertToNormal(mStrUpdateTime));
        map.put(DataKey.KEY_CONTENT, UIUtils.getNotNullString(mStrDescription));
        map.put(DataKey.KEY_SYMPTOM, UIUtils.getNotNullString(mStrSymptom));
        map.put(DataKey.KEY_IMG, chatPatientCaseListBean.getImages());

        list.add(map);
    }

    class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return i;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            View itemView;

            if (expandableListView.isGroupExpanded(i)) {
                itemView = View.inflate(getActivity(), R.layout.item_group_case_detail, null);
            } else {
                itemView = inflate(getActivity(), R.layout.item_group_case_detail_expand, null);
            }

            if (list.size() > 0) {
                TextView mTxtTitle = (TextView) itemView.findViewById(R.id.id_txt_title);
                mTxtTitle.setText((String) list.get(i).get(DataKey.KEY_TITLE));

                TextView mTxtUpdatedTime = (TextView) itemView.findViewById(R.id.id_txt_updated_time);
                mTxtUpdatedTime.setText((String) list.get(i).get(DataKey.KEY_UPDATE_TIME));
            }

            return itemView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.activity_details_list_header, null);

            if (list.size() > 0) {
                TextView mTxtDescription = (TextView) itemView.findViewById(R.id.id_txt_description);
                mTxtDescription.setText((String) list.get(i).get(DataKey.KEY_CONTENT));

                TextView mTxtSymptom = (TextView) itemView.findViewById(R.id.id_txt_symptom);
                mTxtSymptom.setText((String) list.get(i).get(DataKey.KEY_SYMPTOM));

                List<ImageNameBean> images = (List<ImageNameBean>) list.get(i).get(DataKey.KEY_IMG);
                createImageView(itemView, images);
            }

            return itemView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

    public void createImageView(View view, final List<ImageNameBean> images) {
        AutoGridImageView autoGridImageView = (AutoGridImageView) view.findViewById(R.id.auto_image_container);
        autoGridImageView.setVisibility(View.VISIBLE);
        ArrayList<String> imageList = new ArrayList<>();
        for (ImageNameBean imageNameBean : images) {
            String imageUrl = imageNameBean.getImageUrl();
            imageList.add(imageUrl);
        }
        autoGridImageView.addImages(imageList);
    }
}
