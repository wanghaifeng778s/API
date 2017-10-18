package com.mkit.libmkit.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CSSUtil {
    /**
     * 将读取到的assets文件的流转换成字符串
     */
    public static String getCssString(Context context, String css) {
        AssetManager assets = context.getAssets();
        ByteArrayOutputStream out;
        InputStream open;
        try {
            open = assets.open(css);//"holga.css"
            out = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = open.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            open.close();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getNopicCssString(Context context) {
        AssetManager assets = context.getAssets();
        ByteArrayOutputStream out;
        InputStream open;
        try {
            open = assets.open("holgaNopic.css");
            out = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = open.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            open.close();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
