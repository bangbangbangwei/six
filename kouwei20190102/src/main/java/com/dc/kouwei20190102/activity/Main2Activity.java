package com.dc.kouwei20190102.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dc.kouwei20190102.R;

public class Main2Activity extends AppCompatActivity {
    int a = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this,++a+"",Toast.LENGTH_SHORT).show();
    }
}
