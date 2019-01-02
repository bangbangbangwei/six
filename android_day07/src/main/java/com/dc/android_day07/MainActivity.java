package com.dc.android_day07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private MyView myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //layout = findViewById(R.id.layout);
        myview = findViewById(R.id.myview);
       /* for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText(i+"");
            layout.addView(textView);
        }*/
        myview.invalidate();
        myview.postInvalidate();
    }
}
