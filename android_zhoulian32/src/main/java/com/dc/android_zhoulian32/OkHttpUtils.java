package com.dc.android_zhoulian32;

import android.os.Handler;

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
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private final OkHttpClient okhttp;
    private static OkHttpUtils mInstance;
    Handler handler = new Handler();
    public OkHttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(httpLoggingInterceptor.getLevel().BODY);
        okhttp = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpUtils getmInstance(){
        if (mInstance == null){
            synchronized (OkHttpUtils.class){
                if (mInstance == null){
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }
    public void doPost(String url, HashMap<String,String> params, final Contract.OkHttpCallBack okHttpCallBack){
        FormBody.Builder forbody = new FormBody.Builder();
        for (Map.Entry<String, String> mp : params.entrySet()) {
            forbody.add(mp.getKey(),mp.getValue());
        }
        Request request = new Request.Builder().url(url).post(forbody.build()).build();
        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallBack.fail("网络异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallBack.success(res);
                    }
                });
            }
        });
    }
    public void okHttpDispatch(){
        if (okhttp != null){
            okhttp.dispatcher().cancelAll();
        }
    }
}
