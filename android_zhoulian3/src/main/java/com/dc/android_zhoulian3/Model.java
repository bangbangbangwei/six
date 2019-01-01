package com.dc.android_zhoulian3;

import java.util.HashMap;

public class Model implements Contract.IJsonModel {
    @Override
    public void getJson(String url, HashMap<String, String> params, Contract.OkHttpCallBack okHttpCallBack) {
        OkHttpUtils.getmInstance().doPost(url,params,okHttpCallBack);
    }
}
