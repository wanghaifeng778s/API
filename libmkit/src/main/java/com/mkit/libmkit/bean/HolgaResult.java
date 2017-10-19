package com.mkit.libmkit.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 2016/5/28.
 */
public class HolgaResult implements IHolgaItem{
    @SerializedName("code")
    public String scode;

    @SerializedName("data")
    public Page page;
    public String itemMode;
    public String uid;

    public class Page {
        @SerializedName("size")
        public String index;
        public
        @SerializedName("items")
        List<HolgaItem> holgaItems;
    }
}
