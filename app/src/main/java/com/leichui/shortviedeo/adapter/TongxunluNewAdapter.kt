package com.leichui.shortviedeo.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.TongxunluMyNewBean
import com.leichui.shortviedeo.holder.TongxunluNewViewHolder

/**
 * Created by Administrator on 2017/7/31.
 */
class TongxunluNewAdapter(context: Context) : RecyclerArrayAdapter<TongxunluMyNewBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TongxunluMyNewBean.DataBean> {
        return TongxunluNewViewHolder(parent)
    }

}