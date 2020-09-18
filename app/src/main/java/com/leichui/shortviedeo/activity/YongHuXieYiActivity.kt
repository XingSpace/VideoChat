package com.leichui.shortviedeo.activity

import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R

class YongHuXieYiActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_xieyi
    }

    override fun startAction() {
        showLeftBackButton()
        setTitleCenter("用户协议及隐私政策")
    }

}
