package com.dc.android_mvp.presenter;

import com.dc.android_mvp.contract.RegContract;
import com.dc.android_mvp.model.RegModle;

import java.util.HashMap;

public class RegPresenter extends RegContract.RegPresenter {
    RegModle regModle;
    RegContract.IRegView iRegView;

    public RegPresenter(RegContract.IRegView iRegView) {
        this.regModle = new RegModle();
        this.iRegView = iRegView;
    }

    @Override
    public void register(HashMap<String, String> params) {

    }

    @Override
    public void login(HashMap<String, String> params) {

    }
}
