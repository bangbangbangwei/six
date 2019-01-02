package com.dc.android_zhou1;

import java.util.HashMap;

public class JsonPresenter {
    Contract.iJsonView iJsonView;
    JsonModel jsonModel;

    public JsonPresenter(Contract.iJsonView iJsonView) {
        this.jsonModel = new JsonModel();
        this.iJsonView = iJsonView;
    }
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
