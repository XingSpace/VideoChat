package com.leichui.shortviedeo.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.tencent.qcloud.xiaoshipin.R
import com.yiw.circledemo.listener.OnClickEventListener

class SliderViewShouyeItem : ShouyeItem, OnClickEventListener {

    var sliderView: SliderLayout? = null

    var onClickEventListener: OnClickEventListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init();
    }

    private fun init() {
        sliderView = SliderLayout(context)

        var layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER

        root!!.findViewById<FrameLayout>(R.id.content_root).addView(sliderView, layoutParams)
    }

    public fun setList(list: List<ItemData>) {
        var i = 0
        list.forEach {
            sliderView!!.addSlider(ItemView(context)
                    .setData(it)
                    .setPosition(i)
                    .setOnClickEventListener(this))
            i++
        }
    }

    fun onItemClickListener(onClickEventListener: OnClickEventListener) {
        this.onClickEventListener = onClickEventListener
    }

    override fun onClick(view: View?, position: Int, event: Int) {
        onClickEventListener!!.onClick(view, position, event)
    }

    class ItemView : BaseSliderView {

        var rootView: View? = null
        var title_text: TextView? = null
        var time_text: TextView? = null
        var address_text: TextView? = null
        var people_num: TextView? = null
        var price_text: TextView? = null
        var canjia_text: TextView? = null
        var tips_text: TextView? = null
        var name_text: TextView? = null
        var face_icon: ImageView? = null
        var position: Int = 0
        var itemData:ItemData? = null

        var onClickEventListener: OnClickEventListener? = null

        constructor(context: Context) : super(context) {
        }

        override fun getView(): View {
            rootView = LayoutInflater.from(context).inflate(R.layout.slider_view_for_shouye, null)

            time_text = rootView!!.findViewById(R.id.time_text)
            title_text = rootView!!.findViewById(R.id.title_text)
            address_text = rootView!!.findViewById(R.id.address_text)
            people_num = rootView!!.findViewById(R.id.people_num)
            price_text = rootView!!.findViewById(R.id.price_text)
            canjia_text = rootView!!.findViewById(R.id.canjia_text)
            tips_text = rootView!!.findViewById(R.id.tips_text)
            name_text = rootView!!.findViewById(R.id.name_text)
            face_icon = rootView!!.findViewById(R.id.face_icon)

            setData()

            rootView!!.setOnClickListener {
                onClickEventListener!!.let {
                    it.onClick(rootView, position, 1)
                }
            }

            return rootView!!
        }

        public fun setData(itemData: ItemData): ItemView {
            this.itemData = itemData
            return this
        }

        private fun setData() {
            title_text!!.text = itemData!!.title_text;
            time_text!!.text = itemData!!.time_text;
            address_text!!.text = itemData!!.address_text;
            people_num!!.text = itemData!!.people_num;
            price_text!!.text = itemData!!.price_text;
            canjia_text!!.text = itemData!!.canjia_text;
            tips_text!!.text = itemData!!.tips_text;
            name_text!!.text = itemData!!.name_text;
            face_icon!!.let {
                Glide.with(context).load(itemData!!.face_icon).error(R.drawable.face) to it
            }
        }

        fun setOnClickEventListener(onClickEventListener: OnClickEventListener): ItemView {
            this.onClickEventListener = onClickEventListener
            return this
        }

        fun setPosition(position: Int): ItemView {
            this.position = position
            return this
        }

    }

    class ItemData() {
        var title_text: String? = null
        var time_text: String? = null
        var address_text: String? = null
        var people_num: String? = null
        var price_text: String? = null
        var canjia_text: String? = null
        var tips_text: String? = null
        var name_text: String? = null
        var face_icon: String? = null

        fun title(title: String): ItemData {
            title_text = title
            return this
        }

        fun time(time: String): ItemData {
            time_text = time
            return this
        }

        fun address(address: String): ItemData {
            address_text = address
            return this
        }

        fun people(people: String): ItemData {
            people_num = people
            return this
        }

        fun price(price: String): ItemData {
            price_text = price
            return this
        }

        fun canjia(canjia: String): ItemData {
            canjia_text = canjia
            return this
        }

        fun tips(tips: String): ItemData {
            tips_text = tips
            return this
        }

        fun name(name: String): ItemData {
            name_text = name
            return this
        }

        fun face(faceUrl: String): ItemData {
            face_icon = faceUrl
            return this
        }

    }

}