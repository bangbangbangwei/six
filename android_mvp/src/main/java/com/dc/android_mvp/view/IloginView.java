package com.dc.android_mvp.view;

import com.dc.android_mvp.bean.HttbBean;

public interface IloginView {
    void mobildeError(String msg);
    void pwdError(String msg);
    void failure(String msg);
    void success(HttbBean httbBean);
    void successMsg(String msg);
}
