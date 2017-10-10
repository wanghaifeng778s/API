package com.mkit.libmkit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mkit.libmkit.base.BaseAdapter;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.holder.NewsOneImgHolder;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class NewsListAdapter extends BaseAdapter<HolgaItem> {
    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseViewHolder getHolder(int position, ViewGroup parent) {
        BaseViewHolder baseViewHolder=new NewsOneImgHolder(mContext,parent);
        return baseViewHolder;
    }
}
