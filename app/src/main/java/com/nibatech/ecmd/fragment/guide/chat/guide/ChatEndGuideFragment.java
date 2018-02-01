package com.nibatech.ecmd.fragment.guide.chat.guide;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatEndGuideBean;
import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.broadcast.BroadCast;
import com.nibatech.ecmd.common.dialog.AlertDialogBuilder;
import com.nibatech.ecmd.common.dialog.AlertDialogListener;
import com.nibatech.ecmd.common.request.ChatRequest;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.fragment.base.NormalViewFragment;
import com.nibatech.ecmd.fragment.guide.chat.BaseChatFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.HeadItemView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端   guide-聊天-结束指导意见
 */
public class ChatEndGuideFragment extends BaseFragment implements View.OnClickListener,
        NormalViewFragment.ShowNormalViewDataListener {
    public static final int ACTIVITY_RESULT_END_GUIDE = 1;
    private Button btnBack, btnOk, btnFollowing;
    private List<RadioButton> radioButtons;
    private HeadItemView headItemView;
    private RadioGroup radioGroup;
    private FrameLayout frameLayout;
    private int effectId;
    private Map<String, Integer> map;
    private List<ChatEndGuideBean.Effect> effects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_end_guide, container, false);

        initView(view);
        setViewListener();
        getHostUrlData();

        return view;
    }

    @Override
    public void initView(View view) {
        //整个布局
        frameLayout = (FrameLayout) view.findViewById(R.id.frame_layout);
        //头像
        headItemView = (HeadItemView) view.findViewById(R.id.head_item_view);
        //返回
        btnBack = (Button) view.findViewById(R.id.btn_back);
        //确定
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        //关注
        btnFollowing = (Button) view.findViewById(R.id.btn_following);
        //组合
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioButtons = new ArrayList<>();
        //治愈
        radioButtons.add((RadioButton) view.findViewById(R.id.rb_recovery));
        //显效
        radioButtons.add((RadioButton) view.findViewById(R.id.rb_excellence));
        //有效
        radioButtons.add((RadioButton) view.findViewById(R.id.rb_valid));
        //无效
        radioButtons.add((RadioButton) view.findViewById(R.id.rb_invalid));
    }

    @Override
    public void setViewListener() {
        btnBack.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnFollowing.setOnClickListener(this);
    }

    @Override
    public void getHostUrlData() {
        CommonRequest.getUrlData(getIntentSelfUrl(), new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getHostUrlDataSuccess(success.toString());
            }
        });
    }

    @Override
    public void getHostUrlDataSuccess(String json) {
        ChatEndGuideBean chatEndGuideBean = UIUtils.fromJson(json, ChatEndGuideBean.class);
        effectId = chatEndGuideBean.getEffects().get(1).getId();
        setViewData(chatEndGuideBean);
    }

    @Override
    public void setViewData(Object object) {
        //布局显示
        frameLayout.setVisibility(View.VISIBLE);

        ChatEndGuideBean chatEndGuideBean = (ChatEndGuideBean) object;
        //头像
        DoctorProfileBean doctorProfileBean = chatEndGuideBean.getDoctor();
        headItemView.setHeadViewImageAndGender(doctorProfileBean.getAvatarUrl(),
                doctorProfileBean.getGender(), doctorProfileBean.getFullName());
        //疗效名称和id
        map = new HashMap<>();
        effects = chatEndGuideBean.getEffects();
        for (int i = 0; i < effects.size(); i++) {
            String name = effects.get(i).getName();
            Integer id = effects.get(i).getId();
            map.put(name, id);
            radioButtons.get(i).setText(name);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back://返回
                getActivity().finish();
                break;
            case R.id.btn_ok://确定
                if (attemptOk()) {
                    showAlertDialogConfirm();
                }
                break;
            case R.id.btn_following://暂时没有接口
                break;
        }
    }

    private boolean attemptOk() {
        boolean ok = false;

        if (effects != null) {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getActivity(), "请您对本次康复指导效果做出选择，谢谢!", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < effects.size(); i++) {
                    if (radioGroup.getCheckedRadioButtonId() == radioButtons.get(i).getId()) {
                        String name = (String) radioButtons.get(i).getText();
                        effectId = map.get(name);
                        break;
                    }
                }
                ok = true;
            }
        }

        return ok;
    }

    private void requestPostJudgeEndGuide() {
        ChatRequest.putJudgeEndGuide(getActivity(), getIntentSelfUrl(), effectId, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                putJudgeEndGuide();
            }
        });
    }

    private void putJudgeEndGuide() {
        //广播通知患者进行中的订单，医生端的中医指导需要重新刷新
        BroadCast.send(getActivity(), BroadCast.REFRESH_GUIDE_PATIENT_ONGOING);
        BroadCast.send(getActivity(), BroadCast.REFRESH_MY_PATIENT_GUIDE_LIST);
        //评价成功
        Toast.makeText(getActivity(), "评价成功", Toast.LENGTH_SHORT).show();
        getActivity().setResult(BaseChatFragment.SELECT_BY_GUIDE);
        getActivity().finish();
    }

    private void showAlertDialogConfirm() {
        AlertDialogBuilder.onCreate(getActivity(), "提示", "您是否要结束当前的康复指导?",
                true, new AlertDialogListener() {
                    @Override
                    public void selectPositive() {
                        requestPostJudgeEndGuide();
                    }
                });
    }
}
