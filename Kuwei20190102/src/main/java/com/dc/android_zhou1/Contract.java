package com.dc.android_zhou1;

import java.util.HashMap;

public interface Contract {
    public interface OkhttpCallBack{
        void success(String res);
        void fail(String string);
    }
    public interface iJsonModel{
        void getJson(String url, HashMap<String,String> params,OkhttpCallBack okhttpCallBack);
    }
    public interface iJsonView{
        void success(String res);
        void fail(String string);
    }
}
