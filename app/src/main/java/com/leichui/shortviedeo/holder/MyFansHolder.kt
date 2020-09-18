package com.leichui.shortviedeo.holder

import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.bean.MyFocusBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R


class MyFansHolder(parent: ViewGroup) : BaseViewHolder<MyFocusBean.DataBean>(parent, R.layout.item_focus) {

    val tv1: TextView
    val tv2: TextView
    val btn1: TextView
    val focusHead:SimpleDraweeView
    init {
        tv1 = itemView.findViewById(R.id.tv1) as TextView
        tv2 = itemView.findViewById(R.id.tv2) as TextView
        btn1 = itemView.findViewById(R.id.btn1) as TextView
        focusHead = itemView.findViewById(R.id.focusHead) as SimpleDraweeView
    }

    override fun setData(data: MyFocusBean.DataBean) {
        focusHead.setImageURI(data.user_follow_avatar)
        tv1.text = data.user_follow_name
        tv2.text = "关注了你  " + data.createtime

        if(data.is_followed == "1"){//（0没有，1相互关注，2已关注）
            btn1.text = "互相关注"
            btn1.setOnClickListener(null)
            btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
        }else{
            btn1.text = "回关"
            btn1.setBackgroundResource(R.drawable.red_cornor_bg)
            btn1.setOnClickListener {
                ApiMapper.addUserFollow(TCUserMgr.getInstance().userToken, data.user_follow_id, object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java, false, true, true) {
                    override fun onSuccess2Bean(bean: BaseBean) {
                        btn1.text = "互相关注"
                        btn1.setOnClickListener(null)
                        btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
                    }
                })
            }
        }

    }


}