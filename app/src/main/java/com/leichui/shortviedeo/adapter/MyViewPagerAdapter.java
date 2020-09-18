package com.leichui.shortviedeo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.leichui.shortviedeo.activity.ImageBrowseActivity;
import com.tencent.qcloud.xiaoshipin.R;

import java.util.ArrayList;
import java.util.List;

import static com.leichui.conghua.utils.UtKt.L;


/**
 * Created by 60288 on 2018/8/31.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    List<String> imgs;

    Context mContext;

    public MyViewPagerAdapter(Context context, List<String> imgs) {

        this.mContext = context;
        this.imgs = imgs;

    }

    @Override
    public int getCount() { // 获得size
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        String imgUrl = imgs.get(position);
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.img_browse_list, null);
        SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.photo_view);
        img.setImageURI(imgUrl);
        ((ViewPager) container).addView(view);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageBrowseActivity.class);
                intent.putExtra("position", position);
                intent.putStringArrayListExtra("imgs", (ArrayList<String>) imgs);
                mContext.startActivity(intent);
            }
        });
        return view;

    }
}