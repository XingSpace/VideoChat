package com.leichui.shortviedeo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.tencent.qcloud.xiaoshipin.R
import com.tencent.qcloud.xiaoshipin.mainui.list.DividerGridItemDecoration
import kotlinx.android.synthetic.main.shouye_item.view.*

open class ShouyeItem : LinearLayout {

    public var root:View? = null

    private var titleStr:String = "";

    var onItemClickListener: OnItemClickListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.ShouyeItem)

        titleStr = a.getString(R.styleable.ShouyeItem_title)

        a.recycle()

        init()
    }

    private fun init() {
        root = LayoutInflater.from(context).inflate(R.layout.shouye_item, this)
        item_title.text = titleStr
    }


    val NORMAL_EVENT:Int = 1
    val GONGLUE_EVENT:Int = 2
    val FACE_PARENT:Int = 3
    interface OnItemClickListener {
        fun onItemClick(position:Int, event:Int);
    }

}