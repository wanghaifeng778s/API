package com.mkit.libmkit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 2016/6/28 0028.
 */
public class CompleteDate {

    public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat yMd = new SimpleDateFormat("dd-MM");

    public static long unixTime(String atime) throws Exception {
        if (atime.equals("")) {
            return 0;
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

            // dateFormat.setTimeZone(getCurrentTimeZone());
            Date date = null;
            try {
                date = dateFormat.parse(atime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long unixtime = date.getTime() - TimeZone.getTimeZone("GMT+8").getRawOffset() + TimeZone.getDefault().getRawOffset();

            return unixtime;
        }
    }

    public static String CurTime(String atime) {
        if (atime.equals("")) {
            return "";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

            // dateFormat.setTimeZone(getCurrentTimeZone());
            Date date = null;
            try {
                date = dateFormat.parse(atime);
            } catch (ParseException e) {
                date = new Date(System.currentTimeMillis());
                e.printStackTrace();
            }
            long unixtime = date.getTime() - TimeZone.getTimeZone("GMT+8").getRawOffset() + TimeZone.getDefault().getRawOffset();

            String HHmmString = HHmm.format(unixtime).toString();
            String ddString = getMonDate(yMd.format(unixtime).toString());

            return ddString + " " + HHmmString;
        }
    }


    private static String getMonDate(String s) {


        String[] arr_ddMM = s.split("-");
        switch (arr_ddMM[1]) {
            case "01":
                return arr_ddMM[0] + "-Jan";
            case "02":
                return arr_ddMM[0] + "-Feb";
            case "03":
                return arr_ddMM[0] + "-Mar";
            case "04":
                return arr_ddMM[0] + "-Apr";
            case "05":
                return arr_ddMM[0] + "-May";
            case "06":
                return arr_ddMM[0] + "-Jun";
            case "07":
                return arr_ddMM[0] + "-Jul";
            case "08":
                return arr_ddMM[0] + "-Aug";
            case "09":
                return arr_ddMM[0] + "-Sep";
            case "10":
                return arr_ddMM[0] + "-Oct";
            case "11":
                return arr_ddMM[0] + "-Nov";
            case "12":
                return arr_ddMM[0] + "-Dec";
        }
        return "";
    }


}
