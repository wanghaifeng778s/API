package com.mkit.libmkit.mkitutils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.mkit.libmkit.mkithttp.Constant;


/**
 * 2017/3/7 0007.
 */

public class CheckUtils {
    public static String checkUID2(Context context) {

        String uid = null;
        if (Constant.UID.equals("temp")) {

            if (!SharedPrefUtil.getString(context, "uid", "empty").equals("empty")) {
                uid = SharedPrefUtil.getString(context, "uid", "empty");
            } else {
                String random_uuid = DeviceUtil.getUUID();
                uid = SharedPrefUtil.getString(context, "vid", random_uuid);
                if (uid.equals(random_uuid)) {
                    SharedPrefUtil.saveString(context, "vid", random_uuid);
                    Constant.VID = random_uuid;
                }
            }
        } else {
            uid = Constant.UID;
        }
        return uid;
    }

    public static String checkUID(Context context) {
        String uid = SharedPrefUtil.getString(context, "uid", "-1");
        String tempId = getTempId(context);
        if (uid.equals("-1")) {
            SharedPrefUtil.saveString(context, "uid", tempId);
            return tempId;
        } else {
            return uid;
        }
    }

    public static void generateTempId(Context context) {
        String random_uuid = DeviceUtil.getUUID();
        SharedPrefUtil.saveString(context, "temp_id", random_uuid);
    }

    public static String getTempId(Context context) {
        return SharedPrefUtil.getString(context, "temp_id", "-1");
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getDID(Context context) {
        return SharedPrefUtil.getString(context, "did", "");
    }

    public static String getPID(Context context) {
        return SharedPrefUtil.getString(context, "pid", "");
    }
}
