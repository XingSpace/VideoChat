package com.leichui.shortviedeo.holder

import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.DiscussBean
import com.tencent.qcloud.xiaoshipin.R

/**
 * Created by Administrator on 2017/7/31.
 */

class DiscussHolder(parent: ViewGroup) : BaseViewHolder<DiscussBean.DataBean.ListBean>(parent, R.layout.item_discuss) {

    val tv1: TextView
    val tv2: TextView
    val tv3: TextView
    val discussHead:SimpleDraweeView
    init {
        tv1 = itemView.findViewById(R.id.tv1) as TextView
        tv2 = itemView.findViewById(R.id.tv2) as TextView
        tv3 = itemView.findViewById(R.id.tv3) as TextView
        discussHead = itemView.findViewById(R.id.discussHead) as SimpleDraweeView
    }

    override fun setData(data: DiscussBean.DataBean.ListBean) {
        tv1.text = data.user_name
        tv2.text = data.video_discuss
        tv3.text = data.createtime
        discussHead.setImageURI(data.user_avatar)
    }


}