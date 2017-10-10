package com.mkit.libmkit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mkit.libmkit.R;
import com.mkit.libmkit.api.API;
import com.mkit.libmkit.base.BaseFragment;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.bean.HolgaResult;
import com.mkit.libmkit.ui.adapter.NewsListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mkit.libmkit.R.id.list_home_data;

/**
 * Created by WHF.Javas on 2017/10/10.
 */

public class HomeListFragment extends BaseFragment implements OnRefreshListener, OnRefreshLoadmoreListener {

    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private NewsListAdapter listAdapter;

    @Override
    protected void lazyLoad() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = (SmartRefreshLayout)view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(list_home_data);
        listAdapter=new NewsListAdapter(mContext);
        listView.setAdapter(listAdapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnRefreshLoadmoreListener(this);

        refreshLayout.setRefreshHeader(new BezierRadarHeader(mContext));
        refreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.autoRefresh();
        getData(0);
    }

    private void getData(final int count) {
        API.getComMkit(mContext).getTestData().enqueue(new Callback<HolgaResult>() {
            @Override
            public void onResponse(Call<HolgaResult> call, Response<HolgaResult> response) {

                Log.d("onResponse***", "onResponse: " + response.body() + "++++" + response.body().page.holgaItems.size());

                if (response.body() != null && response.body().page != null
                        && response.body().page.holgaItems != null
                        && response.body().page.holgaItems.size() > 0) {
                    List<HolgaItem> holgaItems = response.body().page.holgaItems;
                    listAdapter.notifyDataSetChanged(count,holgaItems);
                }
                if (count==0){
                    refreshLayout.finishRefresh();
                }else {
                    refreshLayout.finishLoadmore();
                }

            }

            @Override
            public void onFailure(Call<HolgaResult> call, Throwable t) {
                if (count==0){
                    refreshLayout.finishRefresh();
                }else {
                    refreshLayout.finishLoadmore();
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData(0);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(listAdapter.getCount());
    }
}
