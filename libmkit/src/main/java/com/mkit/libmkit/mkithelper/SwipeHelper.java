package com.mkit.libmkit.mkithelper;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.mkit.libmkit.R;
import com.mkit.libmkit.mkitbase.BaseSwipeLayout;


/**
 * Created by Administrator on 2016/2/20 0020.
 */
public class SwipeHelper {

    private FragmentActivity mActivity;
    private BaseSwipeLayout mBaseSwipeLayout;


    public SwipeHelper(FragmentActivity activity) {
        this.mActivity = activity;
    }

    public void onActivityCreate() {
        mBaseSwipeLayout = (BaseSwipeLayout) LayoutInflater.from(mActivity)
                .inflate(R.layout.swipe_layout, null);
        mBaseSwipeLayout.setOnFinishScroll(new BaseSwipeLayout.OnFinishScroll() {
            @Override
            public void complete(int leftOrRight) {
                if (leftOrRight==0){
                    mActivity.finish();
                }
            }
        });
    }

    public void onPostCreate() {
        mBaseSwipeLayout.attachToActivity(mActivity);
    }

    public void setSwipeEdge(int edgeFlag) {
        mBaseSwipeLayout.setSwipeEdge(edgeFlag);
    }

}
