package com.dc.android_mvp.view;

public interface IRegView {
    void mobileError(String error);
    void success(String result);
    void failure(String msg);
}
