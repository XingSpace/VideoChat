package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.ShopListBean
import com.leichui.shortviedeo.holder.ShangChengHolder

class ShangChengAdapter(context: Context) : RecyclerArrayAdapter<ShopListBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ShopListBean.DataBean> {
        return ShangChengHolder(parent)
    }

}