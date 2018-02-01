package com.nibatech.ecmd.fragment.guide.chat.guide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.bean.chat.ChatGuideBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

/**
 * 医生端／患者端   guide-聊天-查看指导意见
 */
public class ChatGuideViewFragment extends Fragment {
    private String selfUrl;
    private ImageView guideImage;
    private TextView txtGuide, txtTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_guide_view, container, false);
        getIntentData();
        getAllController(view);
        getHttpUrl();
        return view;

    }

    private void getIntentData() {
        selfUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_SELF_URL);
    }

    private void getAllController(View view) {
        guideImage = (ImageView) view.findViewById(R.id.iv_guide_pic);
        //指导意见
        txtGuide = (TextView) view.findViewById(R.id.id_txt_description);
        //复诊时间
        txtTime = (TextView) view.findViewById(R.id.id_txt_time);
    }

    private void getHttpUrl() {
        getUrl();
    }

    private void getUrl() {
        CommonRequest.getUrlData(selfUrl, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    private void getUrlDataSuccess(String success) {
        ChatGuideBean chatGuideBean = new Gson().fromJson(success, ChatGuideBean.class);
        //指导
        txtGuide.setText(chatGuideBean.getGuide());
        //复诊时间
        txtTime.setText(UIUtils.timeISO8601ConvertToNormal(chatGuideBean.getNextGuideTime()));
        //图片
        UIUtils.loadNetworkImage(getActivity(), chatGuideBean.getGuideImageUrl(), guideImage);
    }
}
