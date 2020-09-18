package com.leichui.shortviedeo.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.leichui.conghua.utils.startMyActivityWithOneParm
import com.leichui.shortviedeo.adapter.MyViewPagerAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.GoodInfoBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_goods.*
import npay.npay.yinmengyuan.fragment.adapter.ShangChengAdapter

class GoodsActivity : BaseActivity() {
    lateinit var adapter: ShangChengAdapter
    var good_id = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_goods
    }

    override fun startAction() {
        base_title.visibility = View.GONE
        good_id = intent.getStringExtra("good_id")
        initAdapter()
        getGood()
    }

    private fun initAdapter(){
        adapter = ShangChengAdapter(this)
        goods_recyclerView.adapter = adapter

        val gridLayoutManager = GridLayoutManager(this, 2)
        goods_recyclerView.setLayoutManager(gridLayoutManager)
        //     quanbu_recyclerView.setHasFixedSize(true)
//        goods_recyclerView.setRefreshListener(this)
//        adapter.setMore(R.layout.easy_recycle_view_more, this)

//        val itemDecoration = DividerDecoration(Color.parseColor("#f4f4f4"), 2, 0, 0)//颜色 & 高度 & 左边距 & 右边距
//        itemDecoration.setDrawLastItem(true)//有时候你不想让最后一个item有分割线,默认true.
//        itemDecoration.setDrawHeaderFooter(false)//是否对Header于Footer有效,默认false.
//
//        shop_recyclerView.addItemDecoration(itemDecoration)
        adapter.setOnItemClickListener {
            val inn = Intent(this, GoodsActivity::class.java)
            inn.putExtra("good_id", adapter.allData[it].good_id)
            startActivity(inn)

        }
    }


    private fun getGood() {
        ApiMapper.getGoodInfo(TCUserMgr.getInstance().userToken,good_id , object : OkGoStringCallBack<GoodInfoBean>(this, GoodInfoBean::class.java,false) {
            override fun onSuccess2Bean(bean: GoodInfoBean) {
                val adapter = MyViewPagerAdapter(context, bean.data.good_pics)
                img_viewpager.setAdapter(adapter)
                var all_num = bean.data.good_pics.size
                jishu.visibility = View.VISIBLE
                jishu.text = "1/" + all_num
                img_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                    }

                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    }

                    override fun onPageSelected(position: Int) {
                        jishu.text = (position + 1).toString() + "/" + all_num
                    }

                })
                buyNow.setOnClickListener {
                    var inn = Intent(this@GoodsActivity,QueRenActivity::class.java)
                    inn.putExtra("bean",bean)
                    startActivity(inn)

                }
                setSameList(bean)
            }
        })
    }

    private fun setSameList(bean: GoodInfoBean){
        var dataList = ArrayList<com.leichui.shortviedeo.bean.ShopListBean.DataBean>()
        bean.data.good_list.forEach {
            var data = com.leichui.shortviedeo.bean.ShopListBean.DataBean()
            data.good_id = it.good_id
            data.good_name = it.good_name
            data.good_one_pic = it.good_one_pic
            data.good_price = it.good_price
            data.sell_count = it.sell_count
            dataList.add(data)
        }
        adapter.addAll(dataList)
    }

}
