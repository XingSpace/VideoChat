package com.leichui.shortviedeo.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.bean.OrderListBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import npay.npay.yinmengyuan.fragment.adapter.OrderListAdapter


class OrderListHolder(parent: ViewGroup) : BaseViewHolder<OrderListBean.DataBean>(parent, R.layout.activity_wodedingdan_item) {

    val tv1: TextView
    val tv2: TextView
    val lin:LinearLayout
    val tv5: TextView
    var layout: LayoutInflater= LayoutInflater.from(context)
    val delete_btn:TextView
    val toPay:TextView

    init {
        tv1 = itemView.findViewById(R.id.tv1) as TextView
        tv2 = itemView.findViewById(R.id.tv2) as TextView
        tv5 = itemView.findViewById(R.id.tv5) as TextView
        delete_btn = itemView.findViewById(R.id.delete_btn) as TextView
        toPay = itemView.findViewById(R.id.toPay) as TextView
        lin = itemView.findViewById(R.id.lin) as LinearLayout
    }

    override fun setData(data: OrderListBean.DataBean) {

        tv1.text = "订单编号 ${data.order_id}"
        tv2.text=data.createtime
        tv5.text=data.order_price
        lin.removeAllViews()

        addView(data)

        if (data.pay_status.equals("0")){
            toPay.visibility = View.VISIBLE

        }else{
            toPay.visibility = View.GONE

        }
        if(data.order_status == "0"){
            delete_btn.text = "取消订单"
        }else{
            delete_btn.text = "删除订单"
        }
        delete_btn.setOnClickListener {
            ApiMapper.delOrder(TCUserMgr.getInstance().userToken,data.order_id,object : OkGoStringCallBack<BaseBean>(context, BaseBean::class.java,false) {
                override fun onSuccess2Bean(bean: BaseBean) {
                    T(context,"${delete_btn.text}成功")
                    getOwnerAdapter<OrderListAdapter>()!!.remove(position)
                    getOwnerAdapter<OrderListAdapter>()!!.notifyDataSetChanged()
                }
            })
        }
    }
    fun addView(bean: OrderListBean.DataBean) {
        val view = layout.inflate(R.layout.activity_querendingdan_item, null)
        val img = view.findViewById(R.id.img) as SimpleDraweeView
        val tv1 = view.findViewById(R.id.tv1) as TextView
        val tv2 = view.findViewById(R.id.tv2) as TextView
        val count = view.findViewById(R.id.count) as TextView
        img.setImageURI(bean.good_one_pic)
        tv1.text = bean.good_name
        tv2.text = bean.good_specs
        count.text = "*" + bean.good_sum
        lin.addView(view)
    }

}