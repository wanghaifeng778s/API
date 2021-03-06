package com.mkit.libmkit.http;

import android.content.Context;
import android.util.Log;

import com.mkit.libmkit.BuildConfig;
import com.mkit.libmkit.utils.CheckUtils;
import com.mkit.libmkit.utils.DeviceUtil;
import com.mkit.libmkit.utils.NetWorkChoice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


public class HttpHandler {
    private Request request;
    private HttpUrl.Builder authorizedUrlBuilder;
    private Context mContext;

    private  String X_CA_REQUEST_MODE = "X-Ca-Request-Mode";
    private  String X_Ca_Stage = "X-Ca-Stage";
    private  String X_Ca_Key = "X-Ca-Key";
    private  String Accept = "Accept";
    private  String X_Ca_Timestamp = "X-Ca-Timestamp";
    private  String X_Ca_Signature = "X-Ca-Signature";
    private  String X_Ca_Signature_Headers = "X-Ca-Signature-Headers";
    public   String TAG = "HttpHandler-----";
    private  String APP_SECRET = BuildConfig.APP_SECRET;

    private  String h1="debug";
    private  String h2="TEST";
    private  String h3= BuildConfig.KEY;
    private  String h4="application/json";
    private String h5="";
    private  String h6=" X-Ca-Timestamp,X-Ca-Request-Mode,X-Ca-Stage,X-Ca-Key";

    private String mkit_did= "";


    Response onResponse(String httpResult, Interceptor.Chain chain, Response response) {
        return response;
    }
    public HttpHandler(Context context) {
        mContext = context;
        mkit_did= DeviceUtil.getDeviceInfo(mContext);
    }
    Response onRequest(Interceptor.Chain chain) throws IOException {
        h5= String.valueOf(System.currentTimeMillis());
        request = chain.request();
        authorizedUrlBuilder = request.url().newBuilder()
                .addQueryParameter("uid", CheckUtils.checkUID(mContext))//用户ID
                .addQueryParameter("pid", CheckUtils.getPID(mContext))//用户通行证ID
                .addQueryParameter("did", mkit_did)//设备号
                .addQueryParameter("net", NetWorkChoice.getNet(mContext))//网络状况
                .addQueryParameter("appname", BuildConfig.APP_NAME)//appname
                .addQueryParameter("dcid", "2000")//渠道号(小米:20000)
                .addQueryParameter("mos", "1")//用户终端系统(0:IOS,1:Android)
                .addQueryParameter("appversion", BuildConfig.VERSION_NAME)//版本
                .addQueryParameter("lang", "1")//用户语言（可选值 0:English,1:Hindi,2:Marathi,3:Tamil）
//                .addQueryParameter("subjects", "weird_fun,adult_jokes,meme,gif,funny_animals,funny_fails,jokes,funny_pictures,funny_trolls,funny_cartoons")//主题号逗号分隔
//                .addQueryParameter("cid", "32")//频道
//                .addQueryParameter("strategy", "32")//策略 1 频道 2 主题 3 主题下频道
//                .addQueryParameter("pmode", "1")//机型
                .scheme(request.url().scheme())
                .host(request.url().host());

        request = request.newBuilder()
//                .header(X_CA_REQUEST_MODE, h1)
//                .header(X_Ca_Stage, h2)
//                .header(X_Ca_Key, h3)
//                .header(Accept, h4)
//                .header(X_Ca_Timestamp, h5)
//                .header(X_Ca_Signature_Headers, h6)
//                .header(X_Ca_Signature, getSign())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(request);
    }

    private String getSign() {
        HashMap<String, String> urlParams = getUrlParams(authorizedUrlBuilder.build());
        HashMap<String, String> headerParams = getHeaderParams();
        String path = authorizedUrlBuilder.build().encodedPath();
        HashMap<String, String> bodyParams = getBodyParams(request.body());
        String sign = SignUtil.sign(APP_SECRET, request.method(), path, headerParams, urlParams, bodyParams, signHeaderPrefixList());
        Log.d(TAG, "getSign-----------------" + sign + "time------" + h5 + "****" + path);
        return sign;
    }

    private HashMap<String, String> getBodyParams(RequestBody requestBody) {
        HashMap<String, String> body_params = null;
        if (requestBody != null) {
            try {
                body_params = new HashMap<>();
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(Charset.defaultCharset());
                }
                String paramsStr = buffer.readString(charset);
                Log.d(TAG, "paramsStr: " + paramsStr);
                String[] strs = paramsStr.split("&");
                for (String s : strs) {
                    String[] ms = s.split("=");
                    if (ms.length == 2) {
                        body_params.put(ms[0], ms[1]);
                    } else {
                        body_params.put(ms[0], "");
                    }
                }

            } catch (Exception e) {
                Log.d(TAG, "Exception: "+e.getMessage());
            }
        }
        return body_params;
    }

    private HashMap<String, String> getHeaderParams() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(X_CA_REQUEST_MODE, h1);
        hashMap.put(X_Ca_Stage, h2);
        hashMap.put(X_Ca_Key, h3);
        hashMap.put(Accept, h4);
        hashMap.put(X_Ca_Timestamp, h5);
        Log.d(TAG, "getHeaderParams------------" + hashMap.size());
        return hashMap;
    }

    private HashMap<String, String> getUrlParams(HttpUrl url) {
        HashMap<String, String> map = new HashMap<>();
        Set<String> strings = url.queryParameterNames();
        for (int i = 0; i < strings.size(); i++) {
            map.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return map;
    }

    private List<String> signHeaderPrefixList() {
        List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();
        CUSTOM_HEADERS_TO_SIGN_PREFIX.clear();
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Request-Mode");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Stage");
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("X-Ca-Key");
        return CUSTOM_HEADERS_TO_SIGN_PREFIX;
    }
}
