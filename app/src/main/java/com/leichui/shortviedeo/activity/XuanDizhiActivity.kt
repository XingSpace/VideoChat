package com.leichui.shortviedeo.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.AddressListBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_dizhi_guanli.*
import npay.npay.yinmengyuan.fragment.adapter.DiZhiGuanLiAdapter

class XuanDizhiActivity : BaseActivity() {

    lateinit var adapter: DiZhiGuanLiAdapter


    override fun setLayoutId(): Int {
        return R.layout.activity_dizhi_guanli
    }

    override fun startAction() {
        showLeftBackButton()
        setTitleCenter("选择地址")
        initView()
        add_dizhi.setOnClickListener {
            var inn = Intent(this, AddAdressActivity::class.java)
            startActivity(inn)
        }
    }

    private fun initView() {

        adapter = DiZhiGuanLiAdapter(this)
        dizhi_recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        dizhi_recyclerView.setLayoutManager(linearLayoutManager)


    }

    override fun onResume() {
        super.onResume()
        getList()
    }

    fun getList() {

        ApiMapper.getMyAddressList(TCUserMgr.getInstance().userToken,object : OkGoStringCallBack<AddressListBean>(this@XuanDizhiActivity, AddressListBean::class.java) {
            override fun onSuccess2Bean(bean: AddressListBean) {
                adapter.clear()
                adapter.addAll(bean.data)
                adapter.setOnItemClickListener(object : RecyclerArrayAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {

                        var intent = Intent()
                        intent.putExtra("name", bean.data[position].name+"    "+bean.data[position].tel)
                        intent.putExtra("address", bean.data[position].address_info)
                        intent.putExtra("id", bean.data[position].address_id)
                        setResult(2, intent)
                        finish() //结束当前的activity的生命周期
                    }

                })
            }
        })
    }
}
