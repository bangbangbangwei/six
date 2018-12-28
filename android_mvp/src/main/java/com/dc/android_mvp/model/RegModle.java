package com.dc.android_mvp.model;

import com.dc.android_mvp.api.UserApi;
import com.dc.android_mvp.contract.RegContract;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public class RegModle implements RegContract.IReModle {

    @Override
    public void reg(HashMap<String, String> params) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        FormBody.Builder formbody = new FormBody.Builder();
        for (Map.Entry<String,String> p: params.entrySet()){
            formbody.add(p.getKey(),p.getValue());
        }
        Request request = new Request.Builder().url(UserApi.USER_REG).post(formbody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    @Override
    public void login(HashMap<String, String> params) {

    }
}
