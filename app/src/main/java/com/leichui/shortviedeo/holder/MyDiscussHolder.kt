package com.leichui.shortviedeo.holder

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.MyDiscussBean
import com.tencent.qcloud.xiaoshipin.R


class MyDiscussHolder(parent: ViewGroup) : BaseViewHolder<MyDiscussBean.DataBean>(parent, R.layout.item_mydiscuss) {

    val tv1: TextView
    val tv2: TextView
    val tv3: TextView
    val tvHy: TextView
    val discussHead:SimpleDraweeView
    val img:SimpleDraweeView

    init {
        tv1 = itemView.findViewById(R.id.tv1) as TextView
        tv2 = itemView.findViewById(R.id.tv2) as TextView
        tv3 = itemView.findViewById(R.id.tv3) as TextView
        tvHy = itemView.findViewById(R.id.tvHy) as TextView
        discussHead = itemView.findViewById(R.id.discussHead) as SimpleDraweeView
        img = itemView.findViewById(R.id.img) as SimpleDraweeView
    }

    override fun setData(data: MyDiscussBean.DataBean) {
        tv1.text = data.user_name
        tv2.text = data.video_discuss
        tv3.text = "评论了你的作品   " +  data.createtime
        discussHead.setImageURI(data.user_avatar)
        img.setImageURI(data.video_img_url)
        if(data.is_friend == "1"){
            tvHy.visibility = View.VISIBLE
        }else{
            tvHy.visibility = View.GONE
        }
    }


}