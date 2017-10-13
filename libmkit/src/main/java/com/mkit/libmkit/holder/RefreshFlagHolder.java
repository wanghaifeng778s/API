package com.mkit.libmkit.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mkit.libmkit.R;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItemFlag;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class RefreshFlagHolder extends BaseViewHolder<HolgaItemFlag> implements View.OnClickListener {

    public RefreshFlagHolder(Context context, ViewGroup parent) {
        super(context, parent);

    }
    @Override
    public void inItView(View view) {
        view.findViewById(R.id.lastreaditem).setOnClickListener(this);
    }
    @Override
    public void bindHolder(HolgaItemFlag dataModel) {
    }
    @Override
    public int getLayoutId() {
        return R.layout.lastread_item;
    }

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }
}
