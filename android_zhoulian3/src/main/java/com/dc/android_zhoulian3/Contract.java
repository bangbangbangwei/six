package com.dc.android_zhoulian3;

import java.util.HashMap;

public interface Contract {
    public interface OkHttpCallBack{
        void success(String res);
        void fail(String meg);
    }
    public interface IJsonModel{
        void getJson(String url, HashMap<String,String> params,OkHttpCallBack okHttpCallBack);
    }
    public interface IJsonView{
        void success(String res);
        void fail(String meg);
    }
}
