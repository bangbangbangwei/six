package com.dc.android_mvp.presenter;

import com.dc.android_mvp.RequsetCallBack;
import com.dc.android_mvp.utils.ValidatorUtil;
import com.dc.android_mvp.bean.HttbBean;
import com.dc.android_mvp.model.LoginModel;
import com.dc.android_mvp.view.IloginView;

import java.util.HashMap;

public class LoginPresenter {

    private LoginModel loginModel;
    private IloginView iloginView;
    public  LoginPresenter(IloginView iloginView){
        loginModel = new LoginModel();
        this.iloginView = iloginView;
    }
    public void login (HashMap<String,String> params){
        String mobile = "18612991023";
        String password = "222222";
        if (!ValidatorUtil.isMobile(mobile)){
            if (iloginView!= null){
                iloginView.mobildeError("请输入合法的手机号");
            }
            return ;
        }
        //调用loginmodel的数据处理的方法,登录的方法
        if (loginModel!=null){
                    loginModel.login(params, new RequsetCallBack() {
                        @Override
                        public void failure(String msg) {
                            if (iloginView!= null){
                                iloginView.failure(msg);
                            }
                        }

                @Override
                public void successMsg(String msg) {
                    if (iloginView!=null){
                        iloginView.successMsg(msg);
                    }
                }

                @Override
                public void success(HttbBean bean) {
                    if (iloginView!=null){
                        iloginView.success(bean);
                    }
                }
            });
        }
    }
}
