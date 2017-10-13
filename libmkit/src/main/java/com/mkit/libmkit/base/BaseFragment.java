package com.mkit.libmkit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;


/**
 * 2017/3/7 0007.
 */

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible = true;
    protected boolean isFirst = true;
    private boolean hasInitData=false;
    private String tabtitle;
    protected FragmentActivity mContext;

    public String getTabTitle() {
        return tabtitle;
    }

    public void setTabTitle(String tabtitle) {
        this.tabtitle = tabtitle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasInitData) {
            initData(view , savedInstanceState);
            hasInitData = true;
        }
    }

    protected abstract void initData(View view , Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        if (isFirst) {
//            lazyLoad();
            isFirst = false;
        }
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }
}
