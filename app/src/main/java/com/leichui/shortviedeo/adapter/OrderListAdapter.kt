package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.OrderListBean
import com.leichui.shortviedeo.holder.OrderListHolder

/**
 * Created by Administrator on 2017/7/31.
 */
class OrderListAdapter(context: Context) : RecyclerArrayAdapter<OrderListBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<OrderListBean.DataBean> {
        return OrderListHolder(parent)
    }

}