package com.mkit.libmkit.api;

import android.content.Context;

import com.mkit.libmkit.utils.CheckUtils;
import com.mkit.libmkit.utils.SharedPrefUtil;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class Mkit {
    private Mkit (){}
    public static void Initialization(final Context mContext){

        if(!CheckUtils.checkUID(mContext).equals("-1")||!CheckUtils.getPID(mContext).equals("-1")){
            return;
        }
        API.getComMkit(mContext).GAG_register().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String registerJson = response.body().string();
                    JSONObject jsonObject=new JSONObject(registerJson);
                    JSONObject data = jsonObject.getJSONObject("data");
                    SharedPrefUtil.saveString(mContext, "pid", data.getString("pid"));
                    SharedPrefUtil.saveString(mContext, "uid",  data.getString("uid"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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
