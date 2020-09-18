package com.leichui.shortviedeo.activity

import android.app.Fragment
import android.view.View
import com.leichui.conghua.utils.L
import com.leichui.shortviedeo.Fragment.OtherDFragment
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*

class InfoDetailsActivity : BaseActivity() {

    var userId = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_infodetails
    }

    override fun startAction() {
        base_title.visibility = View.GONE
        base_title.visibility = View.GONE
        userId = intent.getStringExtra("userId")
        setFragment()
    }

    private fun setFragment(){
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
    *的一系列的事务变化，需要通过fragmentTransaction来操作执行
     */
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        //实例化要管理的fragment
        var otherDFragment = OtherDFragment.newInstance(userId)
        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.infoF,otherDFragment!!)
        //事务处理完需要提交
        fragmentTransaction.commit()
    }

}
