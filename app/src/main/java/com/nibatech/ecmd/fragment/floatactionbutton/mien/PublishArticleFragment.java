package com.nibatech.ecmd.fragment.floatactionbutton.mien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.bean.mien.MienArticleCreateBean;
import com.nibatech.ecmd.common.request.MienRequest;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.common.request.callback.VolleyCallback;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 医生端   mien-点加号-发表文章
 */
public class PublishArticleFragment extends Fragment {

    private AutoGridImageEdit autoGridImageEdit;
    private ArrayList<String> pathList;
    private MienArticleCreateBean mienArticleCreateBean;
    private Button btnPublish;
    private EditText editTitle, editContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_acticle, container, false);

        initView(view);
        getHttpData();
        setControllerListener();

        return view;
    }

    private void initView(View view) {
        autoGridImageEdit = (AutoGridImageEdit) view.findViewById(R.id.auto_image_container);
        btnPublish = (Button) view.findViewById(R.id.btn_publish);
        editTitle = (EditText) view.findViewById(R.id.edit_title);
        editContent = (EditText) view.findViewById(R.id.edit_content);
        //初始化数据
        pathList = new ArrayList<>();
    }

    private void setControllerListener() {
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptPublish()) {
                    if (pathList.size() == 0) {
                        //没有图片，只有文字
                        requestPublishHasNoPicture();
                    } else {
                        requestPostPictures();
                    }
                }
            }
        });
    }

    private void getHttpData() {
        MienRequest.postCreateArticle(new VolleyCallback() {
            @Override
            public void onSuccessResponse(JSONObject success) {
                postCreateArticleSuccess(success.toString());
            }
        });
    }

    private void postCreateArticleSuccess(String success) {
        mienArticleCreateBean = new Gson().fromJson(success, MienArticleCreateBean.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                case PhotoViewActivity.REQUIRE_TYPE_GALLERY://图库
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    autoGridImageEdit.addImages(paths);
                    pathList.addAll(paths);
                    break;
                case PhotoViewActivity.REQUIRE_TYPE_DELETE_IMAGE://删除照片
                    autoGridImageEdit.removeImage(data.getStringExtra(PhotoViewActivity.RESULT_PATH));
                    break;
            }
        }
    }

    private boolean attemptPublish() {
        boolean ok = false;

        if (editTitle.getText().toString().isEmpty()) {
            editTitle.setError(getString(R.string.error_field_required));
        } else if (editContent.getText().toString().isEmpty()) {
            editContent.setError(getString(R.string.error_field_required));
        } else if (mienArticleCreateBean == null) {
            UIUtils.connectToHostShowFail(getActivity());
        } else {
            ok = true;
        }

        return ok;
    }

    private void requestPublishHasNoPicture() {
        MienRequest.putPublishArticle(getActivity(), mienArticleCreateBean.getPublishUrl(),
                editTitle.getText().toString(),
                editContent.getText().toString(),
                null,
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "文章发表成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                });
    }

    private void requestPostPictures() {
        BaseOKHttpRequest.post(getActivity(), mienArticleCreateBean.getUploadImageUrl(),
                pathList, new OKHttpCallback() {
                    @Override
                    public void onSuccess(String success) {
                        requestPostArticle(success);
                    }
                });
    }

    private void requestPostArticle(String json) {
        MienRequest.putPublishArticle(null, mienArticleCreateBean.getPublishUrl(),
                editTitle.getText().toString(),
                editContent.getText().toString(),
                UIUtils.getArrayUrl(json),
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONObject success) {
                        Toast.makeText(getActivity(), "您的文章发表成功！", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                });
    }
}
