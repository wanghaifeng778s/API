package com.mkit.libmkit.mkitui.fragment;

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
import com.mkit.libmkit.mkitapi.API;
import com.mkit.libmkit.mkitbase.BaseAdapter;
import com.mkit.libmkit.mkitbase.BaseFragment;
import com.mkit.libmkit.mkitbean.HolgaItem;
import com.mkit.libmkit.mkitbean.HolgaResult;
import com.mkit.libmkit.mkitbean.IHolgaItem;
import com.mkit.libmkit.mkitui.Mkit_WebActivity;
import com.mkit.libmkit.mkitui.adapter.NewsListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WHF.Javas on 2017/10/10.
 */

public class Mkit_HomeListFragment extends BaseFragment implements OnRefreshListener, OnRefreshLoadmoreListener, BaseAdapter.OnViewClickListener {

    private SmartRefreshLayout refreshLayout;
    private ListView listView;
    private NewsListAdapter listAdapter;
    private boolean isViewFirstPrepared;
    public static final String FLAG = "flag";
    private int flag;
    private RelativeLayout notify_view;
    private TextView notify_view_text;
    private LinearLayout network_error;


    @Override
    protected void lazyLoad() {
    }

    public static Mkit_HomeListFragment getInstance(int flag) {
        Bundle bundle = new Bundle();
        bundle.putInt(Mkit_HomeListFragment.FLAG, flag);
        Mkit_HomeListFragment mkitHomeListFragment = new Mkit_HomeListFragment();
        mkitHomeListFragment.setArguments(bundle);
        return mkitHomeListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flag = getArguments().getInt(FLAG, 0);
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
        notify_view_text = (TextView) view.findViewById(R.id.notify_view_text);
        notify_view = ((RelativeLayout) view.findViewById(R.id.notify_view));
        network_error = ((LinearLayout) view.findViewById(R.id.network_error));

        listAdapter = new NewsListAdapter(mContext);
        listAdapter.setOnViewClickListener(this);
        listView.setAdapter(listAdapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnRefreshLoadmoreListener(this);

        refreshLayout.setRefreshHeader(new BezierRadarHeader(mContext));
        refreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setPrimaryColors(R.color.galry);
        refreshLayout.setReboundDuration(100);//回弹动画时长（毫秒）
        if (flag == 0) {//第一个fragment 出现调用自动刷新
            isViewFirstPrepared = true;
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
        if (refreshLayout != null && !isViewFirstPrepared) {
            refreshLayout.autoRefresh();
            isViewFirstPrepared = true;
        }
    }

    private void getData(final int count) {
        API.getComMkit(mContext).getTestData().enqueue(new Callback<HolgaResult>() {
            @Override
            public void onResponse(Call<HolgaResult> call, Response<HolgaResult> response) {
                if (response.body() != null && response.body().page != null
                        && response.body().page.holgaItems != null
                        && response.body().page.holgaItems.size() > 0) {
                    Log.d("*****", "onResponse: "+"SSSSSSSSSSSS_1");
                    List<IHolgaItem> holgaItems = new ArrayList<>();
                    holgaItems.addAll(response.body().page.holgaItems);
                    int size = holgaItems.size();
                    boolean rFlag=false;
                    if (listAdapter.getData().size() != 0) {
                        if (count == -1) {
                            rFlag=true;
                            size--;
                        }
                    }
                    showNotice(0,size + "");
                    listAdapter.notifyDataSetChanged(count, holgaItems,rFlag);
                    network_error.setVisibility(View.GONE);

                } else {
                    Log.d("*****", "onResponse: "+"SSSSSSSSSSSS_2"+response.body().itemMode);
                    showNotice(1, getResources().getString(R.string.nomore));
                    if (listAdapter.getData().size() == 0) {
                        network_error.setVisibility(View.VISIBLE);
                    }
                }


                /*
                无论上拉还是下拉全都停止刷新状态
                 */
                if (count == -1) {
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishLoadmore();
                }

            }

            @Override
            public void onFailure(Call<HolgaResult> call, Throwable t) {
                Log.d("*****", "onResponse: "+"FFFFFFFFFFFFFF");
                if (listAdapter.getData().size() == 0) {
                    network_error.setVisibility(View.VISIBLE);
                }
                showNotice(1, getResources().getString(R.string.nomore));
                if (count == -1) {
                    refreshLayout.finishRefresh();
                } else {
                    refreshLayout.finishLoadmore();
                }
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData(-1);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(listAdapter.getCount());
    }

    @Override
    public void onViewClick(View view, int position) {
        IHolgaItem iholgaItem = listAdapter.getData().get(position);
        if (iholgaItem instanceof HolgaItem) {
            HolgaItem holgaItem = (HolgaItem) iholgaItem;
            int i = view.getId();
            if (i == R.id.style1 || i == R.id.style2) {
                Intent intent = new Intent(mContext, Mkit_WebActivity.class);
                intent.putExtra(Mkit_WebActivity.MATIME, holgaItem.getAtime());
                intent.putExtra(Mkit_WebActivity.MDOMAIN, holgaItem.getDomain());
                intent.putExtra(Mkit_WebActivity.MTITLE, holgaItem.getTitle());
                intent.putExtra(Mkit_WebActivity.MURL, holgaItem.getUrl());
                intent.putExtra(Mkit_WebActivity.SIMGSTR, holgaItem.getImportImage());
                intent.putExtra(Mkit_WebActivity.SID, holgaItem.getSid());
                intent.putExtra(Mkit_WebActivity.CID, holgaItem.getCid());
                intent.putExtra(Mkit_WebActivity.UUID, holgaItem.getUuid());
                intent.putExtra(Mkit_WebActivity.SUBSCRIBEJSON, holgaItem.getSubscribeJson());
                mContext.startActivity(intent);

            }
        } else {
            refreshLayout.autoRefresh();
            listView.setSelectionAfterHeaderView();
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
