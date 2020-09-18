package com.leichui.shortviedeo.activity


import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.MyCollectionBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_mycollection.*
import npay.npay.yinmengyuan.fragment.adapter.MyCollectionAdapter


class MyCollectionActivity : BaseActivity() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    lateinit var adapter : MyCollectionAdapter
    var page = 0
    override fun setLayoutId(): Int {
        return R.layout.activity_mycollection
    }

    override fun startAction() {
        setTitleCenter("收藏")
        showLeftBackButton()

        initList()
        onRefresh()
    }

    private fun initList() {
        adapter = MyCollectionAdapter(this)
        collect_recyclerView.adapter = adapter

        val gridLayoutManager = GridLayoutManager(this, 3)
        collect_recyclerView.setLayoutManager(gridLayoutManager)
        collect_recyclerView.setRefreshListener(this)
        adapter.setMore(R.layout.easy_recycle_view_more, this)

        adapter.setOnItemClickListener {
//            val inn = Intent(this, GoodsActivity::class.java)
//            inn.putExtra("good_id", adapter.allData[it].good_id)
//            startActivity(inn)

        }
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
        ApiMapper.getMyCollectList(TCUserMgr.getInstance().userToken, page.toString(), object : OkGoStringCallBack<MyCollectionBean>(this, MyCollectionBean::class.java, false) {
            override fun onSuccess2Bean(bean: MyCollectionBean) {
                adapter.addAll(bean.data)
            }
        })
    }


}
