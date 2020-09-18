package npay.npay.yinmengyuan.fragment.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.AddressListBean


class DiZhiGuanLiAdapter(context: Context) : RecyclerArrayAdapter<AddressListBean.DataBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AddressListBean.DataBean> {
        return DiZhiGuanLiHolder(parent)
    }

}