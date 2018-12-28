package com.dc.android_mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dc.android_mvp.R;
import com.dc.android_mvp.contract.RegContract;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements RegContract.IRegView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initData();
    }

    private void initData() {

    }

    public void reg(View view) {
        HashMap<String,String> params = new HashMap<>();
        params.put("mobile","18623456789");
        params.put("password","111111");
        
    }

    @Override
    public void mobileError(String error) {

    }

    @Override
    public void success(String result) {

    }

    @Override
    public void failure(String msg) {

    }
}
