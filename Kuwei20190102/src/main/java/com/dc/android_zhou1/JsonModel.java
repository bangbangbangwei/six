package com.dc.android_zhou1;

import java.util.HashMap;

public class JsonModel implements Contract.iJsonModel {
    @Override
    public void getJson(String url, HashMap<String, String> params, Contract.OkhttpCallBack okhttpCallBack) {
        OkHttpUtils.getmInstance().doPost(url,params,okhttpCallBack);
    }
}
