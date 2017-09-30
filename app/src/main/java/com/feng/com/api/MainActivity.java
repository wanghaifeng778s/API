package com.feng.com.api;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mkit.libmkit.api.Mkit;
import com.mkit.libmkit.bean.DataCallBackBody;

import okhttp3.ResponseBody;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mkit.getBody(this, new DataCallBackBody() {
            @Override
            public void dataCall(Response<ResponseBody> response) {
                Log.d("********", "dataCall: ****************************");
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
}
