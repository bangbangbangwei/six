package com.dc.kouwei20190102.contract;

import java.util.HashMap;

/**
 * 契约类
 */
public interface Contract {
    /**
     * Presenter接口
     */
    public interface OkhttpCallBack{
        void success(String res);
        void fail(String string);
    }

    /**
     * Model接口
     */
    public interface iJsonModel{
        void getJson(String url, HashMap<String,String> params, OkhttpCallBack okhttpCallBack);
    }

    /**
     * View接口
     */
    public interface iJsonView{
        void success(String res);
        void fail(String string);
    }
}
