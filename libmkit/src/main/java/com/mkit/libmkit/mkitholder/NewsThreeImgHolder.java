package com.mkit.libmkit.mkitholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkit.libmkit.R;
import com.mkit.libmkit.mkitbase.BaseViewHolder;
import com.mkit.libmkit.mkitbean.HolgaItem;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsThreeImgHolder extends BaseViewHolder<HolgaItem> implements View.OnClickListener {

    private TextView title;
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private LinearLayout viewById;

    public NewsThreeImgHolder(Context context, ViewGroup parent) {
        super(context, parent);

    }
    @Override
    public void inItView(View view) {
        title = (TextView) view.findViewById(R.id.tv_t_title);
        imgView1 = (ImageView) view.findViewById(R.id.img_t_1);
        imgView2 = (ImageView) view.findViewById(R.id.img_t_2);
        imgView3 = (ImageView) view.findViewById(R.id.img_t_3);
        viewById = ((LinearLayout) view.findViewById(R.id.style2));
        viewById.setOnClickListener(this);
    }
    @Override
    public void bindHolder(HolgaItem dataModel) {
        title.setText(dataModel.getTitle());
        String imgUrl1 = "";
        String imgUrl2 = "";
        String imgUrl3 = "";
        try {
            JSONArray jsonArray = new JSONArray(dataModel.getImage());

            imgUrl1 = jsonArray.get(0).toString();
            imgUrl2 = jsonArray.get(1).toString();
            imgUrl3 = jsonArray.get(2).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(mContext)
                .load(imgUrl1)
//                .transform(new CenterCrop(mContext),
//                        new GlideRoundTransform(mContext,4))
                .placeholder(R.drawable.placehold)
                .into(imgView1);
        Glide.with(mContext)
                .load(imgUrl2)
//                .transform(new CenterCrop(mContext),
//                        new GlideRoundTransform(mContext,4))
                .placeholder(R.drawable.placehold)
                .into(imgView2);
        Glide.with(mContext)
                .load(imgUrl3)
//                .transform(new CenterCrop(mContext),
//                        new GlideRoundTransform(mContext,4))
                .placeholder(R.drawable.placehold)
                .into(imgView3);
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_news_three_image;
    }

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }
}
