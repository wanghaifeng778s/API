package com.mkit.libmkit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * 2016/7/12 0012.
 */
public class NetWorkChoice {
    private static WifiInfo connectionInfo1;
    private static int ipAddress;
    private int phoneType;
    TelephonyManager telephonyManager;
    WifiManager wifiManager;
    private int networkType;
    /**
     * Network type is unknown
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * Current network is GPRS
     */
    public static final int NETWORK_TYPE_GPRS = 1;
    /**
     * Current network is EDGE
     */
    public static final int NETWORK_TYPE_EDGE = 2;
    /**
     * Current network is UMTS
     */
    public static final int NETWORK_TYPE_UMTS = 3;
    /**
     * Current network is CDMA: Either IS95A or IS95B
     */
    public static final int NETWORK_TYPE_CDMA = 4;
    /**
     * Current network is EVDO revision 0
     */
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    /**
     * Current network is EVDO revision A
     */
    public static final int NETWORK_TYPE_EVDO_A = 6;
    /**
     * Current network is 1xRTT
     */
    public static final int NETWORK_TYPE_1xRTT = 7;
    /**
     * Current network is HSDPA
     */
    public static final int NETWORK_TYPE_HSDPA = 8;
    /**
     * Current network is HSUPA
     */
    public static final int NETWORK_TYPE_HSUPA = 9;
    /**
     * Current network is HSPA
     */
    public static final int NETWORK_TYPE_HSPA = 10;
    /**
     * Current network is iDen
     */
    public static final int NETWORK_TYPE_IDEN = 11;
    /**
     * Current network is EVDO revision B
     */
    public static final int NETWORK_TYPE_EVDO_B = 12;
    /**
     * Current network is LTE
     */
    public static final int NETWORK_TYPE_LTE = 13;
    /**
     * Current network is eHRPD
     */
    public static final int NETWORK_TYPE_EHRPD = 14;
    /**
     * Current network is HSPA+
     */
    public static final int NETWORK_TYPE_HSPAP = 15;
    /**
     * Unknown network class. {@hide}
     */
    public static final String NETWORK_CLASS_UNKNOWN = "unknown";
    /**
     * Class of broadly defined "2G" networks. {@hide}
     */
    public static final String NETWORK_CLASS_2_G = "2G";
    /**
     * Class of broadly defined "3G" networks. {@hide}
     */
    public static final String NETWORK_CLASS_3_G = "3G";
    /**
     * Class of broadly defined "4G" networks. {@hide}
     */
    public static final String NETWORK_CLASS_4_G = "4G";
    private int networkClass;
    private int wifiState;
    private WifiInfo connectionInfo;
    private String wifiname;


    public static String getNetwork(Context context) {
        TelephonyManager telephonyManager;

        int networkType;


        WifiInfo connectionInfo;
        telephonyManager = (TelephonyManager)
                context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

       /*  connectionInfo = wifiManager.getConnectionInfo();*/
        networkType = telephonyManager.getNetworkType();


        return getNetworkClass(networkType);
    }

    public static int getWifi(Context context) {
        int wifiState;
        WifiManager wifiManager;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiState = wifiManager.getWifiState();
        connectionInfo1 = wifiManager.getConnectionInfo();
        if (connectionInfo1 != null) {
            ipAddress = connectionInfo1.getIpAddress();
        }
        String wifiname;

        if (wifiState == 1) {
            wifiname = "没有打开WIFI";
        } else if (wifiState == 2) {
            wifiname = "打开WIFI但没连上";

        } else if (wifiState == 3) {
            wifiname = "连上了WIFI点击";
        } else {
            wifiname = "WIFI坏了...";
        }
        return ipAddress;
    }

    public static String getNetworkname(Context context) {
        TelephonyManager telephonyManager;

        int networkType;


        WifiInfo connectionInfo;
        telephonyManager = (TelephonyManager)
               context.getSystemService(Context.TELEPHONY_SERVICE);
        networkType = telephonyManager.getNetworkType();
        Log.i("nettype", networkType + "");
        switch (networkType) {
            case NETWORK_TYPE_GPRS:
                return "NETWORK_TYPE_GPRS";
            case NETWORK_TYPE_EDGE:
                return "NETWORK_TYPE_EDGE";
            case NETWORK_TYPE_CDMA:
                return "NETWORK_TYPE_CDMA";
            case NETWORK_TYPE_1xRTT:
                return "NETWORK_TYPE_1xRTT";
            case NETWORK_TYPE_IDEN:
                return "NETWORK_TYPE_IDEN";

            case NETWORK_TYPE_UMTS:
                return "NETWORK_TYPE_UMTS";
            case NETWORK_TYPE_EVDO_0:
                return "NETWORK_TYPE_EVDO_0";
            case NETWORK_TYPE_EVDO_A:
                return "NETWORK_TYPE_EVDO_A";
            case NETWORK_TYPE_HSDPA:
                return "NETWORK_TYPE_HSDPA";
            case NETWORK_TYPE_HSUPA:
                return "NETWORK_TYPE_HSUPA";
            case NETWORK_TYPE_HSPA:
                return "NETWORK_TYPE_HSPA";
            case NETWORK_TYPE_EVDO_B:
                return "NETWORK_TYPE_EVDO_B";
            case NETWORK_TYPE_EHRPD:
                return "NETWORK_TYPE_EHRPD";
            case NETWORK_TYPE_HSPAP:
                return "NETWORK_TYPE_HSPAP";

            case NETWORK_TYPE_LTE:
                return "NETWORK_TYPE_LTE";

            default:
                return "NETWORK_TYPE_UNKNOWN";
        }
    }

    public static String getNetworkClass(int networkType) {
        switch (networkType) {
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:

                return NETWORK_CLASS_2_G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_HSPAP:
            case NETWORK_TYPE_UNKNOWN:
                return NETWORK_CLASS_3_G;
            case NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_3_G;
        }
    }

    /**
     * 当前网络是否链接
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断wifi是否有链接
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否有移动网络链接
     *
     * @param context
     * @return
     */
    public static boolean isMobile(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//移动网络
                return true;
            }
        }
        return false;
    }

    public static String getNet(Context context) {

        int wifistate = NetWorkChoice.getWifi(context);
        boolean net = NetWorkChoice.getNetwork(context).equals("2G");
        String finnet = "2";
        String mode1 = "standard";
        Log.i("net", NetWorkChoice.getNetworkname(context) + "");

        String auto = "auto";
        // mode1 = SharePrefUtil.getString(getContext(), "theme", "standard");
        if ((net && wifistate == 0 && !mode1.equals("standard") && !mode1.equals("best")) || (mode1.equals("nopic") && !auto.equals("auto") && wifistate == 0) || (net && auto.equals("auto") && wifistate == 0)) {
            //   Glide.with(context).pauseRequests();
        } else {

            if (wifistate != 0) {
                finnet = "1";
            } else {
                if (wifistate != 0) {
                    finnet = "3";
                } else {
                    finnet = "4";
                }
            }
        }
        //  Glide.with(context).resumeRequests();
        return finnet;
    }

}
