package com.mkit.libmkit.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 2016/5/28.
 */
public class HolgaResultDetail {
    public String scode;
    //public JsonElement page;
    //@SerializedName("page")
    public Page page;
    public class Page{
        public String index;
        public @SerializedName("items") List<HolgaDetail> holgaDetails;

    }
}
