package com.leichui.shortviedeo.activity


import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.leichui.conghua.utils.L
import com.leichui.conghua.utils.T
import com.leichui.conghua.utils.showInputDialog
import com.leichui.shortviedeo.Base.BaseBean
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.leichui.shortviedeo.utils.SingleSelectCallBack
import com.tencent.qcloud.ugckit.module.upload.TCUserMgr
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.xiaoshipin.TCApplication.activityList
import kotlinx.android.synthetic.main.activity_publishvideo.*
import kotlinx.android.synthetic.main.main_title.*


class PublishVideoActivity : BaseActivity() {
    var hangYeTagList = ArrayList<String>()
    var chanPingTagList = ArrayList<String>()
    var isHaveGood ="0"
    var isGoodApp = "1"
    var isShowCar = "1"
    var goodId = ""
    var goodName = ""
    var goodPrice = ""
    var goodAppUrl = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_publishvideo
    }

    override fun startAction() {
        setRightText("发布")
        setTitleCenter("发布作品")
        baseRightTv.setTextColor(ContextCompat.getColor(this,R.color.app_red))
        showLeftBackButton()
        baseRightTv.setOnClickListener {
            goodName = shangpingming.text.toString()
            goodPrice = jiage.text.toString()
            goodAppUrl = shangpinglianjie.text.toString()
            ApiMapper.addVideo(TCUserMgr.getInstance().userToken,getEtValue(jianjie),TCUserMgr.getInstance().currentVideoId,TCUserMgr.getInstance().currentVideoUrl,TCUserMgr.getInstance().currentVideoPic,
                    getEtValue(zhanweihao),JSON.toJSONString(hangYeTagList),JSON.toJSONString(chanPingTagList),
                    isHaveGood,isGoodApp,isShowCar,goodId,goodName,goodPrice,goodAppUrl,object : OkGoStringCallBack<BaseBean>(this, BaseBean::class.java,false){
                override fun onSuccess2Bean(bean: BaseBean) {
                    T(this@PublishVideoActivity,"视频发布成功")
                    finish()

                }
            })
        }

        add_hangye.setOnClickListener {
            showInputDialog("添加行业标签",this,object : SingleSelectCallBack{
                override fun getSelect(sel: String?) {
                    if(!sel.isNullOrEmpty()){
                        val wordlayout = LayoutInflater.from(this@PublishVideoActivity).inflate(R.layout.item_tag_word, null)
                        var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                        var del = wordlayout.findViewById<ImageView>(R.id.del)
                        tag_tv.text=sel
                        del.setOnClickListener {
                            hangye_fl_xianshi.removeView(wordlayout)
                            hangYeTagList.remove(sel!!)
                        }
                        hangye_fl_xianshi.addView(wordlayout)
                        hangYeTagList.add(sel!!)
                    }

                }

            })
        }
        add_chanping.setOnClickListener {

            showInputDialog("添加产品标签",this,object : SingleSelectCallBack{
                override fun getSelect(sel: String?) {
                    if(!sel.isNullOrEmpty()){
                        val wordlayout = LayoutInflater.from(this@PublishVideoActivity).inflate(R.layout.item_tag_word, null)
                        var tag_tv = wordlayout.findViewById<TextView>(R.id.tag_tv)
                        var del = wordlayout.findViewById<ImageView>(R.id.del)
                        tag_tv.text=sel
                        del.setOnClickListener {
                            chanping_fl_xianshi.removeView(wordlayout)
                            chanPingTagList.remove(sel!!)
                        }
                        chanping_fl_xianshi.addView(wordlayout)
                        chanPingTagList.add(sel!!)
                    }

                }

            })
        }
        guanlianshangping.setOnClickListener {
            guanlianshangping.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            guanlianshangping.setBackgroundResource(R.drawable.pink_biankuang)
            buguanlianshangping.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            buguanlianshangping.setBackgroundResource(R.drawable.grey_biankuang3)
            isHaveGood = "1"
            guanlianL.visibility = View.VISIBLE
        }
        buguanlianshangping.setOnClickListener {
            buguanlianshangping.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            buguanlianshangping.setBackgroundResource(R.drawable.pink_biankuang)
            guanlianshangping.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            guanlianshangping.setBackgroundResource(R.drawable.grey_biankuang3)
            isHaveGood = "0"
            guanlianL.visibility = View.GONE
        }
        zhanneishangping.setOnClickListener {
            zhanneishangping.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            zhanneishangping.setBackgroundResource(R.drawable.pink_biankuang)
            zhanwaishangping.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            zhanwaishangping.setBackgroundResource(R.drawable.grey_biankuang3)
            isGoodApp = "1"
            zhanneishangpingId.visibility = View.VISIBLE
            shangpinglianjie.visibility = View.GONE
        }
        zhanwaishangping.setOnClickListener {
            zhanwaishangping.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            zhanwaishangping.setBackgroundResource(R.drawable.pink_biankuang)
            zhanneishangping.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            zhanneishangping.setBackgroundResource(R.drawable.grey_biankuang3)
            isGoodApp = "0"
            zhanneishangpingId.visibility = View.GONE
            shangpinglianjie.visibility = View.VISIBLE
        }
        xianshigouwuche.setOnClickListener {
            xianshigouwuche.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            xianshigouwuche.setBackgroundResource(R.drawable.pink_biankuang)
            xianshishangpingtu.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            xianshishangpingtu.setBackgroundResource(R.drawable.grey_biankuang3)
            isShowCar = "1"
        }
        xianshishangpingtu.setOnClickListener {
            xianshishangpingtu.setTextColor(ContextCompat.getColor(this,R.color.p_red))
            xianshishangpingtu.setBackgroundResource(R.drawable.pink_biankuang)
            xianshigouwuche.setTextColor(ContextCompat.getColor(this,R.color.hint_color))
            xianshigouwuche.setBackgroundResource(R.drawable.grey_biankuang3)
            isShowCar = "0"
        }
        zhanneishangpingId.setOnClickListener {
            var inn = Intent(this,ShopSelectActivity::class.java)
            startActivityForResult(inn,999)
        }
    }

    override fun onResume() {
        super.onResume()
        if(TCUserMgr.getInstance().currentVideoId.isNullOrEmpty() && activityList.size == 1){
            finish()
        }

    }


    fun getEtValue(et:EditText):String{
        return et.text.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 999){
            goodId = data!!.getStringExtra("good_id")
            var goodsValue = data!!.getStringExtra("good_value")
            zhanneishangpingId.text = goodsValue
        }
    }

}
