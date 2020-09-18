package com.leichui.shortviedeo.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.tencent.qcloud.xiaoshipin.R;

import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;
import uk.co.senab.photoview.PhotoView;


/**
 * Created by 60288 on 2018/8/31.
 */

public class MyViewPagerAdapter2 extends PagerAdapter {

    List<String> imgs;

    Context mContext;

    public MyViewPagerAdapter2(Context context, List<String> imgs) {

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
        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.img_browse, null);
        PhotoDraweeView img = (PhotoDraweeView) view.findViewById(R.id.photo_view);
        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();

        controller.setUri(imgUrl);//设置图片url
        controller.setOldController(img.getController());
        controller.setControllerListener(new BaseControllerListener<ImageInfo>(){

            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null || img == null) {
                    return;
                }
                img.update(imageInfo.getWidth(), imageInfo.getHeight());
            }
        });
        img.setController(controller.build());



        ((ViewPager) container).addView(view);

        return view;

    }
}