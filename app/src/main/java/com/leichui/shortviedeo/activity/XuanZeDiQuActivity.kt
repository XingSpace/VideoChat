package com.leichui.shortviedeo.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.GridLayout
import android.view.LayoutInflater
import android.widget.TextView
import com.leichui.conghua.utils.T
import com.leichui.shortviedeo.base.BaseActivity
import com.leichui.shortviedeo.bean.DiQuBean
import com.leichui.shortviedeo.http.OkGoStringCallBack
import com.leichui.shortviedeo.mapper.ApiMapper
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_xuanzediqu.*


class XuanZeDiQuActivity : BaseActivity() {

    lateinit var inflater: LayoutInflater

    var sheng = ""
    var shi = ""
    var qu = ""
    var shengname = ""
    var shiname = ""
    var quname = ""
    val dizhiList1 = arrayListOf<TextView>()
    val dizhiList2 = arrayListOf<TextView>()
    val dizhiList3 = arrayListOf<TextView>()
    override fun setLayoutId(): Int {
        return R.layout.activity_xuanzediqu
    }

    override fun startAction() {
        setTitleCenter("选择地区")
        showLeftBackButton()
        inflater = LayoutInflater.from(this)
        getSheng()
        left_icon.setOnClickListener {
            val inn = Intent()
            inn.putExtra("diqu", "")
            inn.putExtra("sheng", "")
            inn.putExtra("shi", "")
            inn.putExtra("qu", "")
            setResult(Activity.RESULT_OK, inn)
            finish()
        }
        sure_btn.setOnClickListener {
            val inn = Intent()
            if ("" != sheng) {
                inn.putExtra("qu", "${qu}")
                inn.putExtra("shi", "${shi}")
                inn.putExtra("sheng", "${sheng}")
            }else{
                T(this@XuanZeDiQuActivity,"请选择")
                return@setOnClickListener
            }
            inn.putExtra("diqu", "${shengname}${shiname}${quname}")
            setResult(Activity.RESULT_OK, inn)
            finish()
        }
        sheng_tv.setOnClickListener {
            sheng_tv.text = "选择省份"
            shi_tv.text = "选择城市"
            qu_tv.text = "选择区/镇"
            sheng = ""
            shi = ""
            qu = ""
            shengname = ""
            shiname = ""
            quname = ""
            getSheng()
        }
        shi_tv.setOnClickListener {
            shi_tv.text = "选择城市"
            qu_tv.text = "选择区/镇"
            shi = ""
            qu = ""
            shiname = ""
            quname = ""
            getShi(sheng)
        }
        qu_tv.setOnClickListener {
            qu_tv.text = "选择区/镇"
            qu = ""
            quname = ""
            getQu(shi)
        }
        qingkong.setOnClickListener {
            val inn = Intent()
            inn.putExtra("diqu", "")
            inn.putExtra("sheng", "")
            inn.putExtra("shi", "")
            inn.putExtra("qu", "")
            setResult(Activity.RESULT_OK, inn)
            finish()

        }
    }

    fun getSheng() {
        sheng_tv.setTextColor(resources.getColor(R.color.color4))
        shi_tv.setTextColor(resources.getColor(R.color.textcolor))
        qu_tv.setTextColor(resources.getColor(R.color.textcolor))
        ApiMapper.getAreaList("0", object : OkGoStringCallBack<DiQuBean>(this@XuanZeDiQuActivity, DiQuBean::class.java, false) {
            override fun onSuccess2Bean(bean: DiQuBean) {
                gl2.removeAllViews()
                bean.data.forEachIndexed { index, listitem ->
                    val vi = inflater.inflate(R.layout.text_item, null)
                    val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                    layoutParams.width = 0
                    layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
                    layoutParams.setMargins(8, 8, 8, 8)
                    vi.setLayoutParams(layoutParams)
                    val tv = vi.findViewById<TextView>(R.id.tv)
                    tv.setText(listitem.name)
                    gl2.addView(vi)
                    dizhiList1.add(tv)
                    vi.setOnClickListener {
                        dizhiList1.forEach {
                            it.isActivated = false
                        }
                        it.isActivated = true
                        sheng_tv.text = listitem.name
                        shengname= listitem.name
                        sheng = listitem.id
                        getShi(sheng)
                    }
                }
            }
        })
    }

    fun getShi(province: String) {
        sheng_tv.setTextColor(resources.getColor(R.color.color4))
        shi_tv.setTextColor(resources.getColor(R.color.color4))
        qu_tv.setTextColor(resources.getColor(R.color.textcolor))
        ApiMapper.getAreaList(province, object : OkGoStringCallBack<DiQuBean>(this@XuanZeDiQuActivity, DiQuBean::class.java, false) {
            override fun onSuccess2Bean(bean: DiQuBean) {
                gl2.removeAllViews()
                val vi = inflater.inflate(R.layout.text_item, null)
                val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                layoutParams.width = 0
                layoutParams.setMargins(8, 8, 8, 8)
                vi.setLayoutParams(layoutParams)
                val tv = vi.findViewById<TextView>(R.id.tv)
                tv.setText("全省")
                gl2.addView(vi)

                vi.setOnClickListener {
                    shi = ""
                    sure_btn.performClick()
                }

                bean.data?.forEachIndexed { index, listitem ->
                    val vi = inflater.inflate(R.layout.text_item, null)
                    val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                    layoutParams.width = 0
                    layoutParams.setMargins(8, 8, 8, 8)
                    vi.setLayoutParams(layoutParams)
                    val tv = vi.findViewById<TextView>(R.id.tv)
                    tv.setText(listitem.name)
                    gl2.addView(vi)
                    dizhiList2.add(tv)
                    vi.setOnClickListener {
                        dizhiList2.forEach {
                            it.isActivated = false
                        }
                        it.isActivated = true
                        shi = listitem.id
                        shi_tv.text = listitem.name
                        shiname= listitem.name
                        getQu(shi)
                    }
                }
            }
        })
    }

    fun getQu(prefecture: String) {
        sheng_tv.setTextColor(resources.getColor(R.color.color4))
        shi_tv.setTextColor(resources.getColor(R.color.color4))
        qu_tv.setTextColor(resources.getColor(R.color.color4))
        ApiMapper.getAreaList(prefecture, object : OkGoStringCallBack<DiQuBean>(this@XuanZeDiQuActivity, DiQuBean::class.java, false) {
            override fun onSuccess2Bean(bean: DiQuBean) {
                gl2.removeAllViews()
                val vi = inflater.inflate(R.layout.text_item, null)
                val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                layoutParams.width = 0
                layoutParams.setMargins(8, 8, 8, 8)
                vi.setLayoutParams(layoutParams)
                val tv = vi.findViewById<TextView>(R.id.tv)
                tv.setText("全市")
                gl2.addView(vi)
                vi.setOnClickListener {
                    qu = ""
                    sure_btn.performClick()
                }
                bean.data?.forEachIndexed { index, listitem ->
                    val vi = inflater.inflate(R.layout.text_item, null)
                    val layoutParams = GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1f))
                    layoutParams.width = 0
                    layoutParams.setMargins(8, 8, 8, 8)
                    vi.setLayoutParams(layoutParams)
                    val tv = vi.findViewById<TextView>(R.id.tv)
                    tv.setText(listitem.name)
                    gl2.addView(vi)
                    dizhiList3.add(tv)
                    vi.setOnClickListener {
                        dizhiList3.forEach {
                            it.isActivated = false
                        }
                        it.isActivated = true
                        qu = listitem.id
                        quname= listitem.name
                        qu_tv.text = listitem.name
                    }
                }

            }
        })
    }

}
