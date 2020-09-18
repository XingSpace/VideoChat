package com.leichui.shortviedeo.activity


import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.leichui.shortviedeo.adapter.TongxunluNewAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.TongxunBean
import com.leichui.shortviedeo.bean.TongxunluMyNewBean
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_friends.*


class FriendsActivity : BaseActivity() {

    lateinit var adapter: TongxunluNewAdapter
    var list1 = ArrayList<TongxunBean>()
    lateinit var manager : LinearLayoutManager
//    lateinit var myReceiver: MyReceiver
    override fun setLayoutId(): Int {
        return R.layout.activity_friends
    }

    override fun startAction() {
        showLeftBackButton()


        initAdapter()
    }
    private fun initAdapter(){

        adapter = TongxunluNewAdapter(this)
//        adapter.setNoMore(R.layout.easy_recycle_view_nomore)

        manager = LinearLayoutManager(this)
        recView.setLayoutManager(manager)
        recView.adapter = adapter
    }

    private fun initList(bean: TongxunluMyNewBean) {
        //侧边导航栏
        val heads = java.util.ArrayList<String>()
        for(item in bean.data){
            heads.add(item.key_word)
        }

        index_layout.setIndexBarHeightRatio(0.9f)
        index_layout.getIndexBar().setIndexsList(heads)
        index_layout.indexBar.setSelTextColor(Color.WHITE)
        index_layout.indexBar.setNorTextColor(Color.WHITE)
        index_layout.setCircleTextColor(Color.WHITE)
        index_layout.setCircleRadius(200f)
        index_layout.setCirCleTextSize(150)
        index_layout.setCircleColor(ContextCompat.getColor(this, R.color.black))

    }
}
