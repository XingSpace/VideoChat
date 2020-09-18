package com.leichui.shortviedeo.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_shouyingtai.*


class ShouYinTaiActivity : BaseActivity() {
//    lateinit var api: IWXAPI
    var out_trade_no = ""
    var fangshi = "1"
    var order_price=""
   val SDK_PAY_FLAG = 1001
    override fun setLayoutId(): Int {
        return R.layout.activity_shouyingtai
    }

    override fun startAction() {
        showLeftBackButton()
        out_trade_no = intent.getStringExtra("out_trade_no")
        order_price= intent.getStringExtra("order_price")
        dingdan_tv.text = "订单编号 ${out_trade_no}"
        setTitleCenter("收银台")
        money.text="¥ ${order_price}"
        setClick()

    }

    private fun toWXPay() {
//        api = WXAPIFactory.createWXAPI(this@ShouYinTaiActivity, appIDWX)
//        api.registerApp(appIDWX)
//        FragmentMapper.postPayOrder(BaseApplication.getUser(this)!!.user_token,out_trade_no,"2", object : OkGoStringCallBack<WxBean>(this, WxBean::class.java, false) {
//            override fun onSuccess2Bean(bean: WxBean) {
//                val request = PayReq()
//                request.appId = bean.data.orderstring.appid
//                request.partnerId = bean.data.orderstring.partnerid
//                request.prepayId = bean.data.orderstring.prepayid
//                request.packageValue = "Sign=WXPay"
//                request.nonceStr = bean.data.orderstring.noncestr
//                request.timeStamp = bean.data.orderstring.timestamp.toString()
//                request.sign = bean.data.orderstring.sign
//                api.sendReq(request)//发送调起微信的请求
//            }
//        })
    }



    private fun setClick() {
        weixin.setOnClickListener {
            weixin_check.isChecked = true
            ali_check.isChecked = false
            fangshi = "1"
        }
        ali.setOnClickListener {
            weixin_check.isChecked = false
            ali_check.isChecked = true
            fangshi = "2"
        }
        pay_btn.setOnClickListener {
            if (fangshi.equals("1")){
                toWXPay()
            }
            else{
                toAli()
            }
        }
    }


    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                SDK_PAY_FLAG -> {
//                    val payResult = PayResult(msg.obj as Map<String, String>)
//                    //同步获取结果
//                    val resultInfo = payResult.result
//                    Log.i("Pay", "Pay:" + resultInfo)
//                    val resultStatus = payResult.resultStatus
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Toast.makeText(this@ShouYinTaiActivity, "支付成功", Toast.LENGTH_SHORT).show()
//                        val inn = Intent(this@ShouYinTaiActivity, MyOrderActivity::class.java)
//                        inn.putExtra("page", 2)
//                        startActivity(inn)
//                        toFenXiang()
//                        finish()
//                    } else {
//                        Toast.makeText(this@ShouYinTaiActivity, "支付失败", Toast.LENGTH_SHORT).show()
//                        val inn = Intent(this@ShouYinTaiActivity, MyOrderActivity::class.java)
//                        inn.putExtra("page", 1)
//                        startActivity(inn)
//                        finish()
//                    }
                }
            }
        }
    }
    private fun toFenXiang(){
//        val inn2 = Intent(this@ShouYinTaiActivity, FengXiangActivity::class.java)
//        startActivity(inn2)
    }
    private fun toAli() {
//        FragmentMapper.postPayOrder(BaseApplication.getUser(this)!!.user_token,out_trade_no,"1", object : OkGoStringCallBack<AliBean>(this, AliBean::class.java, false) {
//            override fun onSuccess2Bean(bean: AliBean) {
//                val payRunnable = Runnable {
//                                //新建任务
//            val alipay = PayTask(this@ShouYinTaiActivity)
//            //获取支付结果
//            val result = alipay.payV2(bean.data.orderstring, true)
//            val msg = Message()
//            msg.what = SDK_PAY_FLAG
//            msg.obj = result
//            mHandler.sendMessage(msg)
//        }
//        // 必须异步调用
//        val payThread = Thread(payRunnable)
//        payThread.start()
//            }
//        })
    }
}