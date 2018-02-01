package com.nibatech.ecmd.fragment.homepage;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.guide.PatientGuideActivity;
import com.nibatech.ecmd.activity.mien.MienPatientActivity;
import com.nibatech.ecmd.activity.sea.SeaActivity;


/**
 * 患者端   首页-子类
 */
public class PatientHomePageFragment extends HomePageFragment implements HomePageFragment.ShowHomePageDataListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置监听
        setHomePageListener(this);
    }

    @Override
    public void onShowHomeIcon() {
        mTxtPrize.setText("方海寻珠");
        //方海寻珠图片
        Drawable drawable = getResources().getDrawable(R.drawable.home_sea);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTxtPrize.setCompoundDrawables(null, drawable, null, null);
    }

    @Override
    public void onClickGuide() {
        startActivityBindData(PatientGuideActivity.class, getAPIsUrl(getActivity()).getEntranceOltPatient());
    }

    @Override
    public void onClickMien() {
        startActivity(new Intent(getActivity(), MienPatientActivity.class));
    }

    @Override
    public void onClickPrize() {
        startActivity(new Intent(getActivity(), SeaActivity.class));
    }
}
