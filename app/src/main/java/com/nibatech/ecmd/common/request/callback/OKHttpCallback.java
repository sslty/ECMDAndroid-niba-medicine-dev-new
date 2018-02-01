package com.nibatech.ecmd.common.request.callback;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class OKHttpCallback implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {
    }

    public void onSuccess(String success) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
    }
}

