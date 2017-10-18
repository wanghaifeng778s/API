package com.mkit.libmkit.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;

import com.mkit.libmkit.http.StringUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by will on 16/10/19.
 */

public class DeviceUtil {

    /**
     * 获取app code
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = (context.getPackageManager().getPackageInfo(context.getPackageName(), 0)).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }



    /**
     * 获取设备语言
     *
     * @return
     */
    public static String getLang(Context context) {
        String lang = (context.getResources().getConfiguration().locale).toString();
        return StringUtil.clearString(lang);
    }

    /**
     * 手机制造商
     *
     * @return
     */
    public static String getBrand() {
        String brand = Build.MANUFACTURER;
        return StringUtil.clearString(brand);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取application标签下的meta data
     *
     * @param name
     * @return
     */
    public static String getMetaDataInApplication(String name,Context context) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString(name);
            return msg;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unknow";
        }
    }

    /**
     * 得到uuid
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuid_str = uuid.toString().replace("-", "").substring(0, 15);
        String end_string = "temp-" + uuid_str;
        return end_string;
    }

  /*  public static Context getContext() {
        return getApplicationContext();
    }*/

    /**
     * 获取进程名字
     *
     * @param pid
     * @return
     */
    public static String getProcessName(Context context, int pid) {
        ActivityManager
                am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 获取设备全名
     *
     * @return
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model == null) {
            model = "";
        }
        return StringUtil.clearString(model);
    }


    /**
     * 系统版本 version_code
     *
     * @return
     */
    public static int osVersion() {
        int SDK_INT = Build.VERSION.SDK_INT;
        return SDK_INT;
    }

    /**
     * 获取版本号
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            SharedPrefUtil.saveString(context, "ver", packageInfo.versionName);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }
        return "";
    }

    public static String getVendor(Context context) {
        TelephonyManager telMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return telMgr.getNetworkOperatorName();
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;

    }

    public static String getIp(Context context) {
         String ip = "0";
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            ip=getLocalIpAddress();
           return ip;
        }else {

            try {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                ip = intToIp(ipAddress);
            }catch (Exception e){

            }


        }


        return ip;
    }
    private static String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }

    public static String getLocalIpAddress()
    {

        {
            try {
                //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }


        return "0";
    }

    public static String getDeviceInfo(Context context) {
                String device_id = null;
                try {
                    org.json.JSONObject json = new org.json.JSONObject();
                    TelephonyManager tm = (TelephonyManager) context
                            .getSystemService(Context.TELEPHONY_SERVICE);

                    if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                        device_id = tm.getDeviceId();
                    }
                    String mac = null;
                    FileReader fstream = null;
                    try {
                        fstream = new FileReader("/sys/class/net/wlan0/address");
                    } catch (FileNotFoundException e) {
                        fstream = new FileReader("/sys/class/net/eth0/address");
                    }
                    BufferedReader in = null;
                    if (fstream != null) {
                        try {
                            in = new BufferedReader(fstream, 1024);
                            mac = in.readLine();
                        } catch (IOException e) {
                        } finally {
                            if (fstream != null) {
                                try {
                                    fstream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(device_id)) {
            device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
        }
        if (TextUtils.isEmpty(device_id)) {
            device_id = "a_" + UUID.randomUUID().toString().replaceAll("-", "");
        }
        return device_id;
    }
     /*  json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            json.put("device_id", device_id);

            Log.i("wtf", json.toString());*/
}
