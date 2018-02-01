package com.nibatech.ecmd.activity.mine.certify;

import android.content.Intent;
import android.os.Bundle;

import com.nibatech.ecmd.activity.base.BaseActivity;
import com.nibatech.ecmd.fragment.mine.certify.CertifiedFragment;

/**
 * 医生端   我的-个人资料-医师证明-已认证
 */
public class CertifiedActivity extends BaseActivity {

    private CertifiedFragment certifiedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarText("医师证明");
        addDefaultFragment(certifiedFragment = new CertifiedFragment());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        certifiedFragment.onActivityResult(requestCode, resultCode, data);
    }
}
