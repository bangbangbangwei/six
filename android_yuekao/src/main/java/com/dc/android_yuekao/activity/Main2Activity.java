package com.dc.android_yuekao.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dc.android_yuekao.R;

public class Main2Activity extends AppCompatActivity {
    int i = 5;
    private TextView tv_name;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            i--;
            if (i >  0){
                handler.sendEmptyMessageDelayed(1,2000);
                tv_name.setText(i+"");
            }else{
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_name = findViewById(R.id.tv_name);
        handler.sendEmptyMessageDelayed(1,2000);
    }
}
