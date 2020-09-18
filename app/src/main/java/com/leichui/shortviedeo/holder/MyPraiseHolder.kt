package com.leichui.shortviedeo.holder

import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.bean.MyFocusBean
import com.leichui.shortviedeo.bean.MyPraiseBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R


class MyPraiseHolder(parent: ViewGroup) : BaseViewHolder<MyPraiseBean.DataBean>(parent, R.layout.item_focus) {

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

    override fun setData(data: MyPraiseBean.DataBean) {
        focusHead.setImageURI(data.user_avatar)
        tv1.text = data.user_name
        tv2.text = "赞了你的作品  " + data.createtime

        if(data.is_followed == "0"){//（0都没有关注对方，1对方关注我，我回关，2相互关注3我关注对方，对方没有关注我）
            btn1.text = "+关注"
            btn1.setBackgroundResource(R.drawable.red_cornor_bg)
            btn1.setOnClickListener {
                ApiMapper.addUserFollow(TCUserMgr.getInstance().userToken, data.user_id, object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java, false, true, true) {
                    override fun onSuccess2Bean(bean: BaseBean) {
                        data.is_followed = "3"
                        btn1.text = "已关注"
                        btn1.setOnClickListener(null)
                        btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
                    }
                })
            }
        }else if(data.is_followed == "1"){
            btn1.setOnClickListener {
                ApiMapper.addUserFollow(TCUserMgr.getInstance().userToken, data.user_id, object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java, false, true, true) {
                    override fun onSuccess2Bean(bean: BaseBean) {
                        data.is_followed = "2"
                        btn1.text = "互相关注"
                        btn1.setOnClickListener(null)
                        btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
                    }
                })
            }
        }else if(data.is_followed == "2"){
            btn1.text = "互相关注"
            btn1.setOnClickListener(null)
            btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
        }else if(data.is_followed == "3"){
            btn1.text = "已关注"
            btn1.setOnClickListener(null)
            btn1.setBackgroundResource(R.drawable.grey_xiao_yuanjiao2)
        }
    }


}