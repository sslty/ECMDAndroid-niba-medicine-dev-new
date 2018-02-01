package com.nibatech.ecmd.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.chat.guide.ChatGuideEditActivity;
import com.nibatech.ecmd.activity.chat.guide.ChatGuideViewActivity;
import com.nibatech.ecmd.activity.chat.supply.ChatPatientSupplyEditActivity;
import com.nibatech.ecmd.activity.chat.supply.ChatSupplyViewActivity;
import com.nibatech.ecmd.adapter.CaseButtonAdapter;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.utils.UIUtils;

import java.util.ArrayList;

public class CaseButtonGridView extends RelativeLayout {

    private ArrayList<Button> caseButtonList = new ArrayList<>();
    private CaseButtonAdapter adapter;
    private Button btnIllness;
    private Activity activity;

    public CaseButtonGridView(Context context) {
        super(context);
        activity = (Activity) context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_case_button, this);
        init(view);
    }

    protected void init(View view) {
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        btnIllness = (Button) view.findViewById(R.id.btn_illness);
        adapter = new CaseButtonAdapter(caseButtonList);
        gv_apps.setAdapter(adapter);
    }

    public void setBtnIllnessTextAndClickListener(String text, OnClickListener listener) {
        btnIllness.setText(text);
        btnIllness.setOnClickListener(listener);
    }

    public void addButton(Button button) {
        caseButtonList.add(button);
        adapter.notifyDataSetChanged();
    }

    public void removeAllButton() {
        caseButtonList.clear();
        adapter.notifyDataSetChanged();
    }

    //添加诊疗阶段的按钮
    protected Button getCaseButton(String name, boolean highLight, View.OnClickListener listener) {
        Button button = new Button(UIUtils.getContext());
        button.setText(name);
        button.setTextColor(Color.BLACK);
        button.setGravity(Gravity.CENTER);
        if (highLight) {
            button.setBackgroundResource(R.drawable.shape_button_circle_nomal);
        } else {
            button.setBackgroundResource(R.drawable.shape_oval_stroke);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        button.setPadding(0, 0, 0, 0);
        button.setLayoutParams(params);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        button.setOnClickListener(listener);
        return button;
    }

    //新建诊疗阶段的按钮
    public void createButton(String name, boolean highLight, final String selfUrl, final String imageUrl,
                             final int mode) {
        addButton(getCaseButton(name, highLight, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Class cls;
                switch (mode) {
                    case CustomSendView.MODE_VIEW_GUIDE:
                        cls = ChatGuideViewActivity.class;
                        break;
                    case CustomSendView.MODE_VIEW_SUPPLY:
                        cls = ChatSupplyViewActivity.class;
                        break;
                    case CustomSendView.MODE_EDIT_GUIDE:
                        cls = ChatGuideEditActivity.class;
                        break;
                    case CustomSendView.MODE_EDIT_SUPPLY:
                        cls = ChatPatientSupplyEditActivity.class;
                        break;
                    default:
                        cls = ChatPatientSupplyEditActivity.class;
                        break;
                }
                intent.putExtra(ExtraPass.EXTRA_CHAT_SELF_URL, selfUrl);
                intent.putExtra(ExtraPass.EXTRA_CHAT_IMAGE_URL, imageUrl);
                intent.setClass(activity, cls);
                activity.startActivityForResult(intent, mode);
            }
        }));

    }


}
