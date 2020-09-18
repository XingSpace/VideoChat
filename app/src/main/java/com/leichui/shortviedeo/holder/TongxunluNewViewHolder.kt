package com.leichui.shortviedeo.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.TongxunluMyNewBean
import com.tencent.qcloud.xiaoshipin.R


/**
 * Created by Administrator on 2017/7/31.
 */

class TongxunluNewViewHolder(parent: ViewGroup) : BaseViewHolder<TongxunluMyNewBean.DataBean>(parent, R.layout.friend_new_item_lin) {
    val friend_type: TextView
    val lin: LinearLayout

    init {
        friend_type = itemView.findViewById(R.id.friend_type) as TextView
        lin = itemView.findViewById(R.id.lin) as LinearLayout
    }

    override fun setData(data: TongxunluMyNewBean.DataBean) {
        super.setData(data)
        friend_type.text = data.key_word
        lin.removeAllViews()

        for (i in data.list) {
            addView(i)
        }
//        val name: TextView
//        val num: TextView
//
//
//        name = itemView.findViewById<TextView>(R.id.food_name)
//        num = itemView.findViewById<TextView>(R.id.food_num)
//        name.text=data.food_name
//        num.text=data.sum
    }

    fun addView(bean1: TongxunluMyNewBean.DataBean.ListBean) {
        val view = LayoutInflater.from(context).inflate(R.layout.friend_new_item, null)
        val name = view.findViewById(R.id.tv1) as TextView
        val num = view.findViewById(R.id.tv2) as TextView
        val img = view.findViewById(R.id.img) as ImageView

//        name.text = bean1.user_name
//        img.setImageURI(IURL + bean1.avatar)

        if (bean1.is_chat.equals("0")) {
            num.text = "我是" + bean1.remarks + ",  " + "正在使用黄金海岸"

        } else {
            if(bean1.remarks.equals("")){
                num.text = bean1.user_tel
            }else{
                num.text = "手机通讯录:" + bean1.remarks
            }
//            num.text = "通讯录:" + bean1.remarks
//            num.text = bean1.user_tel
            view.setOnClickListener {
//                RongIM.getInstance().startPrivateChat(context, bean1.rong_UserId, bean1.user_name)
            }
        }


        lin.addView(view)
    }


}
