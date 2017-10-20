package com.feng.com.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mkit.libmkit.api.Mkit;


public class MainActivity extends AppCompatActivity {

    TextView viewById;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        Mkit.Initialization(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
         viewById = (TextView) findViewById(R.id.tv_show);

    }

    private void getData() {
        Intent intent=new Intent(mContext, SecondActivity.class);
        startActivity(intent);
    }
}
