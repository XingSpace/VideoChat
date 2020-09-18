package com.leichui.shortviedeo.holder

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.ShopListBean
import com.tencent.qcloud.xiaoshipin.R


class ShangChengHolder(parent: ViewGroup) : BaseViewHolder<ShopListBean.DataBean>(parent, R.layout.item_shangcheng) {
    val img: SimpleDraweeView
    val tv1: TextView
    val tv3: TextView
    val tv4: TextView

    init {
        img = itemView.findViewById<SimpleDraweeView>(R.id.img)
        tv1 = itemView.findViewById<TextView>(R.id.tv1)
        tv3 = itemView.findViewById<TextView>(R.id.tv3)
        tv4 = itemView.findViewById<TextView>(R.id.tv4)
    }
    override fun setData(data: ShopListBean.DataBean) {
        super.setData(data)
        img.setImageURI(data.good_one_pic)
        tv1.text = data.good_name
        tv3.text = "¥ "+data.good_price
        tv4.text = "已售"+data.sell_count
    }


}
