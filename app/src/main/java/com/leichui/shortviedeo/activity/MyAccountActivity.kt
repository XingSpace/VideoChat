package com.leichui.shortviedeo.activity


import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.tencent.qcloud.xiaoshipin.R
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.TabEntity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_myaccount.*
import java.util.ArrayList


class MyAccountActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_myaccount
    }

    override fun startAction() {
        setTitleCenter("我的账户")
        right_text.visibility = View.VISIBLE
        right_text.text = "账户管理"
        showLeftBackButton()
    }


}
