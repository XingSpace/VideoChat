package com.leichui.shortviedeo.holder

import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.MyCollectionBean
import com.tencent.qcloud.xiaoshipin.R


class MyCollectionHolder(parent: ViewGroup) : BaseViewHolder<MyCollectionBean.DataBean>(parent, R.layout.item_video_collect) {

    val tv1: TextView
    val img:SimpleDraweeView
    init {
        tv1 = itemView.findViewById(R.id.tv1) as TextView
        img = itemView.findViewById(R.id.img) as SimpleDraweeView
    }

    override fun setData(data: MyCollectionBean.DataBean) {
        tv1.text = data.video_collect_count
        img.setImageURI(data.video_img_url)
    }


}