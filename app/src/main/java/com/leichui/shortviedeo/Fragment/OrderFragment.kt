package npay.npay.yinmengyuan.fragment.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.bean.OrderListBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qalsdk.util.BaseApplication
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.fragment_order.*
import npay.npay.yinmengyuan.fragment.adapter.OrderListAdapter

class OrderFragment : Fragment() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    var page = 0
    lateinit var adapter: OrderListAdapter
    var type="0"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        type = bundle!!.getString("type")
        adapter = OrderListAdapter(context!!)
        order_recyclerView.adapter = adapter

        val linerLayoutManager = LinearLayoutManager(context!!)
        order_recyclerView.setLayoutManager(linerLayoutManager)
        order_recyclerView.setRefreshListener(this)
        adapter.setMore(R.layout.easy_recycle_view_more, this)
        onRefresh()

    }
    override fun onRefresh() {
        page=0
        adapter.clear()
        getData()
    }

    override fun onLoadMore() {
        page++
        getData()
    }

    private fun getData(){

        ApiMapper.getOrderList(TCUserMgr.getInstance().userToken, page.toString(),type, object : OkGoStringCallBack<OrderListBean>(context!!, OrderListBean::class.java, false) {
            override fun onSuccess2Bean(bean: OrderListBean) {
                adapter.addAll(bean.data)
            }

        })
    }


}