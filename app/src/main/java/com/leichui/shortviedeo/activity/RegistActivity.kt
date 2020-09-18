package com.leichui.shortviedeo.activity


import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.LoginBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import kotlinx.android.synthetic.main.activity_mylogin.commit
import kotlinx.android.synthetic.main.activity_mylogin.inputValue
import kotlinx.android.synthetic.main.activity_mylogin.phone
import kotlinx.android.synthetic.main.activity_myregist.*
import kotlinx.android.synthetic.main.main_title.*
import java.util.*


class RegistActivity : BaseActivity() {
    private var timer: Timer? = null
    private var time = 60
    private var handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            fasong_tv.text = "${time}s"
            time--
            if (time <= 0) {
                timer?.cancel()
                fasong_tv.text = "获取验证码"
                time = 60
                fasong_tv.isEnabled = true

            }
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_myregist
    }

    override fun startAction() {
        base_left_icon.setImageResource(R.mipmap.cancel_black)
        showLeftBackButton()

        init()

    }

    private fun init() {
        fasong_tv.setOnClickListener {
            if (phone.text.toString().isNullOrEmpty()) {
                T(this, "请输入手机号")
            } else {
                getValid()
                fasong_tv.isEnabled = false
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        handler.sendEmptyMessage(1)
                    }
                }, 0, 1000)
            }

        }

        commit.setOnClickListener {
            regist()
        }
    }

    private fun getValid() {
        ApiMapper.sendVaild(phone.text.toString(), "0", object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java, false) {
            override fun onSuccess2Bean(bean: BaseBean) {
                T(this@RegistActivity, "验证码已发送")
            }
        })
    }

    private fun regist() {
        ApiMapper.register(phone.text.toString(), inputValue.text.toString(), pwd1.text.toString(), pwd2.text.toString(), object : OkGoStringCallBack<LoginBean>(this, LoginBean::class.java, false) {
            override fun onSuccess2Bean(bean: LoginBean) {
                TCUserMgr.getInstance().userToken = bean.data.user_token
                finish()
            }
        })
    }

}
