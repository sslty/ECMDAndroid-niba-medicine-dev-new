package com.nibatech.ecmd.fragment.guide.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatPatientCaseListBean;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageView;
import com.nibatech.ecmd.view.HeadView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 医生端   guide-挂单区／进行中-病例详情-
 */
public class DoctorCaseDetailsFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private List<Map<String, Object>> list;
    private MyAdapter myAdapter;
    private HeadView headView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_case_details_view, container, false);
        initView(view);
        getHttpData();

        return view;
    }


    private void getHttpData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    //成功
    private void getUrlDataSuccess(String json) {
        ChatPatientCaseListBean chatPatientCaseListBean = UIUtils.fromJson(json, ChatPatientCaseListBean.class);
        if (chatPatientCaseListBean != null) {
            //设置控件数据
            setControllerData(chatPatientCaseListBean);
            //刷新数据
            myAdapter.notifyDataSetChanged();
        }
    }

    //
    private void initView(View view) {
        headView = (HeadView) view.findViewById(R.id.id_image_avatar);
        //展开，缩小控件
        expandableListView = (ExpandableListView) view.findViewById(R.id.id_expandable_list_view);
    }

    private void setControllerData(ChatPatientCaseListBean chatPatientCaseListBean) {
        getListData(chatPatientCaseListBean);

        //适配器
        myAdapter = new MyAdapter();
        expandableListView.setAdapter(myAdapter);
        expandableListView.expandGroup(0);

        headView.setHeadPhotoAndGender((String) list.get(0).get(DataKey.KEY_AVATAR),
                (String) list.get(0).get(DataKey.KEY_GENDER))
                .setSignature((Integer) list.get(0).get(DataKey.KEY_AGE));
    }

    private void getListData(ChatPatientCaseListBean chatPatientCaseListBean) {
        //初始化数据
        list = new ArrayList<>();
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
                itemView = View.inflate(getActivity(), R.layout.item_group_case_detail_expand, null);
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
            View itemView = layoutInflater.inflate(R.layout.activity_details_list_header, viewGroup, false);

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
