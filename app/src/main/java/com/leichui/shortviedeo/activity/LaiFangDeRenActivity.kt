package com.leichui.shortviedeo.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.TabEntity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_laifangderen.*
import kotlinx.android.synthetic.main.activity_laifangderen.tab_top
import npay.npay.yinmengyuan.fragment.adapter.LaiFangDeRenAdapter

class LaiFangDeRenActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    private val tabbarTitles = listOf("谁看过我", "我看过谁")
    private val tabbarSelectImgs = listOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    private val tabbarImgs = listOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    private val mTabEntities = ArrayList<CustomTabEntity>()
    var page = 0
    lateinit var adapter: LaiFangDeRenAdapter

    override fun setLayoutId(): Int {
        return R.layout.activity_laifangderen
    }

    override fun startAction() {
        setTitleCenter("来访的人")
        showLeftBackButton()

        initTabs()
        adapter = LaiFangDeRenAdapter(this)
        recView.adapter = adapter

        val linerLayoutManager = LinearLayoutManager(this)
        recView.setLayoutManager(linerLayoutManager)
        recView.setRefreshListener(this)
        adapter.setMore(R.layout.easy_recycle_view_more, this)
        onRefresh()
    }

    fun initTabs() {
        for (i in tabbarTitles.indices) {
            mTabEntities.add(TabEntity(tabbarTitles[i], tabbarSelectImgs[i], tabbarImgs[i]))
        }
        tab_top.setTabData(mTabEntities)
        tab_top.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    override fun onRefresh() {
        page = 0
        adapter.clear()
        getData()
    }

    override fun onLoadMore() {
        page++
        getData()
    }

    private fun getData() {
        adapter.add("111")
        adapter.add("222")
        adapter.notifyDataSetChanged()
    }
}
