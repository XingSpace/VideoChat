package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.DiscussBean
import com.leichui.shortviedeo.bean.MyDiscussBean
import com.leichui.shortviedeo.holder.DiscussHolder
import com.leichui.shortviedeo.holder.MyDiscussHolder

class MyDiscussAdapter(context: Context) : RecyclerArrayAdapter<MyDiscussBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MyDiscussBean.DataBean> {
        return MyDiscussHolder(parent)
    }

}