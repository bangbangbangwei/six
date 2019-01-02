package com.dc.kouwei20190102.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dc.kouwei20190102.R;

public class LogActivity extends AppCompatActivity {

    private TextView log_name,log_price;
    private ImageView log_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 获取资源Id
         */

        setContentView(R.layout.activity_log);
        log_name = findViewById(R.id.log_name);
        log_price = findViewById(R.id.log_price);
        log_img = findViewById(R.id.log_img);
        String title = getIntent().getStringExtra("title");
        String img = getIntent().getStringExtra("img");
        String price = getIntent().getStringExtra("price");
        log_name.setText(title);
        String[] split = img.split("\\|");
        Glide.with(this).load(split[0]).into(log_img);
        log_price.setText(price);
    }
}
