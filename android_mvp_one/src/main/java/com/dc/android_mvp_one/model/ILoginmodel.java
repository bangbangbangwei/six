package com.dc.android_mvp_one.model;

import android.os.Handler;

import com.dc.android_mvp_one.bean.HttpBean;
import com.dc.android_mvp_one.contract.ILogincontract;
import com.dc.android_mvp_one.net.ResponseCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ILoginmodel implements ILogincontract.ILoginModel {
    Handler handler = new Handler();
    @Override
    public void setokhttp(HashMap<String, String> map, String string, final ResponseCallBack responseCallBack) {
        //okhttp网络请求框架
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : map.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }
        final Request request = new Request.Builder().url(string).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        responseCallBack.fail("网络连接超时");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String result = response.body().string();
                if (200 == response.code()){
                    getjson(result,responseCallBack);
                }
            }
        });
    }

    private void getjson(String result, final ResponseCallBack responseCallBack) {
        final HttpBean httpBean = new Gson().fromJson(result, HttpBean.class);
        if (httpBean != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    responseCallBack.success(httpBean);
                }
            });
        }
    }

}
