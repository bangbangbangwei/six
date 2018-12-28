package com.dc.android_mvp_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dc.android_mvp_one.bean.HttpBean;
import com.dc.android_mvp_one.contract.ILogincontract;
import com.dc.android_mvp_one.presenter.ILoginPersenter;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity implements ILogincontract.IloginView {

    private EditText mobile;
    private EditText password;
    private ILoginPersenter iLoginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        iLoginPersenter = new ILoginPersenter(this);
    }

    public void reg(View view) {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile",mobile.getText().toString());
        map.put("password",password.getText().toString());
         iLoginPersenter.reg(map);
    }

    @Override
    public void success(HttpBean httpBean) {
        Toast.makeText(this, httpBean.getMsg()+"", Toast.LENGTH_SHORT).show();
        if (httpBean.getMsg().equals("注册成功")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void fail(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileerror(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
