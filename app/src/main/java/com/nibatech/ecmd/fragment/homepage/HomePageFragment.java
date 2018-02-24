package com.nibatech.ecmd.fragment.homepage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.news.NewsListActivity;
import com.nibatech.ecmd.activity.search.SearchListActivity;
import com.nibatech.ecmd.activity.web.WebActivity;
import com.nibatech.ecmd.common.bean.common.APIsBean;
import com.nibatech.ecmd.common.bean.common.BannerBean;
import com.nibatech.ecmd.common.preferences.APISharePreferences;
import com.nibatech.ecmd.common.request.CommonRequest;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.stx.xhb.mylibrary.XBanner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生端／患者端／游客端   首页-父类
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvKouchou;
    private TextView tvShimian;
    private TextView tvShanghai;
    private TextView tvHejiu;
    private TextView tvFangai;
    private LinearLayout llSearch;


    //子类必须实现的接口
    interface ShowHomePageDataListener {
        void onShowHomeIcon();

        void onClickGuide();

        void onClickMien();

        void onClickPrize();
    }

    private TextView mTxtGuide;
    private TextView mTxtMien, mTxtNews;
    private XBanner xBanner;
    protected TextView mTxtPrize;
    private ShowHomePageDataListener listener;

    //子类初始化的时候设置监听
    protected void setHomePageListener(ShowHomePageDataListener showHomePageDataListener) {
        listener = showHomePageDataListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        //初始化所有控件
        initView(view);
        //设置控件事件
        setViewListener();
        //设置控件内容
        setViewData();

        return view;
    }


    @Override
    public void onClick(View view) {
        String url = "";
        switch (view.getId()) {
            case R.id.id_txt_guide://跳转中医指导
                listener.onClickGuide();
                break;
            case R.id.id_txt_mien://跳转中医风采
                listener.onClickMien();
                break;
            case R.id.id_txt_news://跳转平台动态
                gotoNews();
                break;
            case R.id.id_txt_prize://医生跳转有奖分析, 病人跳转芳海寻珠
                listener.onClickPrize();
                break;
            case R.id.ll_search:
                gotoSearching();
                break;
            case R.id.tv_kouchou:
                url = "http://mp.weixin.qq.com/s/chw8ChbbHwx11uRhWaK8mw";
                break;
            case R.id.tv_shimian:
                url = "http://mp.weixin.qq.com/s/_1TbvhyvhSE3HJmw18W_Rw";
                break;
            case R.id.tv_shanghai:
                url = "http://mp.weixin.qq.com/s/s7ecLff8y0E5t5zCS-ALBA";
                break;
            case R.id.tv_hejiu:
                url = "http://mp.weixin.qq.com/s/tWhF1Jsco2_t5Y9OQTxSIQ";
                break;
            case R.id.tv_fangai:
                url = "http://mp.weixin.qq.com/s/hLouc4gtRoIQ24sMhRCmmw";
                break;
        }
        if (!url.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(ExtraPass.SELF_URL, url);
            intent.setClass(getActivity(), WebActivity.class);
            startActivity(intent);
        }
    }

    private void initView(View view) {
        //guide
        mTxtGuide = (TextView) view.findViewById(R.id.id_txt_guide);
        //mien
        mTxtMien = (TextView) view.findViewById(R.id.id_txt_mien);
        //news
        mTxtNews = (TextView) view.findViewById(R.id.id_txt_news);
        //有奖分析/sea
        mTxtPrize = (TextView) view.findViewById(R.id.id_txt_prize);
        //图片轮播器
        xBanner = (XBanner) view.findViewById(R.id.xbanner_home);
        //搜索
        llSearch = (LinearLayout) view.findViewById(R.id.ll_search);
        //web
        tvKouchou = (TextView) view.findViewById(R.id.tv_kouchou);
        tvShimian = (TextView) view.findViewById(R.id.tv_shimian);
        tvShanghai = (TextView) view.findViewById(R.id.tv_shanghai);
        tvHejiu = (TextView) view.findViewById(R.id.tv_hejiu);
        tvFangai = (TextView) view.findViewById(R.id.tv_fangai);
    }

    private void setViewListener() {
        //guide
        mTxtGuide.setOnClickListener(this);
        //有奖分析
        mTxtPrize.setOnClickListener(this);
        //mien
        mTxtMien.setOnClickListener(this);
        //news
        mTxtNews.setOnClickListener(this);
        //搜索
        llSearch.setOnClickListener(this);
        //web
        tvKouchou.setOnClickListener(this);
        tvShimian.setOnClickListener(this);
        tvShanghai.setOnClickListener(this);
        tvHejiu.setOnClickListener(this);
        tvFangai.setOnClickListener(this);
    }

    private void setViewData() {
        //轮播
        setBanner();
        //中间的图标
        listener.onShowHomeIcon();
    }


    private void setBanner() {
//        CommonRequest.getBanner(new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                getBannerSuccess(success.toString());
//            }
//        });
        String success = "{\"banners\": [\n" +
                "    {\n" +
                "        \"link_url\": \"http://mp.weixin.qq.com/s/chw8ChbbHwx11uRhWaK8mw\",\n" +
                "        \"image_url\": \"http://p0.so.qhimgs1.com/bdr/326__/t016ea91f1d952fff4a.jpg\",\n" +
                "        \"title\": \"test1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"link_url\": \"http://mp.weixin.qq.com/s/_1TbvhyvhSE3HJmw18W_Rw\",\n" +
                "        \"image_url\": \"http://p4.so.qhmsg.com/bdr/326__/t01e57bac1b51171009.jpg\",\n" +
                "        \"title\": \"test2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"link_url\": \"http://mp.weixin.qq.com/s/s7ecLff8y0E5t5zCS-ALBA\",\n" +
                "        \"image_url\": \"http://p4.so.qhmsg.com/bdr/326__/t011cd344b5077e5209.jpg\",\n" +
                "        \"title\": \"test3\"\n" +
                "    }\n" +
                "]}";
        getBannerSuccess(success);
    }

    //获得首页图片banner
    private void getBannerSuccess(String success) {
        final BannerBean bannerBean = new Gson().fromJson(success, BannerBean.class);
        final List<String> imagesUrl = new ArrayList<>();
        List<String> textList = new ArrayList<>();
        for (int i = 0; i < bannerBean.getBanners().size(); i++) {
            imagesUrl.add(bannerBean.getBanners().get(i).getImageUrl());
            textList.add(bannerBean.getBanners().get(i).getTitle());
        }
        xBanner.setData(imagesUrl, textList);//第二个参数为提示文字资源集合
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                UIUtils.loadNetworkImage(getActivity(), imagesUrl.get(position), (ImageView) view);
            }
        });
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
//                try {
//                    Uri uri = Uri.parse(bannerBean.getBanners().get(position).getLinkUrl());
//                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
//                } catch (Exception e) {
//                    Log.d(SaveFile.DEBUG_TAG, e.toString());
//                }

                Intent intent = new Intent();
                intent.putExtra(ExtraPass.SELF_URL,bannerBean.getBanners().get(position).getLinkUrl());
                intent.setClass(getActivity(),WebActivity.class);
                startActivity(intent);
            }
        });
    }

    //跳转平台动态
    private void gotoNews() {
        startActivity(new Intent(getActivity(), NewsListActivity.class));
    }

    //跳转搜索
    private void gotoSearching() {
//        startActivityBindData(SearchListActivity.class, getAPIsUrl(getActivity()).getEntranceNormalSearch());
        startActivityBindData(SearchListActivity.class, "");

    }

    //获取首页上的api-url
    public static APIsBean getAPIsUrl(Context context) {
        APIsBean apIsBean = APISharePreferences.get(context);
        if (apIsBean == null) {//无网络，得不到服务器的api-url
            apIsBean = new APIsBean();
        }

        return apIsBean;
    }
}
