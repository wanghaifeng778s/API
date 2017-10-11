package com.mkit.libmkit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mkit.libmkit.base.BaseAdapter;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.holder.NewsOneImgHolder;
import com.mkit.libmkit.holder.NewsThreeImgHolder;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class NewsListAdapter extends BaseAdapter<HolgaItem> {


    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseViewHolder getHolder(int position, ViewGroup parent) {
        BaseViewHolder baseViewHolder=null;
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case TYPE_1:
                baseViewHolder=new NewsOneImgHolder(mContext,parent);
                break;
            case TYPE_2:
                baseViewHolder=new NewsThreeImgHolder(mContext,parent);
                break;
        }
        return baseViewHolder;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%4==0){
            return TYPE_1;
        }
        return TYPE_2;
    }
}
