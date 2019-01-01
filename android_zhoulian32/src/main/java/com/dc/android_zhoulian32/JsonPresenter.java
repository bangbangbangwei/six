package com.dc.android_zhoulian32;

import android.widget.ImageView;

import java.util.HashMap;

public class JsonPresenter {
    Contract.IJsonView iJsonView;
    JsonModel jsonModel;

    public JsonPresenter(Contract.IJsonView iJsonView) {
        this.iJsonView = iJsonView;
        this.jsonModel = new JsonModel();
    }
    public void goJson(String url, HashMap<String,String> params){
        jsonModel.getJson(url, params, new Contract.OkHttpCallBack() {
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
