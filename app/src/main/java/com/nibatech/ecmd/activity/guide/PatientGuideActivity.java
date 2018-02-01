package com.nibatech.ecmd.activity.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.activity.floatactionbutton.guide.PatientSubmitOrderActivity;
import com.nibatech.ecmd.activity.register.profile.UpdatePatientActivity;
import com.nibatech.ecmd.common.bean.guide.GuidePatientTreatingStatusBean;
import com.nibatech.ecmd.common.bean.guide.GuidePatientUrlBean;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.fragment.floatactionbutton.guide.PatientSubmitOrderFragment;
import com.nibatech.ecmd.fragment.guide.PatientOngoingFragment;
import com.nibatech.ecmd.fragment.guide.PatientOrderFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 患者端   guide-挂单区／进行中
 */
public class PatientGuideActivity extends BaseActivity {
    public static final String TREATING_STATE_ORDER = "unaccepted";//已挂单
    public static final String TREATING_STATE_ONGOING = "treating";//进行中
    public static final String TREATING_STATE_NULL = "idle";//未挂单

    private String[] mTopTitles = new String[]{
            "挂单区", "进行中"
    };
    private PatientOrderFragment patientOrderFragment;
    private GuidePatientUrlBean guidePatientUrlBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("中医指导");

        getHostUrlData();
    }

    protected void getHostUrlDataSuccess(String json) {
        guidePatientUrlBean = UIUtils.fromJson(json, GuidePatientUrlBean.class);
        if (guidePatientUrlBean != null) {
            CommonRequest.getUrlData(guidePatientUrlBean.getTreatingStatus(), new VolleyCallback() {
                @Override
                public void onSuccessResponse(JSONObject success) {
                    GuidePatientTreatingStatusBean guidePatientTreatingStatusBean =
                            UIUtils.fromJson(success.toString(),
                                    GuidePatientTreatingStatusBean.class);
                    if (guidePatientTreatingStatusBean != null) {
                        setTabFocusedOnView(guidePatientTreatingStatusBean.getStatus());
                    }
                }
            });
        }
    }

    private void setTabFocusedOnView(String status) {
        patientOrderFragment = new PatientOrderFragment();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(patientOrderFragment);
        fragments.add(new PatientOngoingFragment());

        //self-urls
        List<String> selfUrls = new ArrayList<>();
        selfUrls.add(guidePatientUrlBean.getMyUnacceptedUrl());//挂单区我的
        selfUrls.add(guidePatientUrlBean.getMyTreatingUrl());//进行中我的
        //details-urls
        List<String> detailsUrls = new ArrayList<>();
        detailsUrls.add(guidePatientUrlBean.getOthersUnacceptedUrl());//挂单区其他人
        detailsUrls.add(guidePatientUrlBean.getOthersTreatingUrl());//进行中其他人
        //view-page
        addPageFragmentBindData(fragments, mTopTitles, selfUrls, detailsUrls);
        //focus-tab
        setCurrentTopTab(getTreatStatusSetFocusTab(status));
        //floating-action-button
        setFABClickGreySelection();
    }

    private int getTreatStatusSetFocusTab(String status) {
        int position = 0;

        //只有治疗中的病人，进入中医指导会直接到进行中界面
        if (status.equals(TREATING_STATE_ONGOING)) {
            position = 1;
        }

        return position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {//挂单成功后隐藏float-action-button
            if (requestCode == PatientSubmitOrderFragment.ACTIVITY_RESULT_SUBMIT_ORDER) {
                patientOrderFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    //显示floating-action-button，并设置监听事件
    protected void setFABClickGreySelection() {
        setFloatingButtonVisible(true);
        setFloatingButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guidePatientUrlBean != null) {
                    CommonRequest.getUrlData(guidePatientUrlBean.getTreatingStatus(), new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(JSONObject success) {
                            GuidePatientTreatingStatusBean guidePatientTreatingStatusBean = UIUtils.fromJson(success.toString(),
                                    GuidePatientTreatingStatusBean.class);
                            if (guidePatientTreatingStatusBean != null) {
                                setOrderStatus(guidePatientTreatingStatusBean.getStatus());
                            }
                        }
                    });
                }
            }
        });
    }

    private void setOrderStatus(String status) {
        switch (status) {
            case TREATING_STATE_NULL:
                if (BaseVolleyRequest.getLogin().getUser().getCdNumber() == null) {
                    Toast.makeText(this, "您需要进一步完善资料后才能挂单!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), UpdatePatientActivity.class));
                } else {
                    startActivityBindDataForResult(PatientSubmitOrderActivity.class,
                            guidePatientUrlBean.getCreateOltUrl(),
                            PatientSubmitOrderFragment.ACTIVITY_RESULT_SUBMIT_ORDER);
                }
                break;
            case TREATING_STATE_ORDER:
                Toast.makeText(getApplicationContext(), "您有一个正在挂单区的中医指导还未开始，请完成以后才能再次挂单。",
                        Toast.LENGTH_SHORT).show();
                break;
            case TREATING_STATE_ONGOING:
                Toast.makeText(getApplicationContext(), "您有一个正在进行的中医指导尚未结束，请完成以后才能再次挂单。",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
