package com.dc.android_mvp_one.net;

import com.dc.android_mvp_one.bean.HttpBean;

public interface ResponseCallBack {
    void success(HttpBean httpBean);
    void fail(String string);
}
