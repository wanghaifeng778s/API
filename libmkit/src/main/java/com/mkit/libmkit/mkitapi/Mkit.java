package com.mkit.libmkit.mkitapi;

import android.content.Context;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class Mkit {
    private static Context mContext;
    private Mkit (){}

    private static volatile Mkit instance=null;


    public static void Initialization(Context context){
        mContext=context;
    }

    public static Mkit getInstance(){
        if (instance==null) {
            synchronized (Mkit.class){
                if (instance==null) {
                    instance=new Mkit();
                }
            }
        }
        return instance;
    }

//    public  void getData(final DataCallBackBody backBody){
//        API.getComMkit(mContext).getSth().enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                backBody.dataCall(response);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                backBody.onError(t);
//            }
//        });
//    }
//    public void getNewApi(final DataHomeCallBack callBack){
//        API.getComMkit(mContext).getHolgadataNewApi("p=1080", "demo", "pulldown", 0, "0", "0", "1")
//                .enqueue(new Callback<HolgaResult>() {
//            @Override
//            public void onResponse(Call<HolgaResult> call, Response<HolgaResult> response) {
//                if (response.body() != null && response.body().page != null
//                        && response.body().page.holgaItems != null
//                        && response.body().page.holgaItems.size() > 0){
//                    callBack.dataCall(response);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<HolgaResult> call, Throwable t) {
//                callBack.onError(t);
//            }
//        });
//    }
}
