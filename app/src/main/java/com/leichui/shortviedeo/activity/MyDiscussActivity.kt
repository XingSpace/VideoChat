package com.leichui.shortviedeo.activity


import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.MyDiscussBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_mydicuss.*
import npay.npay.yinmengyuan.fragment.adapter.MyDiscussAdapter
import npay.npay.yinmengyuan.fragment.adapter.MyFocusAdapter


class MyDiscussActivity : BaseActivity() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    lateinit var adapter : MyDiscussAdapter
    var page = 0
    override fun setLayoutId(): Int {
        return R.layout.activity_mydicuss
    }

    override fun startAction() {
        setTitleCenter("评论")
        showLeftBackButton()
        adapter = MyDiscussAdapter(this)
        discuss_recyclerView.adapter = adapter

        val linerLayoutManager = LinearLayoutManager(this)
        discuss_recyclerView.setLayoutManager(linerLayoutManager)
        discuss_recyclerView.setRefreshListener(this)
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
        ApiMapper.getMyDiscussList(TCUserMgr.getInstance().userToken, page.toString(), object : OkGoStringCallBack<MyDiscussBean>(this, MyDiscussBean::class.java, false) {
            override fun onSuccess2Bean(bean: MyDiscussBean) {
                adapter.addAll(bean.data)
            }
        })
    }


}
