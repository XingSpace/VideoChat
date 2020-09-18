package com.leichui.shortviedeo.activity


import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.startMyActivity
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.xiaoshipin.TCApplication.activityList
import com.tencent.qcloud.xiaoshipin.TCApplication.mainActivity
import kotlinx.android.synthetic.main.activity_mysetting.*


class SettingActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_mysetting
    }

    override fun startAction() {
        setTitleCenter("设置")
        showLeftBackButton()
        addView("我的账户", "", R.mipmap.u1, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {
                startMyActivity(this@SettingActivity, MyAccountActivity::class.java)
            }
        })
        addView("编辑个人信息", "", R.mipmap.u2, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {
                startMyActivity(this@SettingActivity, MyInfoActivity::class.java)
            }
        })
        addView("我的订单", "", R.mipmap.u3, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {
                startMyActivity(this@SettingActivity, MyOrderActivity::class.java)
            }
        })
        addView("我的购买", "", R.mipmap.u4, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("我的收藏", "", R.mipmap.u5, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("我的评价", "", R.mipmap.u6, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("发现", "", R.mipmap.u7, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("扫一扫", "", R.mipmap.u8, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("我的活动", "", R.mipmap.u9, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("我的攻略", "", R.mipmap.u10, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("来访的人", "", R.mipmap.u11, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {
                startMyActivity(this@SettingActivity, LaiFangDeRenActivity::class.java)
            }
        })
        addView("每日奖励", "", R.mipmap.u12, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("角色申请", "", R.mipmap.u13, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })
        addView("设置", "", R.mipmap.u14, object : SingleSelectCallBack {
            override fun getSelect(sel: String?) {

            }
        })


        logout.setOnClickListener {
            TCUserMgr.getInstance().logout()
            mainActivity.finish()
            activityList.forEach {
                it.finish()
            }
            startMyActivity(this, LoginActivity::class.java)
        }
    }

    private fun addLine() {
        var inflate = LayoutInflater.from(this).inflate(R.layout.splitline, null)
        lin.addView(inflate)
    }

    private fun addView(title: String, value: String, image: Int, click: SingleSelectCallBack) {
        var inflate = LayoutInflater.from(this).inflate(R.layout.item_setting, null)
        var tv1: TextView = inflate.findViewById(R.id.tv1) as TextView
        var tv2: TextView = inflate.findViewById(R.id.tv2) as TextView
        var img: ImageView = inflate.findViewById(R.id.imgLeft) as ImageView
        tv1.text = title
        if (value.isNullOrEmpty()) {
            tv2.visibility = View.GONE
        } else {
            tv2.visibility = View.VISIBLE
            tv2.text = value
        }

        if (image != 0) {
            img.setImageResource(image)
        }
        inflate.setOnClickListener {
            click.getSelect("")
        }
        lin.addView(inflate)
    }


}
