package com.leichui.shortviedeo.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.leichui.conghua.utils.startMyActivityWithOneParm
import com.leichui.shortviedeo.adapter.MyViewPagerAdapter
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.GoodInfoBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_goods.*
import npay.npay.yinmengyuan.fragment.adapter.ShangChengAdapter

class GoodsDetailsActivity : BaseActivity() {
    var good_id = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_goodsdetails
    }

    override fun startAction() {
        base_title.visibility = View.GONE
        good_id = intent.getStringExtra("good_id")
        getGood()
    }



    private fun getGood() {
        ApiMapper.getGoodInfo(TCUserMgr.getInstance().userToken,good_id , object : OkGoStringCallBack<GoodInfoBean>(this, GoodInfoBean::class.java,false) {
            override fun onSuccess2Bean(bean: GoodInfoBean) {
                val adapter = MyViewPagerAdapter(context, bean.data.good_pics)
                img_viewpager.setAdapter(adapter)
                var all_num = bean.data.good_pics.size
                jishu.visibility = View.VISIBLE
                jishu.text = "1/" + all_num
                img_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {
                    }

                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    }

                    override fun onPageSelected(position: Int) {
                        jishu.text = (position + 1).toString() + "/" + all_num
                    }

                })
                buyNow.setOnClickListener {
                    var inn = Intent(this@GoodsDetailsActivity,QueRenActivity::class.java)
                    inn.putExtra("bean",bean)
                    startActivity(inn)

                }
            }
        })
    }


}
