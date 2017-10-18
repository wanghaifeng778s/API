package com.mkit.libmkit.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mkit.libmkit.base.BaseAdapter;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.bean.HolgaItemFlag;
import com.mkit.libmkit.bean.IHolgaItem;
import com.mkit.libmkit.holder.NewsOneImgHolder;
import com.mkit.libmkit.holder.NewsThreeImgHolder;
import com.mkit.libmkit.holder.RefreshFlagHolder;

import java.util.List;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class NewsListAdapter extends BaseAdapter<IHolgaItem> {


    final int TYPE_0 = -1;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    public  final HolgaItemFlag itemFlag = new HolgaItemFlag();

    public NewsListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public BaseViewHolder getHolder(int position, ViewGroup parent) {
        BaseViewHolder baseViewHolder = null;
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_1:
                baseViewHolder = new NewsOneImgHolder(mContext, parent);
                break;
            case TYPE_2:
                baseViewHolder = new NewsThreeImgHolder(mContext, parent);
                break;
            case TYPE_0:
                baseViewHolder=new RefreshFlagHolder(mContext,parent);
        }
        return baseViewHolder;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        IHolgaItem iHolgaItem = data.get(position);
        if (iHolgaItem instanceof HolgaItem) {
            return TYPE_2;
        } else {
            return TYPE_0;
        }
    }

    public void notifyDataSetChanged(int controlData, List<IHolgaItem> list, boolean flag) {
        if (list == null) {
            return;
        }
        if (controlData < 0) {
            if (flag){
                this.data.remove(itemFlag);
                this.data.add(0,itemFlag);

                list.addAll(this.data);
                this.data=list;
            }else {
                list.addAll(this.data);
                this.data = list;
            }
        } else if (controlData == 0) {
            this.data = list;
        } else {
            this.data.addAll(list);
        }
        super.notifyDataSetChanged();
    }
}
