package com.dc.android_zhoulian2;

import java.util.HashMap;

public interface Contract {
    interface OkhttpCallBAck{
        void success(String res);
        void fail(String meg);
    }
    interface IJsonModel{
        void getJson(String url, HashMap<String,String> params,OkhttpCallBAck okhttpCallBAck);
    }
    interface IJsonView{
        void success(String res);
        void fail(String meg);
    }
}
