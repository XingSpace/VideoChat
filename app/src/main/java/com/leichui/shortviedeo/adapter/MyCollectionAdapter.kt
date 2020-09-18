package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.MyCollectionBean
import com.leichui.shortviedeo.holder.MyCollectionHolder

class MyCollectionAdapter(context: Context) : RecyclerArrayAdapter<MyCollectionBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MyCollectionBean.DataBean> {
        return MyCollectionHolder(parent)
    }

}