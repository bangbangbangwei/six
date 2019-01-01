package com.dc.android_zhoulian3;

import java.util.HashMap;

public class Presenter {
    Contract.IJsonView iJsonView;
    Model model;
    public Presenter(Contract.IJsonView iJsonView){
        this.model = new Model();
        this.iJsonView = iJsonView;
    }
    public void gojson(String url, HashMap<String,String> params){
        model.getJson(url, params, new Contract.OkHttpCallBack() {
            @Override
            public void success(String res) {
                iJsonView.success(res);
            }

            @Override
            public void fail(String meg) {
                iJsonView.fail(meg);
            }
        });
    }
}
