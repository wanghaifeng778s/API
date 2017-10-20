package com.mkit.libmkit.http;

import android.content.Context;

import com.google.gson.Gson;
import com.mkit.libmkit.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

//import com.google.gson.Gson;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class HttpService {
//http://47.88.188.120:8170
    public static final String BASE_URL= BuildConfig.DE_BUG?"http://47.88.188.120:8170":"http://47.88.188.120:8170";

    public static final int TIME_OUT=10;

    private static Retrofit retrofit;

    private static void getRetrofit(final Context mContext){
        Timber.plant(new Timber.DebugTree());
        OkHttpClient client= null;
        try {
            client = new OkHttpClient.Builder()
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return new HttpHandler(mContext).onRequest(chain);
                        }
                    })
                    .addNetworkInterceptor(new RequestInterceptor(new HttpHandler(mContext)))
//                    .sslSocketFactory(MySSLSocketFactory.getSSLSocketFactoryT(mContext))
//                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public static <T> T create(Class<T> service,Context context){
        if (retrofit == null) {
            getRetrofit(context);
        }
        return retrofit.create(service);
    }

}
