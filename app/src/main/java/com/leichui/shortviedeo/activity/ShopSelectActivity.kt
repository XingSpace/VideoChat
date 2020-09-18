package com.leichui.shortviedeo.activity

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.startMyActivity
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.MyCountBean
import com.leichui.shortviedeo.bean.ShopListBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.leichui.shortviedeo.utils.KeyBoardUtils
import com.tencent.qalsdk.service.QalService.context
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_shangcheng.*
import kotlinx.android.synthetic.main.main_title.*
import npay.npay.yinmengyuan.fragment.adapter.ShangChengAdapter

class ShopSelectActivity : BaseActivity() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    lateinit var adapter: ShangChengAdapter
    var page = 0
    var keyword = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_shopselect
    }

    override fun startAction() {
        showLeftBackButton()

        initList()
        onRefresh()

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

    private fun getData(){

        ApiMapper.getGoodList(TCUserMgr.getInstance().userToken,"",keyword , page.toString() , object : OkGoStringCallBack<ShopListBean>(this, ShopListBean::class.java,false) {
            override fun onSuccess2Bean(bean: ShopListBean) {
                adapter.addAll(bean.data)
            }
        })
    }

    private fun initList() {
        adapter = ShangChengAdapter(this)
        shop_recyclerView.adapter = adapter

        val gridLayoutManager = GridLayoutManager(this, 2)
        shop_recyclerView.setLayoutManager(gridLayoutManager)
        //     quanbu_recyclerView.setHasFixedSize(true)
        shop_recyclerView.setRefreshListener(this)
        adapter.setMore(R.layout.easy_recycle_view_more, this)

//        val itemDecoration = DividerDecoration(Color.parseColor("#f4f4f4"), 2, 0, 0)//颜色 & 高度 & 左边距 & 右边距
//        itemDecoration.setDrawLastItem(true)//有时候你不想让最后一个item有分割线,默认true.
//        itemDecoration.setDrawHeaderFooter(false)//是否对Header于Footer有效,默认false.
//
//        shop_recyclerView.addItemDecoration(itemDecoration)
        adapter.setOnItemClickListener {
            val inn = Intent(this, GoodsActivity::class.java)
            inn.putExtra("good_id", adapter.allData[it].good_id)
            inn.putExtra("good_value", adapter.allData[it].good_name)

            setResult(Activity.RESULT_OK,inn)
            finish()
        }
    }
}
