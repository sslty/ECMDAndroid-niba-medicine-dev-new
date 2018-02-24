package com.nibatech.ecmd.fragment.photo;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.login.UserBean;
import com.nibatech.ecmd.common.request.PersonalRequest;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.base.BaseVolleyRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.utils.UIUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;


public class PhotoModifyFragment extends Fragment {
    private String path;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_modify, container, false);

//        getIntentData();
        getAllController(view);
        setControllerData();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CUT_CAMERA://相机裁剪
                case PhotoViewActivity.REQUIRE_TYPE_CUT_GALLERY://图库裁剪
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    requestUploadAvatar(paths);
                    break;
            }
        }
    }

    private void getIntentData() {
        path = BaseVolleyRequest.getLogin().getUser().getAvatarUrl();
    }

    private void getAllController(View view) {
        imageView = (ImageView) view.findViewById(R.id.id_image_view);
    }

    private void setControllerData() {
//        if (path == null) {
//            if (BaseVolleyRequest.getLogin().getUser().getGender() == null) {
//                imageView.setImageResource(R.drawable.case_list_view_null);
//            } else if (BaseVolleyRequest.getLogin().getUser().getGender().compareTo("男") == 0) {
//                imageView.setImageResource(R.drawable.head_man);
//            } else {
//                imageView.setImageResource(R.drawable.head_woman);
//            }
//        } else {
//            BaseVolleyRequest.loadNetworkImage(getActivity(), path, imageView);
//        }

        imageView.setImageResource(R.drawable.head_woman);

        //手势
        new PhotoViewAttacher(imageView);
    }

    private void requestUploadAvatar(final ArrayList<String> paths) {
//        String url = BaseVolleyRequest.HOST_URL + "/api/user/upload_avatar";
//        BaseOKHttpRequest.post(getActivity(), url, paths, new OKHttpCallback() {
//            @Override
//            public void onSuccess(String success) {
//                getPostSuccess(success, paths.get(0));
//            }
//        });
        getPostSuccess("", paths.get(0));
    }

    private void getPostSuccess(final String json, final String path) {
//        PersonalRequest.putUpdateAvatar(UIUtils.getImageUrl(json), new VolleyCallback() {
//            @Override
//            public void onSuccessResponse(JSONObject success) {
//                Toast.makeText(getActivity(), "头像更新成功", Toast.LENGTH_SHORT).show();
//                Bitmap bitmap = BitmapFactory.decodeFile(path, null);
//                imageView.setImageBitmap(bitmap);
//                //更新头像
//                UserBean userBean = BaseVolleyRequest.getLogin().getUser();
//                userBean.setAvatarUrl(UIUtils.getImageUrl(json));
//                BaseVolleyRequest.setUser(userBean);
//            }
//        });
        Toast.makeText(getActivity(), "头像更新成功", Toast.LENGTH_SHORT).show();
        Bitmap bitmap = BitmapFactory.decodeFile(path, null);
        imageView.setImageBitmap(bitmap);
    }
}
