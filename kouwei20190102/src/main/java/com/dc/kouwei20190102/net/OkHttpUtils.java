package com.dc.kouwei20190102.net;

import android.os.Handler;

import com.dc.kouwei20190102.contract.Contract;

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
    public static OkHttpUtils mInstance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();

    /**
     * 创建拦截器和okhttp框架
     */
    public OkHttpUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(httpLoggingInterceptor.getLevel().BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 锁定单例方法
     * @return
     */
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

    /**
     * POST请求
     * @param url
     * @param params
     * @param okhttpCallBack
     */
    public void doPost(String url, HashMap<String,String> params, final Contract.OkhttpCallBack okhttpCallBack){
        FormBody.Builder forbody = new FormBody.Builder();
        for (Map.Entry<String, String> mp : params.entrySet()) {
            forbody.add(mp.getKey(),mp.getValue());
        }
        Request request = new Request.Builder().url(url).post(forbody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallBack.fail("网络异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       okhttpCallBack.success(res);
                    }
                });
            }
        });
    }

    /**
     * 防止内存溢出
     */
    public void cancellMvp(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
