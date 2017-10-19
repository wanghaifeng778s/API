package com.mkit.libmkit.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkit.libmkit.R;
import com.mkit.libmkit.base.BaseViewHolder;
import com.mkit.libmkit.bean.HolgaItem;
import com.mkit.libmkit.utils.CompleteDate;


/**
 * Created by WHF.Javas on 2017/8/22.
 */

public class NewsThreeImgHolder extends BaseViewHolder<HolgaItem> implements View.OnClickListener {

    private TextView title;
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;
    private LinearLayout viewById;
    private TextView item_sub_time;
    private TextView item_sub_name;

    public NewsThreeImgHolder(Context context, ViewGroup parent) {
        super(context, parent);

    }
    @Override
    public void inItView(View view) {
        title = (TextView) view.findViewById(R.id.tv_t_title);
        item_sub_time = (TextView) view.findViewById(R.id.item_sub_time);
        item_sub_name = (TextView) view.findViewById(R.id.item_sub_name);
        imgView1 = (ImageView) view.findViewById(R.id.img_t_1);
        imgView2 = (ImageView) view.findViewById(R.id.img_t_2);
        imgView3 = (ImageView) view.findViewById(R.id.img_t_3);
        viewById = ((LinearLayout) view.findViewById(R.id.style2));
        viewById.setOnClickListener(this);
    }
    @Override
    public void bindHolder(HolgaItem dataModel) {
        title.setText(dataModel.getTitle());
        item_sub_name.setText(dataModel.getAuthor().getName());
        item_sub_time.setText(CompleteDate.CurTime(dataModel.getAddtime()+""));
        String imgUrl1 = dataModel.getImages().get(0);
        String imgUrl2 = dataModel.getImages().get(1);
        String imgUrl3 = dataModel.getImages().get(2);
        Glide.with(mContext)
                .load(imgUrl1)
//                .override(screenWidth,screenHeight)
                .placeholder(R.drawable.placehold)
                .dontAnimate()
                .into(imgView1);
        Glide.with(mContext)
                .load(imgUrl2)
//                .override(screenWidth,screenHeight)
                .placeholder(R.drawable.placehold)
                .dontAnimate()
                .into(imgView2);
        Glide.with(mContext)
                .load(imgUrl3)
//                .override(screenWidth,screenHeight)
                .placeholder(R.drawable.placehold)
                .dontAnimate()
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
