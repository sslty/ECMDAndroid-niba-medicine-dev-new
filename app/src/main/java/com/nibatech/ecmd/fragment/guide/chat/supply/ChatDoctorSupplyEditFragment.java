package com.nibatech.ecmd.fragment.guide.chat.supply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.ChatRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 医生端   guide-聊天-补充资料
 */
public class ChatDoctorSupplyEditFragment extends Fragment implements View.OnClickListener {
    private ListView lvIllState;
    private ArrayList<String> list;
    private EditText etQuestion;
    private Button btnAddQues;
    private MyAdapter mAdapter;
    private Button btnSendPatient;
    private static final int SELECT_BY_SUPPLY = 0;
    private String mStrUrl;
    private Switch switchToggle;
    private EditText etNeedPictureMaterial;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_doctor_supply_edit, container, false);

        getIntentData();
        getAllController(view);
        setControllerListener();
        setControllerData();

        return view;
    }

    private void getIntentData() {
        mStrUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_URL);
    }

    private void getAllController(View view) {
        lvIllState = (ListView) view.findViewById(R.id.lv_ill_state);
        etQuestion = (EditText) view.findViewById(R.id.et_question);
        btnAddQues = (Button) view.findViewById(R.id.btn_addques);
        btnSendPatient = (Button) view.findViewById(R.id.btn_send_patient);
        switchToggle = (Switch) view.findViewById(R.id.switch_toggle);
        etNeedPictureMaterial = (EditText) view.findViewById(R.id.et_need_picture_material);
    }

    private void setControllerListener() {
        btnSendPatient.setOnClickListener(this);
        btnAddQues.setOnClickListener(this);

        switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etNeedPictureMaterial.setVisibility(View.VISIBLE);
                } else {
                    etNeedPictureMaterial.setVisibility(View.GONE);
                }
            }
        });

        etQuestion.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == keyEvent.KEYCODE_ENTER) {
                    addQuestionToList();
                    return true;
                }
                return false;
            }
        });
    }

    private void setControllerData() {
        list = new ArrayList<>();
        mAdapter = new MyAdapter(list);
        lvIllState.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_patient:
                if (attemptSend()) {
                    sendPatient();
                }
                break;
            case R.id.btn_addques:
                addQuestionToList();
                break;
        }
    }

    private boolean attemptSend() {
        boolean ok = false;
        if (list.size() == 0) {
            Toast.makeText(getActivity(), "请输入至少一个问题", Toast.LENGTH_SHORT).show();
        } else {
            ok = true;
        }

        return ok;
    }


    private void addQuestionToList() {
        String question = etQuestion.getText().toString().trim();
        etQuestion.getText().clear();
        if (!TextUtils.isEmpty(question)) {
            list.add(question);
            mAdapter.notifyDataSetChanged();
        }
    }

    private JSONArray getQuestionsFromList() {
        return new JSONArray(list);
    }

    //发给患者补充资料
    private void sendPatient() {
        ChatRequest.sendMaterialToPatient(mStrUrl, getQuestionsFromList(),
                etNeedPictureMaterial.getText().toString(),
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        sendSuccess(success.toString());
                    }
                });
    }

    private void sendSuccess(String success) {
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra(ExtraPass.EXTRA_CHAT_SUPPLY_MATERIAL_SUCCESS, success);
        //设置返回数据
        getActivity().setResult(SELECT_BY_SUPPLY, intent);
        //关闭Activity
        getActivity().finish();
    }


    class MyAdapter extends BaseAdapter {
        ArrayList<String> list;

        public MyAdapter(ArrayList<String> arrayList) {
            this.list = arrayList;
        }

        @Override
        public int getCount() {
            return ChatDoctorSupplyEditFragment.this.list.size();
        }

        @Override
        public Object getItem(int i) {
            return this.list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View viewItem = View.inflate(getActivity(), R.layout.list_question, null);
            TextView textView = (TextView) viewItem.findViewById(R.id.tv_question);
            TextView tvDelete = (TextView) viewItem.findViewById(R.id.tv_delete_self);
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(i);
                    mAdapter.notifyDataSetChanged();
                }
            });
            textView.setText(this.list.get(i));

            return viewItem;
        }
    }
}
