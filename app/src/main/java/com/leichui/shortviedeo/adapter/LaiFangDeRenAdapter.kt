package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.MyFocusBean
import com.leichui.shortviedeo.holder.LaiFangDeRenHolder
import com.leichui.shortviedeo.holder.MyFocusHolder

/**
 * Created by Administrator on 2017/7/31.
 */
class LaiFangDeRenAdapter(context: Context) : RecyclerArrayAdapter<String>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return LaiFangDeRenHolder(parent)
    }

}