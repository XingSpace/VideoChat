package com.leichui.shortviedeo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.leichui.shortviedeo.adapter.MyFragmentPagerAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.TabEntity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_my_order.*
import npay.npay.yinmengyuan.fragment.fragment.OrderFragment

class MyOrderActivity : BaseActivity() {
    private var fragmentList = mutableListOf<Fragment>()
    private val fragment_num = 5
    private val tabbarTitles = listOf("全部订单", "等待付款", "等待发货", "已发货", "订货单")
    private val tabbarSelectImgs = listOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    private val tabbarImgs = listOf(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher)
    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun setLayoutId(): Int {
        return R.layout.activity_my_order
    }

    override fun startAction() {
        setTitleCenter("我的订单")
        showLeftBackButton()
        for (i in tabbarTitles.indices) {
            mTabEntities.add(TabEntity(tabbarTitles[i], tabbarSelectImgs[i], tabbarImgs[i]))
        }
        initFragmentList()
        setSlidingTabsAndViewPager()
        vp.currentItem =  intent.getIntExtra("page",0)
    }




    private fun setSlidingTabsAndViewPager() {
        vp.setOffscreenPageLimit(40)
        val pagerAdapter = MyFragmentPagerAdapter(supportFragmentManager, fragmentList)
        vp.setOffscreenPageLimit(fragment_num)
        vp.setAdapter(pagerAdapter)
        tab_top.setTabData(mTabEntities)
        tab_top.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                vp.setCurrentItem(position, false)
            }

            override fun onTabReselect(position: Int) {

            }
        })
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                tab_top.setCurrentTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun initFragmentList() {

        val aFragment = OrderFragment()
        var m1 = Bundle()
        m1.putString("type", "0")
        aFragment.arguments = m1
        val bFragment = OrderFragment()
        var m2 = Bundle()
        m2.putString("type", "1")
        bFragment.arguments = m2
        val cFragment = OrderFragment()
        var m3 = Bundle()
        m3.putString("type", "2")
        cFragment.arguments = m3
        val dFragment = OrderFragment()
        var m4 = Bundle()
        m4.putString("type", "3")
        dFragment.arguments = m4
        val eFragment = OrderFragment()
        var m5 = Bundle()
        m5.putString("type", "4")
        eFragment.arguments = m5

        fragmentList.add(aFragment)
        fragmentList.add(bFragment)
        fragmentList.add(cFragment)
        fragmentList.add(dFragment)
        fragmentList.add(eFragment)


    }
}
