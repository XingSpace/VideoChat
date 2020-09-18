package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.MyFocusBean
import com.leichui.shortviedeo.bean.MyPraiseBean
import com.leichui.shortviedeo.holder.MyFansHolder
import com.leichui.shortviedeo.holder.MyFocusHolder
import com.leichui.shortviedeo.holder.MyPraiseHolder

class MyPraiseAdapter(context: Context) : RecyclerArrayAdapter<MyPraiseBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MyPraiseBean.DataBean> {
        return MyPraiseHolder(parent)
    }

}