package com.mkit.libmkit.bean;


import retrofit2.Response;

/**
 * Created by WHF.Javas on 2017/9/28.
 */

public interface DataHomeCallBack {
    void dataCall(Response<HolgaResult> bean);
    void onError(Throwable t);
}
