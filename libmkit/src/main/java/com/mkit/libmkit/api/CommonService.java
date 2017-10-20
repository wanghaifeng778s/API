package com.mkit.libmkit.api;

import com.mkit.libmkit.bean.HolgaResult;
import com.mkit.libmkit.bean.NewsDetailBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public interface CommonService {

    @GET("api/user/register")
    Call<ResponseBody> GAG_register();

    @GET("api/article/pulldown")
    Call<HolgaResult> GAG_PullDown(@Query("subjects") String subjects);

    @GET("api/article/content")
    Call<NewsDetailBean> GAG_Content(@Query("tid") String tid);
}
