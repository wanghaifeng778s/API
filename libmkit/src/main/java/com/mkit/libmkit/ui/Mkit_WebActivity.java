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
import com.mkit.libmkit.bean.NewsDetailBean;
import com.mkit.libmkit.helper.SwipeHelper;
import com.mkit.libmkit.utils.CSSUtil;
import com.mkit.libmkit.utils.CompleteDate;
import com.mkit.libmkit.utils.GlideCircleTransform;
import com.mkit.libmkit.utils.MyDateUtils;
import com.mkit.libmkit.utils.ReplaceHtmlTag;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Mkit_WebActivity extends FragmentActivity {


    public static final String SUB_AVATAR="sub_avatar";
    public static final String SUB_NAME="sub_name";
    public static final String SUB_ADDTIME="sub_addtime";
    public static final String DETAIL_TITLE="detail_title";
    public static final String DETAIL_ID="detail_id";


    private ImageView img_back;
    private ImageView img_author;
    private TextView tv_author;
    private TextView tv_data;
    private TextView tv_detail_title;
    private WebView webView;
    private Context mContext;
    private String mContent;
    private boolean no_pic = false;
    private String linkCss;
    private String css;
    private String fin;
    private String cssname;
    private ImageView defaultView;
    private LinearLayout network_error;
    private ImageView refresh;
    private RelativeLayout lay_defaultView;

    private SwipeHelper mSwipeHelper;
    private String sub_img;
    private String sub_name;
    private String sub_addtime;
    private String detail_title;
    private String detail_id;


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
        tv_detail_title = ((TextView) findViewById(R.id.tv_detail_title));
        tv_data = ((TextView) findViewById(R.id.tv_data));
        webView = ((WebView) findViewById(R.id.web_view));
        network_error = ((LinearLayout) findViewById(R.id.network_error));
        lay_defaultView = ((RelativeLayout) findViewById(R.id.lay_defaultView));

        Glide.with(mContext)
                .load(R.raw.rozbuzz)
                .into(defaultView);
        Glide.with(mContext)
                .load(sub_img)
                .transform(new GlideCircleTransform(this))
                .into(img_author);
        tv_author.setText(sub_name);
        tv_data.setText(CompleteDate.CurTime(sub_addtime));
        tv_detail_title.setText(detail_title);
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
        sub_img = getIntent().getStringExtra(SUB_AVATAR);
        sub_name = getIntent().getStringExtra(SUB_NAME);
        sub_addtime = getIntent().getStringExtra(SUB_ADDTIME);
        detail_title = getIntent().getStringExtra(DETAIL_TITLE);
        detail_id = getIntent().getStringExtra(DETAIL_ID);
    }

    /*
    获取详情页数据
     */
    private void getDetailData() {
        API.getComMkit(mContext).GAG_Content(detail_id).enqueue(new Callback<NewsDetailBean>() {
            @Override
            public void onResponse(Call<NewsDetailBean> call, Response<NewsDetailBean> response) {
                if (response.body() != null&& response.body().getData()!=null) {
                    network_error.setVisibility(View.GONE);
                    mContent = response.body().getData().getContent();
                    loadWeb();
                }else {
                    lay_defaultView.setVisibility(View.GONE);
                    network_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NewsDetailBean> call, Throwable t) {
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

//        linkCss = formatHtml(linkCss, mTitle, mAtime, mDomain);

        fin = new StringBuilder(css).append(linkCss).append(con).toString();
        fin = ReplaceHtmlTag.replaceSampleSrcs(fin);
//        try {
//            JSONArray jsonArray = new JSONArray(sImgStr);
//            mImages = new ArrayList<>();
//            importImage = new ArrayList<>();
//            importW = new ArrayList<>();
//            List<Integer> list = new ArrayList();
//            for (int i = 0; i < jsonArray.length(); i++) {
//             /*
//             详情页中图片个数用于显示填充默认图
//             */
//                mImages.add(jsonArray.get(i).toString());
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                importW.add(jsonObject.getString("w"));
//                importImage.add(jsonObject.getString("h"));
//
//                String url = jsonObject.getString("url");
//                if (url.substring(url.length() - 4, url.length()).contains("gif")) {
//                    list.add(1);
//                } else {
//                    list.add(0);
//                }
//            }
//            fin = ReplaceHtmlTag.replaceSampleSrcs(fin, mImages, importImage, importW, list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        webView.loadDataWithBaseURL("", fin + "", "text/html", "utf-8", "");
    }

    private static String formatHtml(String contentStr, String mTitle, String mAtime, String mDomain) {
        StringBuffer sb = new StringBuffer(contentStr);
        sb.insert(0, "<div class=\"content\">");
        sb.append("</div></div></body></html>");
        sb.insert(0, new StringBuilder("</head><body><div class=\"container\"><div class=\"title\">")
                .append(mTitle)
                .append("</div><div class=\"info\">")
                .append(mDomain == null ? "" : "<span  class=\"domain\">" + mDomain + "</span>")
//                .append("<Br/>")
                .append(MyDateUtils.stringTimeFormat(mAtime) == null ? "" : "<span class=\"post_time\" >" + CompleteDate.CurTime(mAtime) + "</span >")
                .append("</div>")
                .toString());
        return sb.toString();
    }


}
