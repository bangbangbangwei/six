package com.dc.android_zhoulian2;

import java.util.HashMap;

public class Model implements Contract.IJsonModel {
    @Override
    public void getJson(String url, HashMap<String, String> params, Contract.OkhttpCallBAck okhttpCallBAck) {
        OkHttpUtils.getmInstance().doPost(url,params,okhttpCallBAck);
    }
}
