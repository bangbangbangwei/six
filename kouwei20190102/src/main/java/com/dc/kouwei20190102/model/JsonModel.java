package com.dc.kouwei20190102.model;

import com.dc.kouwei20190102.net.OkHttpUtils;
import com.dc.kouwei20190102.contract.Contract;

import java.util.HashMap;

public class JsonModel implements Contract.iJsonModel {
    /**
     * 引用Model接口回调契约类
     * @param url
     * @param params
     * @param okhttpCallBack
     */
    @Override
    public void getJson(String url, HashMap<String, String> params, Contract.OkhttpCallBack okhttpCallBack) {
        OkHttpUtils.getmInstance().doPost(url,params,okhttpCallBack);
    }
}
