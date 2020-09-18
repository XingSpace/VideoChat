package com.leichui.shortviedeo.activity

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.ugckit.utils.ScreenUtils
import com.tencent.qcloud.xiaoshipin.R
import com.yiw.circledemo.utils.DrawableBuilder
import kotlinx.android.synthetic.main.activity_shouye_address.*
import kotlinx.android.synthetic.main.main_title.*

/**
 * 选择城市的activity
 */
class ShouyeAddressActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_shouye_address
    }

    override fun startAction() {
        search_bar.visibility = View.VISIBLE
        address_txt.visibility = View.GONE
        search_left_img.visibility = View.VISIBLE
        findViews()
        init()
    }

    private fun findViews() {
        search_left_img.setOnClickListener(this::onSearchLeftImgClick)
    }

    private fun init() {
        lishi_recycler.layoutManager = GridLayoutManager(baseContext, 3)
        lishi_recycler.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                outRect!!.set(0,ScreenUtils.dp2px(baseContext, 11f).toInt(),0, ScreenUtils.dp2px(baseContext, 11f).toInt())
            }
        })
        var list:List<String> = ArrayList()
        list+= "青岛"
        list+= "上海"
        list+= "上海"

        lishi_recycler.adapter = Adapter(baseContext, list)


    }

    private fun onSearchLeftImgClick(v:View) {
        finish()
    }


    class Adapter : RecyclerView.Adapter<ViewHoler> {

        var context:Context?
        var list:List<String>?

        constructor(context: Context, list:List<String>) : super() {
            this.context = context
            this.list = list
        }


        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHoler {
            return ViewHoler(FrameLayout(context));
        }

        override fun getItemCount(): Int {
            return list!!.size
        }

        override fun onBindViewHolder(holder: ViewHoler?, position: Int) {
            holder!!.textView!!.text = list!![position]
        }

    }

    class ViewHoler : RecyclerView.ViewHolder {
        var textView:TextView?
        constructor(itemView: View) :super(itemView) {

            textView = TextView(itemView.context)

            textView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

            var params:FrameLayout.LayoutParams = FrameLayout.LayoutParams(
                    ScreenUtils.dp2px(itemView.context, 78f).toInt(), ScreenUtils.dp2px(itemView.context, 33f).toInt())
            params.gravity = Gravity.CENTER

            textView!!.layoutParams = params

            textView!!.gravity = Gravity.CENTER

            textView!!.background = DrawableBuilder()
                    .setStroke(3, 0xffc9c9c9.toInt())
                    .setRadius(5F)
                    .create()

            (itemView as FrameLayout).addView(textView)

        }
    }

}