package com.mkit.libmkit.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkit.libmkit.R;
import com.mkit.libmkit.api.API;
import com.mkit.libmkit.bean.HolgaResultDetail;
import com.mkit.libmkit.helper.SwipeHelper;
import com.mkit.libmkit.utils.CSSUtil;
import com.mkit.libmkit.utils.CompleteDate;
import com.mkit.libmkit.utils.MyDateUtils;
import com.mkit.libmkit.utils.ReplaceHtmlTag;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WebActivity extends FragmentActivity {

    private ImageView img_back;
    private ImageView img_author;
    private TextView tv_author;
    private TextView tv_data;
    private WebView webView;
    private Context mContext;
    private String mContent;
    private boolean no_pic = false;
    private String linkCss;
    private String css;
    private String fin;
    private List<String> importImage;
    private List<String> importW;
    private String cssname;
    private String mTitle;
    private String mAtime;
    private String mDomain;
    private String mUrl;
    private List<String> mImages;
    private String sid;
    private String sImgStr;
    private int cId;
    private String tid;
    private String subscribeJson;

    public static final String MTITLE = "title";
    public static final String MATIME = "time";
    public static final String MDOMAIN = "domain";
    public static final String MURL = "url";
    public static final String SID = "id";
    public static final String CID = "cid";
    public static final String SIMGSTR = "imgs";
    public static final String UUID = "uuid";
    public static final String SUBSCRIBEJSON = "subscribeJson";
    private ImageView defaultView;
    private LinearLayout network_error;
    private ImageView refresh;
    private String headimage;
    private String subName;
    private RelativeLayout lay_defaultView;

    private SwipeHelper mSwipeHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webdetail_activity);

        /*
        滑动退出
         */
        mSwipeHelper = new SwipeHelper(this);
        mSwipeHelper.onActivityCreate();
        mSwipeHelper.setSwipeEdge(ViewDragHelper.EDGE_LEFT);

        mContext = this;
        inItData();
        inItView();
        webSetSet();
        getDetailData();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeHelper.onPostCreate();
    }

    /*
       View 初始化
        */
    private void inItView() {
        img_back = ((ImageView) findViewById(R.id.img_detail_back));
        img_author = ((ImageView) findViewById(R.id.img_author));
        refresh = ((ImageView) findViewById(R.id.refresh));
        defaultView = ((ImageView) findViewById(R.id.defaultView));

        tv_author = ((TextView) findViewById(R.id.tv_author_name));
        tv_data = ((TextView) findViewById(R.id.tv_data));
        webView = ((WebView) findViewById(R.id.web_view));
        network_error = ((LinearLayout) findViewById(R.id.network_error));
        lay_defaultView = ((RelativeLayout) findViewById(R.id.lay_defaultView));

        Glide.with(mContext)
                .load(R.raw.rozbuzz)
                .asGif()
                .into(defaultView);
        Glide.with(mContext)
                .load(headimage)
                .into(img_author);
        tv_author.setText(mDomain);
        tv_data.setText(CompleteDate.CurTime(mAtime));
        webView.setScrollContainer(false);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetailData();
            }
        });

    }

    /*
    获取传值数据
     */
    private void inItData() {
        cssname = "holga.css";
        mTitle = getIntent().getStringExtra(MTITLE);
        mAtime = getIntent().getStringExtra(MATIME);
        mDomain = getIntent().getStringExtra(MDOMAIN);
        mUrl = getIntent().getStringExtra(MURL);
        sid = getIntent().getStringExtra(SID);
        tid = getIntent().getStringExtra(UUID);
        cId = getIntent().getIntExtra(CID, 0);
        sImgStr = getIntent().getStringExtra(SIMGSTR);
        subscribeJson = getIntent().getStringExtra(SUBSCRIBEJSON);

        try {
            /*
            获取头条号信息
             */
            JSONObject subJson = new JSONObject(subscribeJson);
            headimage = subJson.getString("headimage");
            subName = subJson.getString("name");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    获取详情页数据
     */
    private void getDetailData() {
        API.getComMkit(mContext).getDetailArticle("readsubs",
                tid, cId, "0", "0", "0", "1000", 0).enqueue(new Callback<HolgaResultDetail>() {
            @Override
            public void onResponse(Call<HolgaResultDetail> call, Response<HolgaResultDetail> response) {
                if (response.body() != null && response.body().page.holgaDetails.size() != 0) {
                    network_error.setVisibility(View.GONE);
                    HolgaResultDetail body = response.body();
                    mContent = body.page.holgaDetails.get(0).getTcontent();
                    loadWeb();
                } else {
                    lay_defaultView.setVisibility(View.GONE);
                    network_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<HolgaResultDetail> call, Throwable t) {
                lay_defaultView.setVisibility(View.GONE);
                network_error.setVisibility(View.VISIBLE);
            }
        });
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void webSetSet() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                lay_defaultView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    private void loadWeb() {
        if (no_pic) {
            linkCss = "<link rel=\"stylesheet\" href=\"file:///android_asset/holgaNopic.css\" type=\"text/css\">";
        } else {
            linkCss = "<link rel=\"stylesheet\" href=\"file:///android_asset/holga.css\" type=\"text/css\">";
        }

        String con = "</header>" + mContent + "</body><script>" + "document.body.style.lineHeight=1.5" + "</script>" + "</html>";

        if (no_pic) {
            css = new StringBuffer().insert(0, new StringBuffer("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<head>")
                    .append("<style type='text/css'>" + CSSUtil.getNopicCssString(this) + "</style>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\">")).toString();
        } else {
            css = new StringBuffer().insert(0, new StringBuffer("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<head>")
                    .append("<style type='text/css'>" + CSSUtil.getCssString(this, cssname) + "</style>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\">")).toString();
        }

        linkCss = formatHtml(linkCss, mTitle, "", "");

        fin = new StringBuilder(css).append(linkCss).append(con).toString();

        try {
            JSONArray jsonArray = new JSONArray(sImgStr);
            mImages = new ArrayList<>();
            importImage = new ArrayList<>();
            importW = new ArrayList<>();
            List<Integer> list = new ArrayList();
            for (int i = 0; i < jsonArray.length(); i++) {
             /*
             详情页中图片个数用于显示填充默认图
             */
                mImages.add(jsonArray.get(i).toString());
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                importW.add(jsonObject.getString("w"));
                importImage.add(jsonObject.getString("h"));

                String url = jsonObject.getString("url");
                if (url.substring(url.length() - 4, url.length()).contains("gif")) {
                    list.add(1);
                } else {
                    list.add(0);
                }
            }
            fin = ReplaceHtmlTag.replaceSampleSrcs(fin, mImages, importImage, importW, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        webView.loadDataWithBaseURL(fin + "", fin + "", "text/html", "utf-8", mUrl);
    }

    private static String formatHtml(String contentStr, String mTitle, String mAtime, String mDomain) {
        StringBuffer sb = new StringBuffer(contentStr);
        sb.insert(0, "<div class=\"content\">");
        sb.append("</div></div></body></html>");
        sb.insert(0, new StringBuilder("</head><body><div class=\"container\"><div class=\"title\">")
                .append(mTitle)
                .append("</div><div class=\"info\">")
                .append(mDomain == null ? "" : "<span  class=\"domain\">" + mDomain + "</span>")
                .append(MyDateUtils.stringTimeFormat(mAtime) == null ? "" : "<span class=\"post_time\" >" + CompleteDate.CurTime(mAtime) + "</span >")
                .append("</div>")
                .toString());
        return sb.toString();
    }


}
