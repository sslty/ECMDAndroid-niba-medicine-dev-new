package com.nibatech.ecmd.common.request.base;


import android.content.Context;
import android.widget.Toast;

import com.nibatech.ecmd.application.ECMDApplication;
import com.nibatech.ecmd.common.dialog.WaitingProgressDialog;
import com.nibatech.ecmd.common.request.callback.OKHttpCallback;
import com.nibatech.ecmd.utils.UIUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class BaseOKHttpRequest {
    private static final String KEY_AUTHORIZATION = "Authorization";
    private static final String KEY_UPLOAD_FILE = "upload_file";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_SYMPTOM = "symptom";
    private static final String KEY_JWT = "jwt ";
    private static final String PICTURE_TYPE = "image/png/jpg";

    //上传照片到服务器
    private static void postImage(String url, ArrayList<String> imagePaths,
                                  final OKHttpCallback okHttpCallback) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse(PICTURE_TYPE);

        //参数
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //读取图片路径，存入body
        if (imagePaths != null && imagePaths.size() != 0) {
            for (int i = 0; i < imagePaths.size(); i++) {
                String path = imagePaths.get(i);
                builder.addFormDataPart(KEY_UPLOAD_FILE + "_" + String.valueOf(i + 1), path,
                        RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
            }
        }

        requestBody = builder.build();

        //连接服务器
        createRequest(url, requestBody, okHttpCallback);
    }

    private static void createRequest(String url, RequestBody requestBody,
                                      final OKHttpCallback okHttpCallback) {
        //请求,头信息
        Request request = new Request.Builder()
                .header(KEY_AUTHORIZATION, KEY_JWT + BaseVolleyRequest.getToken())
                .url(url)
                .post(requestBody)
                .build();

        ECMDApplication.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okHttpCallback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttpCallback.onResponse(call, response);
            }
        });
    }

    //医生上传诊断照片
    public static void uploadPicture(String url, String path, String description,
                                     final OKHttpCallback okHttpCallback) {
        String key = "/data/";
        MediaType MEDIA_TYPE_PNG = MediaType.parse(PICTURE_TYPE);

        //参数
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();

        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(KEY_DESCRIPTION, description);

        if (path != null) {
            builder.addFormDataPart(KEY_UPLOAD_FILE, path, RequestBody.create(MEDIA_TYPE_PNG,
                    new File(path)));
        }

        requestBody = builder.build();

        //连接服务器
        createRequest(url + key, requestBody, okHttpCallback);
    }

    //提交挂单求医
    public static void postOrder(String url, ArrayList<String> pathList, String description, String symptom,
                                 final OKHttpCallback okHttpCallback) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse(PICTURE_TYPE);

        //参数
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();

        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(KEY_DESCRIPTION, description);
        builder.addFormDataPart(KEY_SYMPTOM, symptom);

        if (pathList != null && pathList.size() != 0) {
            for (int i = 0; i < pathList.size(); i++) {
                String path = pathList.get(i);
                builder.addFormDataPart(KEY_UPLOAD_FILE + "_" + String.valueOf(i + 1), path,
                        RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
            }
        }

        requestBody = builder.build();

        //连接服务器
        createRequest(url, requestBody, okHttpCallback);
    }

    public static void post(final Context context, String url, ArrayList<String> paths, final OKHttpCallback okHttpCallback) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse(PICTURE_TYPE);
        //参数
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //读取图片路径，存入body
        if (paths != null && paths.size() != 0) {
            for (int i = 0; i < paths.size(); i++) {
                String path = paths.get(i);
                builder.addFormDataPart(KEY_UPLOAD_FILE + "_" + String.valueOf(i + 1), path,
                        RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
            }
        }
        requestBody = builder.build();
        //头信息
        Request request = new Request.Builder()
                .header(KEY_AUTHORIZATION, KEY_JWT + BaseVolleyRequest.getToken())
                .url(url)
                .post(requestBody)
                .build();
        //等待框
        final WaitingProgressDialog waitingProgressDialog = WaitingProgressDialog.createDialog(context);
        waitingProgressDialog.show();
        //请求
        ECMDApplication.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        waitingProgressDialog.dismiss();
                        Toast.makeText(context, "服务器连接超时，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
                okHttpCallback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                /**warning:
                 * response.body().string();最多只能调用1次,如果调用2次以上,就会提示java.lang.IllegalStateException: closed
                 * 就是说调用response.body().string()的时候数据流已经关闭了，再次调用就是提示已经closed，或者没有数据送到解析处。
                 * 同时，debug watch时候也不要调用response.body().string()
                 * 详细解释请见帖子http://blog.csdn.net/u014616515/article/details/52202942
                 */
                String success = "";
                try {
                    success = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                UIUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        waitingProgressDialog.dismiss();
                    }
                });

                if (response.isSuccessful()) {
                    okHttpCallback.onSuccess(success);
                } else {
                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片上传失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
