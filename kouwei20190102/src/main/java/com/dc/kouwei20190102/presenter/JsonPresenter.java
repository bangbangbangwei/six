package com.dc.kouwei20190102.presenter;

import com.dc.kouwei20190102.contract.Contract;
import com.dc.kouwei20190102.model.JsonModel;

import java.util.HashMap;

public class JsonPresenter {
    Contract.iJsonView iJsonView;
    JsonModel jsonModel;

    /**
     * 无惨构造
     * @param iJsonView
     */
    public JsonPresenter(Contract.iJsonView iJsonView) {
        this.jsonModel = new JsonModel();
        this.iJsonView = iJsonView;
    }

    /**
     * 获取解析成功的字符串
     * @param url
     * @param params
     */
    public void goJson(String url, HashMap<String,String> params){
        jsonModel.getJson(url, params, new Contract.OkhttpCallBack() {
            @Override
            public void success(String res) {
                iJsonView.success(res);
            }

            @Override
            public void fail(String string) {
                iJsonView.fail(string);
            }
        });
    }
}
