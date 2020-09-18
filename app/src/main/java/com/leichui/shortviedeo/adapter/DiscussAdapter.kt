package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.DiscussBean
import com.leichui.shortviedeo.holder.DiscussHolder

class DiscussAdapter(context: Context) : RecyclerArrayAdapter<DiscussBean.DataBean.ListBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DiscussBean.DataBean.ListBean> {
        return DiscussHolder(parent)
    }

}