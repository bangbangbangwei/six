package com.dc.android_mvp_one.contract;

import com.dc.android_mvp_one.bean.HttpBean;
import com.dc.android_mvp_one.net.ResponseCallBack;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public interface ILogincontract {
    public abstract class ILoginPersenter2{
        public abstract void login(HashMap<String, String> map);
        public abstract void reg(HashMap<String,String> map);
    }
    public interface ILoginModel{
        void setokhttp(HashMap<String,String> map, String string, ResponseCallBack responseCallBack);
    }
    public interface IloginView{
        public void success(HttpBean httpBean);
        public void fail(String string);
        public void mobileerror(String error);
    }
}
