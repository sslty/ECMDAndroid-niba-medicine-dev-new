package com.nibatech.ecmd.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.common.bean.common.ErrorMessageBean;
import com.nibatech.ecmd.common.bean.common.ImageArrayUrlBean;
import com.nibatech.ecmd.common.bean.login.LoginBean;
import com.nibatech.ecmd.common.message.CloudService;
import com.nibatech.ecmd.common.message.MessageEvent;
import com.nibatech.ecmd.common.preferences.LoginSharedPreferences;
import com.nibatech.ecmd.common.realm.BaseRealm;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.volley.VolleyNetworkImage;
import com.nibatech.ecmd.config.SaveFile;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;

import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UIUtils {
    //获得上下文
    public static Context getContext() {
        return ECMDApplication.getContext();
    }

    public static Handler getHandler() {
        return ECMDApplication.getHandler();
    }

    public static int getMainThreadId() {
        return ECMDApplication.getMainThreadId();
    }

    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            r.run();
        } else {
            getHandler().post(r);
        }
    }

    public static boolean isRunOnUIThread() {
        int myTid = Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dip + 0.5f);
    }

    public static float px2dip(float px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    //ISO8601只显示年月日
    public static String timeISO8601ConvertToNormal(String time) {
        return timeShortLineConvertToDot(time).substring(0, 10);//YYYY-MM-DD
    }

    //ISO8601去掉T
    public static String timeISO8601RemoveT(String str) {
        return timeShortLineConvertToDot(str).replace("T", " ");//YYYY-MM-DD HH:MM:SS
    }

    //-换成.
    private static String timeShortLineConvertToDot(String str) {
        return str.replace("-", ".");
    }

    public static String unicode2string(String s) {
        List<String> list = new ArrayList<>();
        String zz = "\\\\u[0-9,a-z,A-Z]{4}";

        //正则表达式用法参考API
        Pattern pattern = Pattern.compile(zz);
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            list.add(m.group());
        }
        for (int i = 0, j = 2; i < list.size(); i++) {
            String st = list.get(i).substring(j, j + 4);

            //将得到的数值按照16进制解析为十进制整数，再強转为字符
            char ch = (char) Integer.parseInt(st, 16);
            //用得到的字符替换编码表达式
            s = s.replace(list.get(i), String.valueOf(ch));
        }
        return s;
    }

    //倒计时父类
    private static long getTimeCountDownDistance(String start) {
        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());
        String now = formatter.format(curDate);

        long distance = 0;
        try {
            Date d1 = formatter.parse(now);
            Date d2 = formatter.parse(start);
            distance = d1.getTime() - d2.getTime();
            distance = distance > 0 ? distance : 1;//防止手机时间比服务器时间快
        } catch (Exception e) {
            Log.d(SaveFile.DEBUG_TAG, e.toString());
        }

        return distance;
    }

    //24小时倒计时
    public static String timeCountDownIn24(String start) {
        String str;
        long time24h = 24 * 60 * 60 * 1000;
        long distance = getTimeCountDownDistance(start);

        long diff = time24h - distance;//这样得到的差值是微秒级别
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        if (days > 0) {
            str = "已结束";
        } else if (hours > 0) {
            str = String.valueOf(hours) + "小时";
        } else if (minutes > 0) {
            str = String.valueOf(minutes) + "分钟";
        } else {
            str = "已结束";
        }

        return str;
    }

    //从现在开始算，少于15分钟-刚刚，大于15分钟-1小时-1小时，大于1小时-几小时，大于1天-几天
    public static String timeCountDownBeforeNow(String start) {
        String str;
        long diff = getTimeCountDownDistance(start);
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        if (days > 0) {
            str = String.valueOf(days) + "天前";
        } else if (hours > 0) {
            str = String.valueOf(hours) + "小时前";
        } else if (minutes > 15) {
            str = "1小时前";
        } else {
            str = "刚刚";
        }

        return str;
    }

    //从现在开始倒数第几天
    public static String timeCountDownOnToday(String start) {
        long diff = getTimeCountDownDistance(start);
        long days = diff / (1000 * 60 * 60 * 24);

        return String.valueOf(days + 1);
    }

    //end - start之间相差的天数
    public static String timeCountDownBetweenTwoDays(String start, String end) {
        long diffStart = getTimeCountDownDistance(start);
        long diffEnd = getTimeCountDownDistance(end);
        long days = (diffEnd - diffStart) / (1000 * 60 * 60 * 24);

        return String.valueOf(days + 1);
    }

    //获取当前时间
    public static String getTimeNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    //质量压缩图片
    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    //尺寸压缩图片
    public static Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        //Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    //质量尺寸压缩
    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    //得到一个不是空指针的字符串,如果指针为空,那么返回""
    public static String getNotNullString(String string) {
        return string != null ? string : "";
    }

    public static String getNotNullInteger(Integer i) {
        return i != null ? String.valueOf(i) : "";
    }

    public static void loginTM(String cd, String sign) {
        if (cd != null) {//有cd号才能登录
            TIMUser user = new TIMUser();
            user.setAccountType("7214");
            user.setAppIdAt3rd("1400014156");
            user.setIdentifier(cd);

            //发起登录请求
            TIMManager.getInstance().login(
                    1400014156,        //sdkAppId，由腾讯分配
                    user,
                    sign,              //用户帐号签名，由私钥加密获得，具体请参考文档
                    new TIMCallBack() {//回调接口
                        @Override
                        public void onSuccess() {//登录成功
                            MessageEvent.getInstance();
                        }

                        @Override
                        public void onError(int code, String desc) {//登录失败
                            //错误码code和错误描述desc，可用于定位请求失败原因, 请参见错误码表
                            Toast.makeText(getContext(), "error code = " + code, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private static void logoutTM() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因, 请参见错误码表
            }

            @Override
            public void onSuccess() {
                //登出成功
                Toast.makeText(getContext(), "用户已安全退出", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //绑定阿里云消息推送系统
    public static void loginALiYun(String phone) {
        //绑定账号
        CloudService.bindAccountToALiYun(phone);
    }

    //解除阿里云绑定
    private static void logoutALiYun() {
        //解除绑定账号
        CloudService.unbindAccountToALiYun();
    }

    //用户注销
    public static void logout() {
        //清除用户配置信息
        LoginSharedPreferences.remove(getContext());
        //清除全局变量中的信息
        BaseVolleyRequest.getECMDApplication().setLoginBean(null);
        //腾讯登出
        logoutTM();
        //解除绑定阿里云
        logoutALiYun();
    }

    //用户登录
    public static void login(Context context, String success, String phone, String password) {
        //json解析
        LoginBean loginBean = new Gson().fromJson(success, LoginBean.class);
        //新建数据库
        BaseRealm.createUserRealm(context, loginBean.getUser().getCellPhone());
        //保存用户名和密码
        LoginSharedPreferences.save(context, phone, password);
        //保存用户信息
        BaseVolleyRequest.setLogin(loginBean);
        //腾讯登录
        loginTM(loginBean.getUser().getCdNumber(), loginBean.getUser().getTlsSig());
        //绑定阿里云推送
        loginALiYun(loginBean.getUser().getCellPhone());

    }

    public static void connectToHostShowFail(Context context) {
        Toast.makeText(context, "网络连接失败，请检查网络设置", Toast.LENGTH_SHORT).show();
    }

    public static void connectToHostShowError(Context context, VolleyError error) {
        try {
            if (error.networkResponse != null && error.networkResponse.data != null) {
                String json = UIUtils.unicode2string(new String(error.networkResponse.data, "UTF-8"));
                ErrorMessageBean errorMessageBean = new Gson().fromJson(json, ErrorMessageBean.class);
                Toast.makeText(context, errorMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setInputMaxLengthListener(final Context context, EditText editText,
                                                 final String strShow, final int maxLength) {
        //输入框限制输入字数
        final long[] timeStart = {0};
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLength) {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int destStart, int destEnd) {
                if (source.length() > 0) {
                    if (dest.length() == maxLength - 1) {
                        timeStart[0] = 0;
                    } else if (dest.length() == maxLength) {
                        if (timeStart[0] == 0 || System.currentTimeMillis() - timeStart[0] > 3000) {
                            Toast.makeText(context, strShow, Toast.LENGTH_SHORT).show();
                            timeStart[0] = System.currentTimeMillis();
                        }
                    }
                }

                return super.filter(source, start, end, dest, destStart, destEnd);
            }
        };

        editText.setFilters(filters);
    }

    //聊天界面，名字和时间组合
    public static String linkNameAndTimeOnButton(String name, String time) {
        return name + "\n" + timeISO8601ConvertToNormal(time);
    }

    //从服务器loading图片，同步缓存请求
    public static void loadNetworkImage(Context context, String url, ImageView imageView) {
        ImageLoader mImageLoader = VolleyNetworkImage.getInstance(context).getImageLoader();
        mImageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image_show_loading, R.drawable.image_show_error));
    }

    public static void loadLocalImage(String path, ImageView imageView) {
        Bitmap bitmap = BitmapFactory.decodeFile(path, null);
        Drawable drawable = new BitmapDrawable(bitmap);
        imageView.setImageDrawable(drawable);
    }


    public static Map<String, Object> getColor() {
        Map<String, Object> color = new HashMap<>();
        int[] colorArray = {
                R.color.recovery,//
                R.color.remarkable,//
                R.color.valid,
                R.color.invalid,//
                R.color.processing,//
        };

        color.put(Constants.RECOVER, colorArray[0]);
        color.put(Constants.RECOVERY, colorArray[0]);
        color.put(Constants.REMARKABLE, colorArray[1]);
        color.put(Constants.VALID, colorArray[2]);
        color.put(Constants.INVALID, colorArray[3]);
        color.put(Constants.PROCESSING, colorArray[4]);

        return color;
    }

    //得到图片的url
    public static JSONArray getArrayUrl(String json) {
        ImageArrayUrlBean imageArrayUrlBean = new Gson().fromJson(json, ImageArrayUrlBean.class);
        return new JSONArray(imageArrayUrlBean.getImagesUrl());
    }

    public static String getImageUrl(String json) {
        ImageArrayUrlBean imageArrayUrlBean = new Gson().fromJson(json, ImageArrayUrlBean.class);
        return imageArrayUrlBean.getImagesUrl().get(0);
    }

    //设置web-view
    @SuppressLint("SetJavaScriptEnabled")
    public static void loadWebViewSetting(WebView webView, String url) {
        webView.loadUrl(url);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);

        //web-view中如果有https资源，需要加上此设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * fromJson
     * 参数：
     * String json json值
     * Class<T> tClass class类型
     * 功能：
     * 把string-json转化为类class，如果发生异常，直接new class()
     */
    public static <T> T fromJson(String json, Class<T> tClass) {
        T t = null;

        try {
            t = new Gson().fromJson(json, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
}

