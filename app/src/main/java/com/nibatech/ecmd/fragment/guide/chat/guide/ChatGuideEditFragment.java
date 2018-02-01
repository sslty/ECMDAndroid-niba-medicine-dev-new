package com.nibatech.ecmd.fragment.guide.chat.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.common.PatientBean;
import com.nibatech.ecmd.common.request.ChatRequest;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.config.ExtraPass;
import com.nibatech.ecmd.config.SaveFile;
import com.nibatech.ecmd.fragment.guide.chat.BaseChatFragment;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.path;


/**
 * 医生端   guide-聊天-发送指导意见
 */
public class ChatGuideEditFragment extends Fragment implements View.OnClickListener {

    private ImageView ivGuidePic;
    private Button btnReCamera;
    private Button btnSendGuide;
    private String mStrUrl, mStrImageUrl;
    private ArrayList<String> picPath;
    private int picNumber;
    private EditText edContent, edTime;
    private String mStrPath, mStrJson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_guide_edit, container, false);

        getIntentData();

        getAllController(view);

        setControllerListener();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_guide_camera:
                if (attemptSend()) {
                    send();
                }
                break;
            case R.id.iv_guide_pic:
                openCamera();
                break;
            case R.id.btn_re_camera:
                openCamera();
                break;
        }
    }

    private void getIntentData() {
        mStrUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_SELF_URL);
        mStrImageUrl = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_IMAGE_URL);
        mStrPath = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_IMAGE_PATH);
        mStrJson = getActivity().getIntent().getStringExtra(ExtraPass.EXTRA_CHAT_PATIENT_JSON);

        picNumber = 0;
        picPath = new ArrayList<>();
    }

    private void getAllController(View view) {
        ivGuidePic = (ImageView) view.findViewById(R.id.iv_guide_pic);
        btnReCamera = (Button) view.findViewById(R.id.btn_re_camera);
        btnSendGuide = (Button) view.findViewById(R.id.btn_send_guide_camera);
        edContent = (EditText) view.findViewById(R.id.edit_content);
        edTime = (EditText) view.findViewById(R.id.id_edit_time);
    }

    private void setControllerListener() {
        btnSendGuide.setOnClickListener(this);
        btnReCamera.setOnClickListener(this);
        ivGuidePic.setOnClickListener(this);
        UIUtils.setInputMaxLengthListener(getActivity(), edTime, "复诊时间应该在0-60天之间", 2);
    }

    private boolean attemptSend() {
        boolean ok = false;

        if (edContent.getText().toString().isEmpty()) {
            edContent.setError(getString(R.string.error_field_required));
        } else if (edTime.getText().toString().isEmpty()) {
            edTime.setError(getString(R.string.error_field_required));
        } else if (picNumber == 0) {
            Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_SHORT).show();
        } else if (Integer.valueOf(edTime.getText().toString()) <= 0 ||
                Integer.valueOf(edTime.getText().toString()) > 60) {
            edTime.setError("复诊时间应该在0-60天之间");
        } else {
            ok = true;
        }

        return ok;
    }

    private void send() {
        requestPostImage();
    }

    private void requestPostImage() {
        BaseOKHttpRequest.post(getActivity(), mStrImageUrl, picPath, new OKHttpCallback() {
            @Override
            public void onSuccess(String success) {
                requestPostSuccess(success);
            }
        });
    }

    //上传图片成功，再次上传资料信息和图片url
    private void requestPostSuccess(String json) {
        ChatRequest.getGuideSuggestion(mStrUrl,
                edContent.getText().toString(),
                UIUtils.getImageUrl(json),
                Integer.valueOf(edTime.getText().toString()),
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        //把返回数据存入Intent
                        intent.putExtra(ExtraPass.EXTRA_CHAT_GUIDE_SUGGESTION_JSON, success.toString());
                        //设置返回数据
                        getActivity().setResult(BaseChatFragment.SELECT_BY_GUIDE, intent);
                        //关闭Activity
                        getActivity().finish();
                    }
                });
    }

    private void addWaterMark() {
        PatientBean patientBean = new Gson().fromJson(mStrJson, PatientBean.class);
        String info = "CD: " + patientBean.getCdNumber() + " " + patientBean.getGender()
                + " " + patientBean.getAge() + "岁 " + "日期: " +
                UIUtils.timeISO8601ConvertToNormal(UIUtils.getTimeNow());

        Bitmap bitmap = BitmapFactory.decodeFile(mStrPath);
        if (bitmap != null) {
            bitmap = drawTextToBitmap(info, bitmap);
            ivGuidePic.setImageBitmap(bitmap);

            saveImageToGallery(getActivity(), bitmap);
            File f = new File(mStrPath);
            f.delete();

            //路径
            String sysPath = getActivity().getExternalCacheDir().toString();
            File file = new File(new File(sysPath), System.currentTimeMillis() + ".jpg");
            String pathPicture = file.getPath();

            savePicture(bitmap, pathPicture);
            picPath.add(picNumber++, pathPicture);
        }
    }

    private void openCamera() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), PhotoViewActivity.class);
        //传给PhotoViewActivity，选择相机
        intent.putExtra(PhotoViewActivity.REQUIRE_TYPE, PhotoViewActivity.REQUIRE_TYPE_CAMERA);
        //告诉调用的activity，选择相机返回的
        startActivityForResult(intent, PhotoViewActivity.REQUIRE_TYPE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                    if (data != null) {
                        ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                        mStrPath = paths.get(0);
                        addWaterMark();
                    }
                    break;
            }
        }
    }

    public Bitmap drawTextToBitmap(String text, Bitmap bitmap) {

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float scale = dm.density;

        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }

        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
//        paint.setTextSize((int) (9 * scale));
        paint.setTextSize(UIUtils.dip2px(8));
        paint.setDither(true); //获取跟清晰的图像采样
        paint.setFilterBitmap(true);//过滤一些
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = 20;
        int y = 25;
        canvas.drawText(text, x * scale, y * scale, paint);
        return bitmap;
    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "ECMD");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "guide_picture" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }

    //保存图片
    private void savePicture(Bitmap bitmap, String path) {
        File destFile = new File(path);
        OutputStream os;
        try {
            os = new FileOutputStream(destFile);
            bitmap.compress(Bitmap.CompressFormat.WEBP, 100, os);
            os.flush();
            os.close();
        } catch (IOException e) {
            Log.d(SaveFile.DEBUG_TAG, e.toString());
        }
    }
}