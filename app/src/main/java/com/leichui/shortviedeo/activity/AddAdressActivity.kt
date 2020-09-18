package com.leichui.shortviedeo.activity

import android.app.Activity
import android.content.Intent
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_add_adress.*

class AddAdressActivity : BaseActivity() {

    var province_id = ""
    var city_id = ""
    var area_id = ""
    var is_default = "0"
    override fun setLayoutId(): Int {
        return R.layout.activity_add_adress
    }

    override fun startAction() {
        setTitleCenter("添加地址")
        showLeftBackButton()
        setClick()
    }

    private fun setClick() {
        btn_Options.setOnClickListener {
            val inn = Intent(this, XuanZeDiQuActivity::class.java)
            startActivityForResult(inn, 111)
        }
        checkbox.setOnClickListener {
            if (checkbox.isChecked) {
                is_default = "1"
            } else {
                is_default = "0"
            }
        }
        save.setOnClickListener {
            if (shouhuoren.text.toString().isNullOrEmpty()) {
                T(this, "请输入收货人的姓名")
                return@setOnClickListener
            }

            if (shoujihao.text.toString().isNullOrEmpty()) {

                T(this, "请输入手机号")
                return@setOnClickListener
            }

            if (detail_address.text.toString().isNullOrEmpty()) {

                T(this, "请输入详细地址")
                return@setOnClickListener
            }
            if (province_id.isNullOrEmpty()) {
                T(this, "请选择城市")
                return@setOnClickListener
            }

            ApiMapper.addMyAddress(TCUserMgr.getInstance().userToken,
                    shouhuoren.text.toString(),
                    shoujihao.text.toString(),
                    detail_address.text.toString(),
                    is_default,
                    province_id,
                    city_id,
                    area_id, object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java) {
                override fun onSuccess2Bean(bean: BaseBean) {
                    T(this@AddAdressActivity, "添加成功")
                    finish()
                }


            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            province_id = data?.getStringExtra("sheng")!!
            city_id = data?.getStringExtra("shi")!!
            area_id = data?.getStringExtra("qu")!!
            btn_Options.text = data?.getStringExtra("diqu")
        }
    }
}
