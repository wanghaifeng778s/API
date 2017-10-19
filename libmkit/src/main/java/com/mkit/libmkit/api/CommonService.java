package com.mkit.libmkit.api;

import com.mkit.libmkit.bean.HolgaResult;
import com.mkit.libmkit.bean.HolgaResultDetail;
import com.mkit.libmkit.bean.NewsDetailBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
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



    @GET("https://irrational.cn/lolu/article/pullup")
    Call<ResponseBody> getSth();

    @GET("https://openapi.rozbuzz.com/{type}/{act}")//openapi.rozbuzz.com
    Call<HolgaResult> getHolgadataNewApi(@Header("ma-header") String dis, @Path("type") String type, @Path("act") String list,
                                         @Query("cid") int number, @Query("lang") String lang, @Query("from") String from, @Query("ad") String ad);
    @GET("http://testapi3.goldenmob.com/demo/pulldown")
    Call<HolgaResult> getTestData();

    @GET("http://testapi.masala.goldenmob.com/article/{path}")
    Call<HolgaResultDetail> getDetailArticle(@Path("path") String path,
                                             @Query("tid") String tid, @Query("cid") int cid, @Query("lang") String lang,
                                             @Query("atype") String atype, @Query("stid") String stid, @Query("pf") String pf,
                                             @Query("sourceid") int sourceid);
}
