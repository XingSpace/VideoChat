package com.leichui.shortviedeo.activity


import android.support.v4.content.ContextCompat
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.startMyActivity
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.LoginBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.lzy.okgo.model.Response
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_mylogin.*
import kotlinx.android.synthetic.main.main_title.*


class LoginActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_mylogin
    }

    override fun startAction() {
        base_left_icon.setImageResource(R.mipmap.cancel_black)
        showLeftBackButton()
        commit.setOnClickListener {
            getValid()
        }
        zhuCeBtn.setOnClickListener {
            startMyActivity(this, RegistActivity::class.java)
        }
        wangJiMiMaBtn.setOnClickListener {
            startMyActivity(this, WangJiMiMaActivity::class.java)
        }
        yinSiBtn.setOnClickListener {
            startMyActivity(this, YongHuXieYiActivity::class.java)
        }

    }

    private fun getValid() {
        ApiMapper.sendVaild(phone.text.toString(), "0", object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java, false) {
            override fun onSuccess2Bean(bean: BaseBean) {
                T(this@LoginActivity, "验证码已发送")
                commit.isClickable = true
                setLogin()
            }
        })
    }

    private fun setLogin() {
        commit.text = "登录"
        commit.setOnClickListener {
            ApiMapper.codeLogin(phone.text.toString(), inputValue.text.toString(), object : OkGoStringCallBack<LoginBean>(this, LoginBean::class.java, false) {
                override fun onSuccess2Bean(bean: LoginBean) {
                    TCUserMgr.getInstance().userToken = bean.data.user_token
                    finish()
                }

            })
        }
    }


}
