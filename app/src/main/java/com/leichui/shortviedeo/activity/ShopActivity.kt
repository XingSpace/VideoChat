package com.leichui.shortviedeo.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.conghua.utils.L
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

class ShopActivity : BaseActivity() , SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    lateinit var adapter: ShangChengAdapter
    var page = 0
    var keyword = ""
    var userId = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_shangcheng
    }

    override fun startAction() {
        showLeftBackButton()
        baseRightImageTv.text = "我的订单"
        baseRightImageTv.visibility = View.VISIBLE
        baseRightImageTv.setTextColor(ContextCompat.getColor(this,R.color.dingdanColor))

        userId = intent.getStringExtra("userId")

        val drawable = resources.getDrawable(
                R.mipmap.gouwu_ic)
        // 这一步必须要做，否则不会显示。
        drawable.setBounds(0, 0, drawable.minimumWidth,
                drawable.minimumHeight)
        baseRightImageTv.setCompoundDrawables(drawable,null , null, null)
        baseRightImageTv.setOnClickListener {
            startMyActivity(this,MyOrderActivity::class.java)
        }
        initList()
        onRefresh()

        sousuoSet()
    }

    private fun sousuoSet(){
        cancel.setOnClickListener {
            keyword = ""
            sousuo.setText("")
            KeyBoardUtils.closeKeybord(sousuo,this)
            onRefresh()
        }
        sousuo.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_DONE){
                keyword = sousuo.text.toString()
                onRefresh()
            }
            false
        }

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

        ApiMapper.getMyCount(TCUserMgr.getInstance().userToken, userId, object : OkGoStringCallBack<MyCountBean>(this, MyCountBean::class.java, false) {
            override fun onSuccess2Bean(bean: MyCountBean) {
                shopHead.setImageURI(bean.data.user_avatar)
                name.text = bean.data.user_name + "的店铺"
            }
        })

        ApiMapper.getGoodList(TCUserMgr.getInstance().userToken,userId,keyword , page.toString() , object : OkGoStringCallBack<ShopListBean>(this, ShopListBean::class.java,false) {
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
            startActivity(inn)

        }
    }
}
