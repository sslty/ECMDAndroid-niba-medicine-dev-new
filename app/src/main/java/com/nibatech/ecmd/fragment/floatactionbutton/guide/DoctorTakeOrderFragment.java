package com.nibatech.ecmd.fragment.floatactionbutton.guide;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.GuideRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;


/**
 * 医生端   guide-我要接单
 */
public class DoctorTakeOrderFragment extends BaseFragment implements View.OnClickListener {
    private EditText mEditSuggestion, mEditExpect, mEditPrice;
    private CheckBox mCheckBox;
    private Button btnOrder, btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_take_order, container, false);

        //得到控件对象
        initView(view);
        //设置控件监听事件
        setViewListener();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_order:
                if (attemptAccept()) {
                    requestTakeOrder();
                }
                break;
            case R.id.btn_back:
                getActivity().finish();
                break;
        }
    }

    private void initView(View view) {
        //您对患者病情的看法
        mEditSuggestion = (EditText) view.findViewById(R.id.id_edit_suggestion);
        //您对效果对预期
        mEditExpect = (EditText) view.findViewById(R.id.id_edit_expectation);
        //诊费
        mEditPrice = (EditText) view.findViewById(R.id.id_edit_price);
        //首次免费
        mCheckBox = (CheckBox) view.findViewById(R.id.id_checkbox);
        //返回
        btnBack = (Button) view.findViewById(R.id.btn_back);
        //接单
        btnOrder = (Button) view.findViewById(R.id.btn_order);
    }

    private void setViewListener() {
        btnBack.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        UIUtils.setInputMaxLengthListener(getActivity(), mEditPrice,
                "诊费应该在0-9999元之间", 4);
    }

    private boolean attemptAccept() {
        boolean ok = false;

        if (mEditSuggestion.getText().toString().isEmpty()) {
            mEditSuggestion.setError(getString(R.string.error_field_required));
        } else if (mEditExpect.getText().toString().isEmpty()) {
            mEditExpect.setError(getString(R.string.error_field_required));
        } else if (mEditPrice.getText().toString().isEmpty()) {
            mEditPrice.setError(getString(R.string.error_field_required));
        } else {
            ok = true;
        }

        return ok;
    }

    private void requestTakeOrder() {
        GuideRequest.postAcceptPatientOrder(getIntentSelfUrl(),
                mEditSuggestion.getText().toString(),
                mEditExpect.getText().toString(), Integer.parseInt(mEditPrice.getText().toString()),
                mCheckBox.isChecked(), new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "您好，您的诊断信息已提交成功，数据专员已开始处理", Toast.LENGTH_SHORT).show();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                    }
                });
    }
}
