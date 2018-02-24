package com.nibatech.ecmd.activity.guide.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.floatactionbutton.guide.DoctorTakeOrderActivity;
import com.nibatech.ecmd.activity.register.profile.UpdateDoctorActivity;
import com.nibatech.ecmd.common.bean.chat.ChatPatientCaseListBean;
import com.nibatech.ecmd.common.bean.common.ImageNameBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.guide.base.BaseGuideFragment;
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
public class DoctorCaseDetailsActivity extends BaseActivity {
    public static final int ACTIVITY_RESULT_TAKE_ORDER = 3;

    private ExpandableListView expandableListView;
    private List<Map<String, Object>> list;
    private MyAdapter myAdapter;
    private HeadView headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("诊断详情");
        initView(getSelfView(R.layout.fragment_chat_case_details_view));
//        addDefaultFragment(new DoctorCaseDetailsFragment());
//        setFABListener();
//        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion);
//        getWindow().setSharedElementEnterTransition(transition);
//        transition.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//                getHttpData();
//
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                expandableListView.setVisibility(View.VISIBLE);//1.当动画先结束了，提早让expandview显示空数据（其实也是什么也看不见），当网络数据来了再显示逻辑也对，2.当网络请求先结束，那直到动画结束，才显示数据，所以逻辑也对
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });
        setControllerData(null);
        expandableListView.setVisibility(View.VISIBLE);
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
        }
    }

    //
    private void initView(View view) {
        headView = ((HeadView) view.findViewById(R.id.id_image_avatar)).setShowWithoutData();
        //展开，缩小控件
        expandableListView = (ExpandableListView) view.findViewById(R.id.id_expandable_list_view);
    }

    private void setControllerData(ChatPatientCaseListBean chatPatientCaseListBean) {
        getListData(chatPatientCaseListBean);

        headView.setHeadPhotoAndGender((String) list.get(0).get(DataKey.KEY_AVATAR),
                (String) list.get(0).get(DataKey.KEY_GENDER))
//                .setSignature((Integer) list.get(0).get(DataKey.KEY_AGE));
                .setSignature(23);
        //适配器
        myAdapter = new MyAdapter();
        expandableListView.setAdapter(myAdapter);
        expandableListView.expandGroup(0);

    }

    private void getListData(ChatPatientCaseListBean chatPatientCaseListBean) {
        //初始化数据
        list = new ArrayList<>();
        //年龄, 头像, 性别
        String mStrAvatar, mStrGender;
        Integer mIntAge;
//        if (chatPatientCaseListBean.getPatient() != null) {
//            mIntAge = chatPatientCaseListBean.getPatient().getAge();
//            mStrAvatar = chatPatientCaseListBean.getPatient().getAvatarUrl();
//            mStrGender = chatPatientCaseListBean.getPatient().getGender();
//        } else {
//            mIntAge = null;
//            mStrAvatar = null;
//            mStrGender = null;
//        }

//        //更新时间
//        String mStrUpdateTime = chatPatientCaseListBean.getUpdatedTime();
//        //描述
//        String mStrDescription = chatPatientCaseListBean.getDescription();
//        //症状和特征
//        String mStrSymptom = chatPatientCaseListBean.getSymptom();

        Map<String, Object> map = new HashMap<>();
        map.put(DataKey.KEY_TITLE, "基本信息");
        map.put(DataKey.KEY_AVATAR, "");
        map.put(DataKey.KEY_AGE, "20");
        map.put(DataKey.KEY_GENDER, "男");
        map.put(DataKey.KEY_UPDATE_TIME, "2018");
        map.put(DataKey.KEY_CONTENT, "test");
        map.put(DataKey.KEY_SYMPTOM, "symbol");
        map.put(DataKey.KEY_IMG, "");

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
                itemView = View.inflate(DoctorCaseDetailsActivity.this, R.layout.item_group_case_detail, null);
            } else {
                itemView = View.inflate(DoctorCaseDetailsActivity.this, R.layout.item_group_case_detail_expand, null);
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
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.activity_details_list_header, viewGroup, false);

            if (list.size() > 0) {
                TextView mTxtDescription = (TextView) itemView.findViewById(R.id.id_txt_description);
                mTxtDescription.setText((String) list.get(i).get(DataKey.KEY_CONTENT));

                TextView mTxtSymptom = (TextView) itemView.findViewById(R.id.id_txt_symptom);
                mTxtSymptom.setText((String) list.get(i).get(DataKey.KEY_SYMPTOM));

//                List<ImageNameBean> images = (List<ImageNameBean>) list.get(i).get(DataKey.KEY_IMG);
//                createImageView(itemView, images);
                createImageView(itemView);
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

    public void createImageView(View view) {
        AutoGridImageView autoGridImageView = (AutoGridImageView) view.findViewById(R.id.auto_image_container);
        autoGridImageView.setVisibility(View.VISIBLE);
        ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String imageUrl = "http://p0.so.qhimgs1.com/t01a470b7418af6434a.jpg";
            imageList.add(imageUrl);
        }
        autoGridImageView.addImages(imageList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ACTIVITY_RESULT_TAKE_ORDER) {
                //接单成功之后，通知接单区，list需要刷新
                if (getIntentEntrance() == BaseGuideFragment.GUIDE_DOCTOR_ORDER) {
                    BroadCast.send(this, BroadCast.REFRESH_GUIDE_DOCTOR_ORDER);
                } else {
                    BroadCast.send(this, BroadCast.REFRESH_GUIDE_DOCTOR_DIFFICULT);
                }
                setFloatingButtonVisible(false);
            }
        }
    }

    private void setFABListener() {
        setFloatingButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getHostUrlData();
                takeOrder("");
            }
        });

//        String join = getIntent().getStringExtra(ExtraPass.JOIN);
//        if (join == null) {
//            setFloatingButtonVisible(true);
//        }
        setFloatingButtonVisible(true);
    }

    protected void getHostUrlDataSuccess(String json) {
        ChatPatientCaseListBean chatPatientCaseListBean = new Gson().fromJson(json, ChatPatientCaseListBean.class);
        if (BaseVolleyRequest.getLogin().getUser().getCdNumber() == null) {
            updateProfile(chatPatientCaseListBean.getOfferUrl());
        } else {
            takeOrder(chatPatientCaseListBean.getOfferUrl());
        }
    }

    //补充自己的资料
    private void updateProfile(String url) {
        Toast.makeText(this, "您需要进一步完善资料后才能接单!", Toast.LENGTH_SHORT).show();
        startActivityBindData(UpdateDoctorActivity.class, url);
    }

    //医生接单
    public void takeOrder(String url) {
        startActivityBindDataForResult(DoctorTakeOrderActivity.class, url,
                ACTIVITY_RESULT_TAKE_ORDER);
    }
}