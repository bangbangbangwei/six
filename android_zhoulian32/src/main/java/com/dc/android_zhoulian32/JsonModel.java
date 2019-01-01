package com.dc.android_zhoulian32;

import java.util.HashMap;

public class JsonModel implements Contract.IJsonModel {

    @Override
    public void getJson(String api, HashMap<String, String> params, Contract.OkHttpCallBack okHttpCallBack) {
        OkHttpUtils.getmInstance().doPost(api,params,okHttpCallBack);
    }
}
