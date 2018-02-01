package com.nibatech.ecmd.fragment.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.common.request.RegisterRequest;
import com.nibatech.ecmd.config.ExtraPass;

/**
 * 医生端/患者端   注册-注册协议
 */
public class ProtocolFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protocol, container, false);

        TextView textView = (TextView) view.findViewById(R.id.id_txt_protocol);
        String id = getActivity().getIntent().getStringExtra(ExtraPass.ID);

        if (id.compareTo(RegisterRequest.JSON_VALUE_DOCTOR) == 0) {
            textView.setText(getString(R.string.doctor_protocol));
        } else {
            textView.setText(getString(R.string.user_protocol));
        }

        return view;
    }


}