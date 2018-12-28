package com.dc.android_mvp;

import com.dc.android_mvp.bean.HttbBean;

public interface RequsetCallBack {
    void failure(String msg);//服务器请求失败,断网,服务器宕机等等因素
    void successMsg(String msg);//请求成功,但数据不正确
    void success(HttbBean bean);//数据合法
}
