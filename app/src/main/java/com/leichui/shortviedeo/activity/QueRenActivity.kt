package com.leichui.shortviedeo.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.GoodInfoBean
import com.leichui.shortviedeo.bean.OrderInfoBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_que_ren.*

class QueRenActivity : BaseActivity() {
    var address_id = ""
    var score_or_price = ""
    var video_id = ""
    lateinit var layout: LayoutInflater
    lateinit var gouWuCheYiXuanBean: GoodInfoBean
    override fun setLayoutId(): Int {
        return R.layout.activity_que_ren
    }

    override fun startAction() {
        showLeftBackButton()
        setTitleCenter("确认订单")
        layout = LayoutInflater.from(this)
        getDef()
        setClick()
    }

    private fun setClick() {
        dizhi_lin.setOnClickListener {
            val inn = Intent(this, XuanDizhiActivity::class.java)
            startActivityForResult(inn, 222)
        }
        nodizhi_lin.setOnClickListener {
            val inn = Intent(this, XuanDizhiActivity::class.java)
            startActivityForResult(inn, 222)
        }
        gouWuCheYiXuanBean = intent.getSerializableExtra("bean") as GoodInfoBean


        price_tv2.text = gouWuCheYiXuanBean.data.good_price
        price_tv3.text = gouWuCheYiXuanBean.data.good_price
        addView(gouWuCheYiXuanBean)

        jiesuan_btn.setOnClickListener {
            if (address_id.isNullOrEmpty()) {
                T(this, "地址不可为空")
                return@setOnClickListener
            }


            ApiMapper.addOrder(TCUserMgr.getInstance().userToken, address_id,gouWuCheYiXuanBean.data.good_id,"1",gouWuCheYiXuanBean.data.good_specs[0],video_id, object : OkGoStringCallBack<OrderInfoBean>(this, OrderInfoBean::class.java, false) {
                override fun onSuccess2Bean(bean: OrderInfoBean) {
                    T(this@QueRenActivity,"订单提交成功")
                    val inn = Intent(this@QueRenActivity, ShouYinTaiActivity::class.java)
                    inn.putExtra("out_trade_no",bean.data.order_number)
                    inn.putExtra("order_price",bean.data.order_price)
                    startActivity(inn)
                }
            })
        }
    }

    private fun getDef() {

//
//        FragmentMapper.getMyDefaultInfo(BaseApplication.getUser(this)!!.user_token, object : OkGoStringCallBack<DefaultAddressBean>(this, DefaultAddressBean::class.java, false) {
//            override fun onSuccess2Bean(bean: DefaultAddressBean) {
//
//                if (bean.data.is_have.equals("1")) {
//                    nodizhi_lin.visibility = View.GONE
//                    dizhi_lin.visibility = View.VISIBLE
//                    dizhi_name.text = bean.data.name + "    " + bean.data.tel
//                    dizhi_tv.text = bean.data.address_info
//                    address_id = bean.data.address_id
//                } else {
//                    nodizhi_lin.visibility = View.VISIBLE
//                    dizhi_lin.visibility = View.GONE
//                }
//            }
//        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 2) {
            nodizhi_lin.visibility = View.GONE
            dizhi_lin.visibility = View.VISIBLE
            dizhi_name.text = data!!.getStringExtra("name")
            dizhi_tv.text = data!!.getStringExtra("address")
            address_id = data!!.getStringExtra("id")
        }
    }

    fun addView(bean: GoodInfoBean) {
        val view = layout.inflate(R.layout.activity_querendingdan_item, null)
        val img = view.findViewById<SimpleDraweeView>(R.id.img)
        val tv1 = view.findViewById<TextView>(R.id.tv1)
        val tv2 = view.findViewById<TextView>(R.id.tv2)

        img.setImageURI(bean.data.good_one_pic)
        tv1.text = bean.data.good_name
        tv2.text = "${bean.data.good_price}"

        lin.addView(view)
    }
}
