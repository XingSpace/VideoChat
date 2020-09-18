package com.leichui.shortviedeo.activity


import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_changephone.*
import java.util.*


class ChangePhoneActivity : BaseActivity() {
    private var serviceSelect = false
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
        return R.layout.activity_changephone
    }

    override fun startAction() {

        setTitleCenter("修改手机号")
        showLeftBackButton()


        init()


    }

    private fun init(){
        fasong_tv.setOnClickListener {
            if(user_tel.text.toString().isNullOrEmpty()){
                T(this,"请输入手机号")
            }else{
                sendCodeMapper()
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
            startChange()
        }

    }

    fun startChange(){
        ApiMapper.updateUserTel(TCUserMgr.getInstance().userToken,user_tel.text.toString(),fasong_tv.text.toString() ,object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java, true) {
            override fun onSuccess2Bean(bean: BaseBean) {
                T(this@ChangePhoneActivity,"修改成功")
                finish()
            }
        })
    }

    fun sendCodeMapper(){
        ApiMapper.sendVaild(user_tel.text.toString(),"1",object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java,false){
            override fun onSuccess2Bean(bean: BaseBean) {

            }
        })
    }


}
