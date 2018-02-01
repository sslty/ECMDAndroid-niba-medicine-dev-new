package com.nibatech.ecmd.fragment.guide.chat.supply;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.chat.ChatQuestionBean;
import com.nibatech.ecmd.common.request.ChatRequest;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.guide.chat.ChatDoctorCustomFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 患者端   guide-聊天-添加基本资料
 */
public class ChatPatientSupplyEditFragment extends Fragment {

    private AutoGridImageEdit autoImageGridView;
    private ArrayList<String> pathList;
    private String mStrSelfUrl, mStrImageUrl;
    private Button btnSendDoctor;
    private ArrayList<EditText> replyList = new ArrayList<>();
    private ScrollView scrollView;
    private ChatQuestionBean chatQuestionBean;
    private TextView tvDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_patient_supply_edit, container, false);

        getIntentData();
        getHttpStages();
        getAllController(view);
        setControllerData();
        setControllerListener();

        return view;
    }


    private void getIntentData() {
        mStrSelfUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_SELF_URL);
        mStrImageUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_IMAGE_URL);
    }

    private void getAllController(View view) {
        btnSendDoctor = (Button) view.findViewById(R.id.id_btn_send);
        scrollView = (ScrollView) view.findViewById(R.id.lv_question);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);
        //自动添加图片
        autoImageGridView = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container);
    }

    private void setControllerListener() {
        btnSendDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptSend()) {
                    sendToDoctor();
                }
            }
        });
    }

    private boolean attemptSend() {
        boolean ok = false;

        if (pathList.size() == 0) {
            Toast.makeText(getActivity(), "请选择至少一张图片！", Toast.LENGTH_SHORT).show();
        } else {
            ok = true;

            //全部问题必须回答
            for (int i = 0; i < replyList.size(); i++) {
                TextView txtAnswer = replyList.get(i);
                if (txtAnswer.getText().toString().isEmpty()) {
                    txtAnswer.setError(getString(R.string.error_field_required));
                    ok = false;
                    break;
                }
            }
        }

        return ok;
    }

    private void setControllerData() {
        //初始化图片列表和计数器
        pathList = new ArrayList<>();
    }

    private void getHttpStages() {
        CommonRequest.getUrlData(mStrSelfUrl, new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                getUrlDataSuccess(success.toString());
            }
        });
    }

    //提出的问题和回答的问题列表
    private void getUrlDataSuccess(String success) {
        //得到数据
        chatQuestionBean = new Gson().fromJson(success, ChatQuestionBean.class);
        //设置控件
        setControllerData(chatQuestionBean.getQuestionsAnswers());
    }

    private void setControllerData(List<ChatQuestionBean.QuestionsAnswers> questionsAnswers) {
        setQuestionList(getQuestions(questionsAnswers));
        //图片描述
        if (chatQuestionBean.getImageDescription() != null) {
            tvDescription.setText(chatQuestionBean.getImageDescription());
        }
    }

    private void setQuestionList(ArrayList<String> questions) {
        replyList.clear();
        for (int i = 0; i < questions.size(); i++) {
            replyList.add(new EditText(UIUtils.getContext()));
        }

        LinearLayout linearLayoutItem = new LinearLayout(UIUtils.getContext());
        linearLayoutItem.setOrientation(LinearLayout.VERTICAL);
        int padding = UIUtils.dip2px(5);

        for (int i = 0; i < questions.size(); i++) {
            TextView title = new TextView(UIUtils.getContext());
            title.setText("问题: " + questions.get(i));
            title.setTextColor(Color.BLACK);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);// 18sp
            title.setPadding(padding, padding, padding, padding);

            EditText reply = new EditText(UIUtils.getContext());
            reply.setHint("请输入回复");
            reply.setHintTextColor(Color.GRAY);
            reply.setTextColor(Color.BLACK);
            reply.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);// 18sp
            reply.setBackgroundResource(R.drawable.shape_oval_stroke);
            reply.setMinLines(2);
            reply.setPadding(padding, padding, padding, padding);
            linearLayoutItem.addView(title);
            linearLayoutItem.addView(reply);
            replyList.set(i, reply);
        }

        scrollView.addView(linearLayoutItem);
    }

    private ArrayList<String> getQuestions(List<ChatQuestionBean.QuestionsAnswers> questionsAnswers) {
        ArrayList<String> questions = new ArrayList<>();
        for (int i = 0; i < questionsAnswers.size(); i++) {
            questions.add(i, questionsAnswers.get(i).getQuestion());
        }

        return questions;
    }

    private void sendToDoctor() {
        BaseOKHttpRequest.post(getActivity(), mStrImageUrl, pathList, new OKHttpCallback() {
            @Override
            public void onSuccess(String success) {
                requestPostSuccess(success);
            }
        });
    }

    //上传图片成功，再次上传资料信息和图片url
    private void requestPostSuccess(String json) {
        ChatRequest.putSupplyMaterialToDoctor(mStrSelfUrl,
                getArrayAnswer(), UIUtils.getArrayUrl(json), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                        //设置返回数据
                        Intent intent = new Intent();
                        //把返回数据存入Intent
                        intent.putExtra(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_SUCCESS, success.toString());
                        getActivity().setResult(ChatDoctorCustomFragment.SELECT_BY_SUPPLY, intent);
                        getActivity().finish();
                    }
                });
    }

    private JSONArray getArrayAnswer() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < chatQuestionBean.getQuestionsAnswers().size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", chatQuestionBean.getQuestionsAnswers().get(i).getId());
            map.put("answer", replyList.get(i).getText().toString());
            list.add(map);
        }

        return new JSONArray(list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                case PhotoViewActivity.REQUIRE_TYPE_GALLERY://图库
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    autoImageGridView.addImages(paths);
                    pathList.addAll(paths);
                    break;
                case PhotoViewActivity.REQUIRE_TYPE_DELETE_IMAGE://删除照片
                    autoImageGridView.removeImage(data.getStringExtra(PhotoViewActivity.RESULT_PATH));
                    break;
            }
        }
    }
}
