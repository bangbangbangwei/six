package com.dc.android_mvp.contract;

import java.util.HashMap;

public interface RegContract {
    public abstract class RegPresenter{
        public abstract void register(HashMap<String,String> params);
        public abstract void login(HashMap<String,String> params);
    }
    interface IReModle{
        void reg(HashMap<String,String> params);
        void login(HashMap<String,String> params);
    }
    interface IRegView{
        void mobileError(String error);
        void success(String result);
        void failure(String msg);
    }
}
