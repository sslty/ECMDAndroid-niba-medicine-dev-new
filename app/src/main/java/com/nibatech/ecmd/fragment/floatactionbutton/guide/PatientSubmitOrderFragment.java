package com.nibatech.ecmd.fragment.floatactionbutton.guide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nibatech.ecmd.R;
import com.nibatech.ecmd.activity.photo.PhotoViewActivity;
import com.nibatech.ecmd.common.request.base.BaseOKHttpRequest;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.fragment.base.BaseFragment;
import com.nibatech.ecmd.utils.UIUtils;
import com.nibatech.ecmd.view.AutoGridImageEdit;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 患者端   guide-挂单
 */
public class PatientSubmitOrderFragment extends BaseFragment {
    public static final int ACTIVITY_RESULT_SUBMIT_ORDER = 2;
    private TextView mEditCase, mEditSign;
    private Button mBtnSubmit;
    private View mProgressView;
    private ArrayList<String> pathList;
    private AutoGridImageEdit autoImageGridView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_submit_order, container, false);
        //获得控件对象
        initView(view);
        //设置控件监听事件
        setViewListener();
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoViewActivity.REQUIRE_TYPE_CAMERA://相机
                case PhotoViewActivity.REQUIRE_TYPE_GALLERY://图库
                    ArrayList<String> paths = data.getStringArrayListExtra(PhotoViewActivity.RESULT_IMAGES);
                    autoImageGridView.addImages(paths);
                    pathList.addAll(paths);
                    break;
                case PhotoViewActivity.REQUIRE_TYPE_DELETE_IMAGE://删除照片
                    autoImageGridView.removeImage(data.getStringExtra(PhotoViewActivity.RESULT_PATH));
                    pathList.remove(data.getStringExtra(PhotoViewActivity.RESULT_PATH));
                    break;
            }
        }
    }

    private void initView(View view) {
        //病例
        mEditCase = (EditText) view.findViewById(R.id.id_edit_case);
        //体征
        mEditSign = (EditText) view.findViewById(R.id.id_edit_sign);
        //挂单求医
        mBtnSubmit = (Button) view.findViewById(R.id.id_btn_submit);
        //等待按钮
        mProgressView = view.findViewById(R.id.id_progress_bar_wait);
        //自动添加图片
        autoImageGridView = (AutoGridImageEdit) view.findViewById(R.id.auto_grid_image_container);
        //初始化图片列表和计数器
        pathList = new ArrayList<>();
    }

    private void setViewListener() {
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptSubmit()) {
                    requestOrderForDoctor();
                }
            }
        });
    }

    private boolean attemptSubmit() {
        boolean ok = false;

        if (mEditCase.getText().toString().isEmpty()) {
            mEditCase.setError(getString(R.string.error_field_required));
        } else if (mEditSign.getText().toString().isEmpty()) {
            mEditSign.setError(getString(R.string.error_field_required));
        } else if (pathList == null || pathList.size() == 0) {
            Toast.makeText(getActivity(), "请选择至少一张照片", Toast.LENGTH_SHORT).show();
        } else {
            ok = true;
        }

        return ok;
    }

    //请求挂单求医
    private void requestOrderForDoctor() {
        showProgress(true);
        mBtnSubmit.setClickable(false);
        BaseOKHttpRequest.postOrder(getIntentSelfUrl(), pathList, mEditCase.getText().toString(),
                mEditSign.getText().toString(), new OKHttpCallback() {
                    @Override
                    public void onResponse(Call call, final Response response) throws IllegalStateException {
                        /**warning:
                         * response.body().string();最多只能调用1次,如果调用2次以上,就会提示java.lang.IllegalStateException: closed
                         * 就是说调用response.body().string()的时候数据流已经关闭了，再次调用就是提示已经closed，或者没有数据送到解析处。
                         * 详细解释请见帖子http://blog.csdn.net/u014616515/article/details/52202942
                         */
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                showProgress(false);
                                mBtnSubmit.setClickable(true);

                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "您已成功挂单，请等待医生给出指导意见。", Toast.LENGTH_SHORT).show();
                                    getActivity().setResult(Activity.RESULT_OK);
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), "图片上传失败" + response.message(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        UIUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                showProgress(false);
                                mBtnSubmit.setClickable(true);
                            }
                        });
                    }
                });
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


}
