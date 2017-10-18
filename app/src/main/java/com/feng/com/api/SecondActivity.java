package com.feng.com.api;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.mkit.libmkit.ui.fragment.Mkit_MainFragment;



public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Mkit_MainFragment fragment=new Mkit_MainFragment();
        ft.replace(R.id.content_fragment,fragment);
        ft.commit();
    }

}
