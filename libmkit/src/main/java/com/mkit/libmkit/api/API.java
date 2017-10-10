package com.mkit.libmkit.api;

import android.content.Context;

import com.mkit.libmkit.http.HttpService;

/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class API {
    private static CommonService commonService;
    public static CommonService getComMkit(Context mContext){
        if (commonService == null) {
            synchronized (API.class){
                if (commonService == null){
                    commonService= HttpService.create(CommonService.class,mContext);
                }
            }
        }
        return commonService;
    }
}
