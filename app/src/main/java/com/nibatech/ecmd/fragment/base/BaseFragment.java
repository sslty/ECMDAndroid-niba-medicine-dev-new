package com.nibatech.ecmd.fragment.base;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.nibatech.ecmd.common.bean.common.DoctorProfileBean;
import com.nibatech.ecmd.common.bean.common.PatientProfileBean;
import com.nibatech.ecmd.config.DataKey;
import com.nibatech.ecmd.config.ExtraPass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * fragment 父类
 * 实现一些共有的功能
 */
public class BaseFragment extends SlidingTabFragment {
    /**
     * startActivityNotBindData
     * 参数：
     * Class cls 界面名称
     * 功能：进入下一个界面，不绑定数据
     */
    protected void startActivityNotBindData(Class cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        startActivity(intent);
    }

    /**
     * startActivityBindData
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * 功能：进入下一个界面，并绑定数据
     */
    protected void startActivityBindData(Class cls, String strSelfUrl) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.setClass(getActivity(), cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindDataForResult
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * int requestCode
     * 功能：进入下一个界面，并绑定数据，而且需要回传数据
     */
    protected void startActivityBindDataForResult(Class cls, String strSelfUrl, int requestCode) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.setClass(getActivity(), cls);
            startActivityForResult(intent, requestCode);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindData
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * String strTitle 标题
     * 功能：进入下一个界面，并绑定数据
     */
    protected void startActivityBindData(Class cls, String strSelfUrl, String strTitle) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.TITLE, strTitle);
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.setClass(getActivity(), cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindData
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * int state 哪个入口
     * String join 是否已经接单
     * 功能：进入下一个界面，并绑定数据
     */
    protected void startActivityBindData(Class cls, String strSelfUrl, int entrance, String join) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.putExtra(ExtraPass.ENTRANCE, entrance);
            intent.putExtra(ExtraPass.JOIN, join);
            intent.setClass(getActivity(), cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * 动画
     * @param cls
     * @param strSelfUrl
     * @param entrance
     * @param join
     * @param activityOptions
     */
    protected void startActivityBindData(Class cls, String strSelfUrl, int entrance, String join, ActivityOptions activityOptions) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.putExtra(ExtraPass.ENTRANCE, entrance);
            intent.putExtra(ExtraPass.JOIN, join);
            intent.setClass(getActivity(), cls);
            startActivity(intent, activityOptions.toBundle());
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindData
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * String strTitle 标题
     * String strFabUrl floating-action-button url
     * 功能：进入下一个界面，并绑定数据
     */
    protected void startActivityBindData(Class cls, String strSelfUrl, String strTitle, String strFabUrl) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.TITLE, strTitle);
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.putExtra(ExtraPass.FLOATING_ACTION_BUTTON, strFabUrl);
            intent.setClass(getActivity(), cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * startActivityBindData
     * 参数：
     * Class cls 界面名称
     * String strSelfUrl self-url 自身的url
     * String detailsUrl 细节的url
     * int state
     * 功能：进入下一个界面，并绑定数据
     */
    protected void startActivityBindData(Class cls, String strSelfUrl, String detailsUrl, int state) {
        if (strSelfUrl != null) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, strSelfUrl);
            intent.putExtra(ExtraPass.DETAILS_URL, detailsUrl);
            intent.putExtra(ExtraPass.ENTRANCE, state);
            intent.setClass(getActivity(), cls);
            startActivity(intent);
        } else {
            connectToHostFailure();
        }
    }

    /**
     * getIntentSelfUrl
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * strSelfUrl!=null, 证明为是fragment
     * strSelfUrl=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected String getIntentSelfUrl() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getString(ExtraPass.SELF_URL);
        } else {
            return getActivity().getIntent().getStringExtra(ExtraPass.SELF_URL);
        }
    }

    /**
     * getIntentDetailsUrl
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * strDetailsUrl!=null, 证明为是fragment
     * strDetailsUrl=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected String getIntentDetailsUrl() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getString(ExtraPass.DETAILS_URL);
        } else {
            return getActivity().getIntent().getStringExtra(ExtraPass.DETAILS_URL);
        }
    }

    /**
     * getIntentFabUrl
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * strFabUrl!=null, 证明为是fragment
     * strFabUrl=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected String getIntentFabUrl() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getString(ExtraPass.FLOATING_ACTION_BUTTON);
        } else {
            return getActivity().getIntent().getStringExtra(ExtraPass.FLOATING_ACTION_BUTTON);
        }
    }

    /**
     * getIntentIntEntrance
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * intEntrance!=null, 证明为是fragment
     * intEntrance=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected int getIntentIntEntrance() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getInt(ExtraPass.ENTRANCE, -1);
        } else {
            return getActivity().getIntent().getIntExtra(ExtraPass.ENTRANCE, -1);
        }
    }

    /**
     * getIntentStringEntrance
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * strEntrance!=null, 证明为是fragment
     * strEntrance=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected String getIntentStringEntrance() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getString(ExtraPass.ENTRANCE);
        } else {
            return getActivity().getIntent().getStringExtra(ExtraPass.ENTRANCE);
        }
    }

    /**
     * getIntentButtonName
     * 参数：
     * 无
     * 功能：获取上一层传递过来的数据，返回string
     * bundle=null, fragment未绑定数据
     * strName!=null, 证明为是fragment
     * strName=null, 证明为是activity
     * 最后返回的是null，证明没有传递数据
     */
    protected String getIntentButtonName() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return getArguments().getString(ExtraPass.NAME);
        } else {
            return getActivity().getIntent().getStringExtra(ExtraPass.NAME);
        }
    }

    /**
     * connectToHostFailure
     * 参数：
     * 无
     * 功能：网络连接失败提示
     */
    protected void connectToHostFailure() {
        Toast.makeText(getActivity(), "网络连接失败，请检查网络设置!", Toast.LENGTH_SHORT).show();
    }

    /**
     * addPageFragmentBindData
     * 参数：
     * ArrayList<Fragment> fragments tab分页界面
     * String[] titles 分页标题
     * List<String> selfUrls 分页url
     * 功能：添加分页fragment到fragment，并添加self-url数据
     */
    protected void addPageFragmentBindData(ArrayList<Fragment> fragments, String[] titles,
                                           List<String> selfUrls) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);

            Bundle bundle = new Bundle();
            bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));

            Bundle oldBundle = fragment.getArguments();
            if (oldBundle == null) {
                fragment.setArguments(bundle);
            }
        }

        addTabLayout(fragments, titles);
    }

    /**
     * addPageFragmentBindData
     * 参数：
     * ArrayList<Fragment> fragments tab分页界面
     * String[] titles 分页标题
     * List<String> selfUrls 分页url
     * List<String> detailsUrls 详情url
     * 功能：添加分页fragment到fragment，并添加self-url数据，details-url数据
     */
    protected void addPageFragmentBindData(ArrayList<Fragment> fragments, String[] titles,
                                           List<String> selfUrls, List<String> detailsUrls) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);

            Bundle bundle = new Bundle();
            bundle.putString(ExtraPass.SELF_URL, selfUrls.get(i));
            bundle.putString(ExtraPass.DETAILS_URL, detailsUrls.get(i));

            Bundle oldBundle = fragment.getArguments();
            if (oldBundle == null) {
                fragment.setArguments(bundle);
            }
        }

        addTabLayout(fragments, titles);
    }

    /**
     * setDoctorProfileToMap
     * 参数：
     * DoctorProfileBean profile 医生个人信息
     * Map<String, Object> map map数据存储
     * 功能：把医生的信息一起存储到map中
     */
    protected void setDoctorProfileToMap(DoctorProfileBean profile, Map<String, Object> map) {
        if (profile != null) {
            //个人首页url
            String strHomepageUrl = profile.getHomepageUrl();
            //头像url
            String strAvatarUrl = profile.getAvatarUrl();
            //性别
            String strGender = profile.getGender();
            //年龄
            String strAge = String.valueOf(profile.getAge());
            //姓名
            String strName = profile.getFullName();
            //医馆
            String strType = profile.getDoctorType();
            //专业
            String strSpecialism = profile.getSpecialism();
            //CD-number
            String strCdNumber = profile.getCdNumber();
            //是否认证
            Boolean verified = profile.isVerified();
            //是否优秀
            Boolean good = profile.isExcellent();

            map.put(DataKey.KEY_HOMEPAGE, strHomepageUrl);
            map.put(DataKey.KEY_AVATAR, strAvatarUrl);
            map.put(DataKey.KEY_GENDER, strGender);
            map.put(DataKey.KEY_AGE, strAge);
            map.put(DataKey.KEY_NAME, strName);
            map.put(DataKey.KEY_TYPE, strType);
            map.put(DataKey.KEY_CD_NUMBER, strCdNumber);
            map.put(DataKey.KEY_SPECIALISM, strSpecialism);
            map.put(DataKey.KEY_VERIFIED, verified);
            map.put(DataKey.KEY_GOOD, good);
        }
    }

    /**
     * setPatientProfileToMap
     * 参数：
     * DoctorProfileBean profile 患者个人信息
     * Map<String, Object> map map数据存储
     * 功能：把患者的信息一起存储到map中
     */
    protected void setPatientProfileToMap(PatientProfileBean profile, Map<String, Object> map) {
        if (profile != null) {
            //头像url
            String strAvatarUrl = profile.getAvatarUrl();
            //性别
            String strGender = profile.getGender();
            //年龄
            String strAge = String.valueOf(profile.getAge());
            //姓名
            String strName = profile.getName();
            //城市
            String strCity = profile.getCity();
            //CD-number
            String strCdNumber = profile.getCdNumber();

            map.put(DataKey.KEY_AVATAR, strAvatarUrl);
            map.put(DataKey.KEY_GENDER, strGender);
            map.put(DataKey.KEY_AGE, strAge);
            map.put(DataKey.KEY_NAME, strName);
            map.put(DataKey.KEY_CITY, strCity);
            map.put(DataKey.KEY_CD_NUMBER, strCdNumber);
        }
    }
}
