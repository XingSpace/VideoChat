package com.leichui.shortviedeo.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.MyFocusBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_myfocus.*
import npay.npay.yinmengyuan.fragment.adapter.MyFansAdapter


class MyFansActivity  : BaseActivity() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    lateinit var adapter : MyFansAdapter
    var page = 0
    override fun setLayoutId(): Int {
        return R.layout.activity_myfocus
    }

    override fun startAction() {
        var title = getTitleStr()
        setTitleCenter(title)
        showLeftBackButton()
        adapter = MyFansAdapter(this)
        focus_recyclerView.adapter = adapter

        val linerLayoutManager = LinearLayoutManager(this)
        focus_recyclerView.setLayoutManager(linerLayoutManager)
        focus_recyclerView.setRefreshListener(this)
        adapter.setMore(R.layout.easy_recycle_view_more, this)
        onRefresh()
    }
    fun getTitleStr():String{
        return "粉丝"
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
        ApiMapper.getUserFollowed(TCUserMgr.getInstance().userToken, page.toString(), object : OkGoStringCallBack<MyFocusBean>(this, MyFocusBean::class.java, false) {
            override fun onSuccess2Bean(bean: MyFocusBean) {
                adapter.addAll(bean.data)
            }
        })
    }

}
