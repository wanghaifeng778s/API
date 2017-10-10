package com.mkit.libmkit.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkit.libmkit.R;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItem;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsOneImgHolder extends BaseViewHolder<HolgaItem> {

    private TextView title;
    private ImageView imgView;

    public NewsOneImgHolder(Context context, ViewGroup parent) {
        super(context, parent);

    }
    @Override
    public void inItView(View view) {
        title = (TextView) view.findViewById(R.id.title1);
        imgView = (ImageView) view.findViewById(R.id.image1);
    }
    @Override
    public void bindHolder(HolgaItem dataModel) {
        title.setText(dataModel.getTitle());
        String imgUrl = "";
        try {
            JSONArray jsonArray = new JSONArray(dataModel.getImage());
            imgUrl = jsonArray.get(0).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(mContext)
                .load(imgUrl)
                .into(imgView);
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_news_one_image;
    }
}
