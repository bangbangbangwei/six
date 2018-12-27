package com.dc.android_mvp;

import java.util.HashMap;

public interface IloginModel {
    void login(HashMap<String,String> params,RequsetCallBack requsetCallback);
}
