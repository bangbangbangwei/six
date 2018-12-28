package com.dc.android_mvp_one.presenter;

import com.dc.android_mvp_one.api.UserApi;
import com.dc.android_mvp_one.bean.HttpBean;
import com.dc.android_mvp_one.contract.ILogincontract;
import com.dc.android_mvp_one.contract.ILogincontract.ILoginPersenter2;
import com.dc.android_mvp_one.model.ILoginmodel;
import com.dc.android_mvp_one.net.ResponseCallBack;
import com.dc.android_mvp_one.util.ValidatorUtil;

import java.util.HashMap;

public class ILoginPersenter extends ILogincontract.ILoginPersenter2 {
    private ILoginmodel iLoginmodel;
    private ILogincontract.IloginView iloginViewl;
    public ILoginPersenter(ILogincontract.IloginView iloginView){
            iLoginmodel = new ILoginmodel();
            this.iloginViewl = iloginView;
    }
    @Override
    public void login(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            iloginViewl.mobileerror("请输入合法的手机号");
            return;
        }
        iLoginmodel.setokhttp(params, UserApi.USER_LOGIN, new ResponseCallBack() {
            @Override
            public void success(HttpBean httpBean) {
                if (httpBean!=null){
                    iloginViewl.success(httpBean);
                }
            }

            @Override
            public void fail(String string) {
                    iloginViewl.fail(string);
            }
        });
    }

    @Override
    public void reg(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            iloginViewl.mobileerror("手机号不合法");
            return;
        }
        iLoginmodel.setokhttp(params, UserApi.USER_REG, new ResponseCallBack() {
            @Override
            public void success(HttpBean httpBean) {
                if (httpBean!=null){
                    iloginViewl.success(httpBean);
                }
            }

            @Override
            public void fail(String string) {
                    iloginViewl.fail(string);
            }
        });
    }
}
