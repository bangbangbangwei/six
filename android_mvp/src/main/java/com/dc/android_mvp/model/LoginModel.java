package com.dc.android_mvp.model;

import android.os.Handler;
import android.text.TextUtils;

import com.dc.android_mvp.RequsetCallBack;
import com.dc.android_mvp.api.UserApi;
import com.dc.android_mvp.bean.HttbBean;
import com.google.gson.Gson;

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

public class LoginModel implements IloginModel {
    Handler handler = new Handler();
    @Override
        public void login(HashMap<String, String> params, final RequsetCallBack requsetCallback) {
        //okHttp网络框架的管理类
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                readTimeout(5,TimeUnit.SECONDS).
                connectTimeout(5,TimeUnit.SECONDS).
                writeTimeout(5,TimeUnit.SECONDS).
                build();
        //对请求体,构建数据的过程,建造这模式
        FormBody.Builder formbody = new FormBody.Builder();
        for (Map.Entry<String,String>p:params.entrySet()){
            formbody.add(p.getKey(),p.getValue());
        }
        //创建请求对象
        final Request request = new Request.Builder().url(UserApi.USER_LOGIN).post(formbody.build()).build();
        //创建请求执行对象
        Call call = okHttpClient.newCall(request);
        //异步和同步请求
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                if (requsetCallback != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requsetCallback.failure("网络不稳定,请稍后再试");
                        }
                    });
                }
            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int code = response.code();
                    if (!TextUtils.isEmpty(result)){
                        paserResult(result,requsetCallback,code);
                    }
            }
        });
    }

    private void paserResult(String result, final RequsetCallBack requsetCallback, int code) {
        final HttbBean httbBean = new Gson().fromJson(result, HttbBean.class);
        if (requsetCallback != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requsetCallback.success(httbBean);
                }
            });
        }
    }
}
