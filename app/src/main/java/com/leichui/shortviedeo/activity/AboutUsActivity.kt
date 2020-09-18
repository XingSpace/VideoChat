package com.leichui.shortviedeo.activity

import android.support.v4.content.ContextCompat
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*

class AboutUsActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_aboutus
    }

    override fun startAction() {
        showLeftBackButton()
        setTitleCenter("关于我们")
    }

}
