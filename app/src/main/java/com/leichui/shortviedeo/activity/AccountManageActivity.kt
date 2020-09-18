package com.leichui.shortviedeo.activity


import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.leichui.conghua.utils.startMyActivity
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_accountmanage.*


class AccountManageActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_accountmanage
    }

    override fun startAction() {
        setTitleCenter("账号管理")
        showLeftBackButton()


        addView("手机号","",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {
                startMyActivity(this@AccountManageActivity,ChangePhoneActivity::class.java)
            }
        })
        addView("修改密码","",0,object : SingleSelectCallBack{
            override fun getSelect(sel: String?) {
                startMyActivity(this@AccountManageActivity,ChangePwdActivity::class.java)
            }
        })


    }


    private fun addView(title:String,value:String,image:Int,click:SingleSelectCallBack){
        var inflate = LayoutInflater.from(this).inflate(R.layout.item_myinfo, null)
        var tv1: TextView = inflate.findViewById(R.id.tv1) as TextView
        var tv2: TextView = inflate.findViewById(R.id.tv2) as TextView
        var img: ImageView = inflate.findViewById(R.id.img) as ImageView
        tv1.text = title
        if(value.isNullOrEmpty()){
            tv2.visibility = View.INVISIBLE
        }else{
            tv2.visibility = View.VISIBLE
            tv2.text = value
        }

        if(image != 0){
            img.setImageResource(image)
        }
        inflate.setOnClickListener {
            click.getSelect("")
        }
        lin.addView(inflate)
    }

}
