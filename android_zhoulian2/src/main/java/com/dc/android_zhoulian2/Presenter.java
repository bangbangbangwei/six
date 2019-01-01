package com.dc.android_zhoulian2;

import java.util.HashMap;

public class Presenter {
    Model model;
    Contract.IJsonView iJsonView;

    public Presenter(Contract.IJsonView iJsonView) {
        this.model = new Model();
        this.iJsonView = iJsonView;
    }
    public void goJson(HashMap<String,String> params,String url){
        model.getJson(url, params, new Contract.OkhttpCallBAck() {
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
