package com.leichui.shortviedeo.activity

import android.content.Intent
import android.view.View
import com.leichui.shortviedeo.Fragment.ShouyeFragment
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.view.ShouyeItem
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_shouye.*
import kotlinx.android.synthetic.main.main_title.*

class ShouYeActivity : BaseActivity() {


    override fun setLayoutId(): Int {
        return R.layout.activity_shouye;
    }

    override fun startAction() {
        search_bar.visibility = View.VISIBLE
        address_txt.text = "上海"
        findViews()
        init()
    }

    private fun findViews() {
        shouyeL.setOnClickListener(this::onShouyeClick)
        shequL.setOnClickListener(this::onShequClick)
        paisheL.setOnClickListener(this::onPaisheClick)
        liaotianL.setOnClickListener(this::onLiaotianClick)
        woL.setOnClickListener(this::onWoClick)
    }

    private fun init() {
        var trans = fragmentManager.beginTransaction()
        trans.replace(R.id.content, ShouyeFragment())
        trans.commit()

        address_txt.setOnClickListener(this::onAddressClick)
        search_edit.setOnClickListener(this::onSearchEditClick)
    }

    /**
     * 添加至首页的滚动栏
     */
    public fun addViewToScroll(item: ShouyeItem) {

    }

    private fun onShouyeClick(view: View) {
        resetTab()
        shouyeV.visibility = View.VISIBLE
    }

    private fun onShequClick(view: View) {
        resetTab()
        shequV.visibility = View.VISIBLE
    }

    private fun onPaisheClick(view: View) {
//        resetTab()

    }

    private fun onLiaotianClick(view: View) {
        resetTab()
        liaotianV.visibility = View.VISIBLE

    }

    private fun onWoClick(view: View) {
        resetTab()
        woV.visibility = View.VISIBLE

    }

    private fun resetTab() {
        shouyeV.visibility = View.GONE
        liaotianV.visibility = View.GONE
        shequV.visibility = View.GONE
        woV.visibility = View.GONE
    }

    private fun onAddressClick(view: View) {
        var intent = Intent(this, ShouyeAddressActivity::class.java)
        startActivityForResult(intent, 8989)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 8989) {
            //todo 修改了地址

        }

    }

    private fun onSearchEditClick(view: View) {

    }


}