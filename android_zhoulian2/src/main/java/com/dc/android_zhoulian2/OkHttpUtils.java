package com.dc.android_zhoulian2;

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
    private static OkHttpUtils mInstance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();
    public OkHttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(httpLoggingInterceptor.getLevel().BODY);
        okHttpClient = new OkHttpClient.Builder()
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
    public void doPost(String url, HashMap<String,String> params, final Contract.OkhttpCallBAck okhttpCallBAck){
        FormBody.Builder formbody = new FormBody.Builder();
        for (Map.Entry<String, String> mp : params.entrySet()) {
            formbody.add(mp.getKey(),mp.getValue());
        }
        Request request = new Request.Builder().url(url).post(formbody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallBAck.fail("网络错误");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                        @Override
                        public void run() {

                            okhttpCallBAck.success(result);
                        }
                    });
            }
        });
    }
    public void cancellAllOkhttp(){
        if (okHttpClient != null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
