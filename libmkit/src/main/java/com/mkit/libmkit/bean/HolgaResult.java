package com.mkit.libmkit.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 2016/5/28.
 */
public class HolgaResult {
    public String scode;
    public Page page;
    public String itemMode;
    public String uid;

    public class Page {
        public String index;
        public
        @SerializedName("items")
        List<HolgaItem> holgaItems;
    }
}
