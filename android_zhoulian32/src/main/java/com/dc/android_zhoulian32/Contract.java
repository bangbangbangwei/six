package com.dc.android_zhoulian32;

import java.util.HashMap;

public interface Contract {
    public interface OkHttpCallBack{
        void success(String res);
        void fail(String string);
    }
    public interface IJsonModel{
        void getJson(String api, HashMap<String,String> params,OkHttpCallBack okHttpCallBack);
    }
    public interface IJsonView{
        void success(String res);
        void fail(String string);
    }
}
