package com.leichui.shortviedeo.activity

import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_yijianfankui.*


class YiJianFanKuiActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_yijianfankui
    }

    override fun startAction() {
        showLeftBackButton()
        setTitleCenter("意见反馈")

        tijiao_tv.setOnClickListener {
//            UserMapper.postMessage(BaseApplication.getUser(this)!!.user_token, yijian_et.text.toString(), object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java, true) {
//                override fun onSuccess2Bean(bean: BaseBean) {
//
//                }
//            })
            T(this@YiJianFanKuiActivity, "感谢您的反馈")
            finish()
        }
    }

}
