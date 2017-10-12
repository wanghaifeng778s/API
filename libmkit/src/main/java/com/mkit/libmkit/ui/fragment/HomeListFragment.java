package com.mkit.libmkit.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mkit.libmkit.R;
import com.mkit.libmkit.api.API;
import com.mkit.libmkit.base.BaseAdapter;
import com.mkit.libmkit.base.BaseFragment;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.bean.HolgaResult;
import com.mkit.libmkit.ui.WebActivity;
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

/**
 * Created by WHF.Javas on 2017/10/10.
 */

public class HomeListFragment extends BaseFragment implements OnRefreshListener, OnRefreshLoadmoreListener, BaseAdapter.OnViewClickListener {

    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private NewsListAdapter listAdapter;
    private boolean isViewFirstPrepared;
    public static final String FLAG="flag";
    private int flag;
    private RelativeLayout notify_view;
    private TextView notify_view_text;
    private LinearLayout network_error;

    @Override
    protected void lazyLoad() {
    }

    public static HomeListFragment getInstance(int flag){
        Bundle bundle = new Bundle();
        bundle.putInt(HomeListFragment.FLAG, flag);
        HomeListFragment homeListFragment=new HomeListFragment();
        homeListFragment.setArguments(bundle);
        return homeListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flag = getArguments().getInt(FLAG,0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_list, container, false);
    }

    @Override
    protected void initData(View view, Bundle savedInstanceState) {
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        listView = (ListView) view.findViewById(R.id.list_home_data);
        notify_view_text=(TextView)view.findViewById(R.id.notify_view_text);
        notify_view = ((RelativeLayout) view.findViewById(R.id.notify_view));
        network_error = ((LinearLayout)view.findViewById(R.id.network_error));

        listAdapter = new NewsListAdapter(mContext);
        listAdapter.setOnViewClickListener(this);
        listView.setAdapter(listAdapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnRefreshLoadmoreListener(this);

        refreshLayout.setRefreshHeader(new BezierRadarHeader(mContext));
        refreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setPrimaryColors(R.color.galry);
        refreshLayout.setReboundDuration(100);//回弹动画时长（毫秒）
        if (flag==0){
            isViewFirstPrepared=true;
            refreshLayout.autoRefresh();
        }

        network_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayout.autoRefresh();
            }
        });
    }

        @Override
    protected void onVisible() {
        super.onVisible();
        if (refreshLayout != null&&!isViewFirstPrepared) {
            refreshLayout.autoRefresh();
            isViewFirstPrepared=true;
        }
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
                    showNotice(0,holgaItems.size()+"");
                    listAdapter.notifyDataSetChanged(count, holgaItems);
                    network_error.setVisibility(View.GONE);
                }else {
                    showNotice(1, getResources().getString(R.string.nomore));
                    if (listAdapter.getData().size()==0) {
                        network_error.setVisibility(View.VISIBLE);
                    }
                }
                if (count == 0) {
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishLoadmore();
                }

            }

            @Override
            public void onFailure(Call<HolgaResult> call, Throwable t) {
                if (listAdapter.getData().size()==0) {
                    network_error.setVisibility(View.VISIBLE);
                }
                showNotice(1, getResources().getString(R.string.nomore));
                if (count == 0) {
                    refreshLayout.finishRefresh();
                } else {
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

    @Override
    public void onViewClick(View view, int position) {
        HolgaItem holgaItem = listAdapter.getData().get(position);
        int i = view.getId();
        if (i == R.id.style1||i==R.id.style2) {
            Intent intent=new Intent(mContext, WebActivity.class);
            intent.putExtra(WebActivity.MATIME,holgaItem.getAtime());
            intent.putExtra(WebActivity.MDOMAIN,holgaItem.getDomain());
            intent.putExtra(WebActivity.MTITLE,holgaItem.getTitle());
            intent.putExtra(WebActivity.MURL,holgaItem.getUrl());
            intent.putExtra(WebActivity.SIMGSTR,holgaItem.getImportImage());
            intent.putExtra(WebActivity.SID,holgaItem.getSid());
            intent.putExtra(WebActivity.CID,holgaItem.getCid());
            intent.putExtra(WebActivity.UUID,holgaItem.getUuid());
            intent.putExtra(WebActivity.SUBSCRIBEJSON,holgaItem.getSubscribeJson());
            mContext.startActivity(intent);

        }
    }

    private void showNotice(int type, String text) {
        final TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
        mHiddenAction.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                notify_view.setVisibility(View.INVISIBLE);
            }
        });
        notify_view.setVisibility(View.VISIBLE);
        if (type == 0) {
            notify_view_text.setText(String.format(getString(R.string.ss_pattern_update), Integer.valueOf(text)));
        } else {
            notify_view_text.setText(text);
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                notify_view.startAnimation(mHiddenAction);
            }
        }, 500);

    }

}
