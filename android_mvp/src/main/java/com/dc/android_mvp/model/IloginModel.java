package com.dc.android_mvp.model;

import com.dc.android_mvp.RequsetCallBack;

import java.util.HashMap;

public interface IloginModel {
    void login(HashMap<String,String> params,RequsetCallBack requsetCallback);
}
