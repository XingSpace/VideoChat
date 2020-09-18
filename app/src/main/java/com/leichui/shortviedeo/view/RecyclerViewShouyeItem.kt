package com.leichui.shortviedeo.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tencent.qcloud.ugckit.utils.ScreenUtils
import com.tencent.qcloud.xiaoshipin.R

class RecyclerViewShouyeItem : ShouyeItem {

    var recyclerView: RecyclerView? = null

    var adapter = null

    val HALF_PARENT:Int = -3

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init();
    }

    private fun init() {
        recyclerView = RecyclerView(context)

        var layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER

        root!!.findViewById<FrameLayout>(R.id.content_root).addView(recyclerView, layoutParams)
    }

    fun setNormalStyle(list: List<NormalData>) {
        recyclerView!!.layoutManager = LinearLayoutManager(context, HORIZONTAL, false);
        recyclerView!!.adapter = NormalAdapter(context, list);
    }

    fun setMoteStyle(list: List<NormalData>) {
        recyclerView!!.layoutManager = LinearLayoutManager(context, HORIZONTAL, false);
        recyclerView!!.adapter = NormalAdapter(context, list, ScreenUtils.dp2px(context, 102.5f).toInt(), ViewGroup.LayoutParams.MATCH_PARENT);
    }

    fun setFaceStyle(list: List<FaceData>) {
        recyclerView!!.layoutManager = LinearLayoutManager(context, HORIZONTAL, false);
        recyclerView!!.adapter = FaceAdapter(context, list);
    }

    fun setGongLueStyle(list: List<GongLueData>) {
        recyclerView!!.layoutManager = LinearLayoutManager(context, HORIZONTAL, false);
        recyclerView!!.adapter = GongLueAdapter(context, list);
    }

    inner class NormalAdapter : RecyclerView.Adapter<NormalViewHolder> {

        var context: Context;
        var list: List<NormalData>
        var width: Int
        var height: Int

        constructor(context: Context, list: List<NormalData>) : this(context, list, HALF_PARENT, ViewGroup.LayoutParams.MATCH_PARENT){
            this.context = context
            this.list = list
        }

        constructor(context: Context, list: List<NormalData>, width: Int, height: Int) {
            this.width = width
            this.height = height
            this.context = context
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NormalViewHolder {
//            Log.e("测试","check width->>"+parent!!.width + " height->>"+parent!!.height);
            return NormalViewHolder(
                    View.inflate(context, R.layout.shouye_item_normal_view, null)
                    , if (HALF_PARENT === width) parent!!.width / 2 else width
                    , if (HALF_PARENT === height) parent!!.height / 2 else height)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: NormalViewHolder?, position: Int) {
            list[holder!!.adapterPosition].url?.let { holder.setPic(context, it) }
            list[holder!!.adapterPosition].title?.let { holder.setTitle(it) }

            holder.itemView.setOnClickListener { onItemClickListener!!.onItemClick(position, NORMAL_EVENT) }
        }

    }

    class NormalViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View?) : this(itemView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        constructor(itemView: View?, width: Int, height: Int) : super(itemView) {
            var params = MarginLayoutParams(width, height)
            itemView!!.layoutParams = params
        }

        fun setPic(context: Context, url: String) {
            Glide.with(context).load(R.drawable.bg).error(R.drawable.bg).into(itemView.findViewById<ImageView>(R.id.pic_img))
        }

        fun setTitle(title: String) {
            itemView.findViewById<TextView>(R.id.title_txt).text = title;
        }
    }

    class NormalData {

        constructor() {}

        constructor(title: String, url: String) {
            this.title = title
            this.url = url
        }

        var title: String? = null
        var url: String? = null
    }

    inner class GongLueAdapter : RecyclerView.Adapter<GongLueViewHolder> {

        var context: Context;
        var list: List<GongLueData>
        var width: Int
        var height: Int

        constructor(context: Context, list: List<GongLueData>) : this(context, list, HALF_PARENT, ViewGroup.LayoutParams.MATCH_PARENT){
            this.context = context
            this.list = list
        }

        constructor(context: Context, list: List<GongLueData>, width: Int, height: Int) {
            this.width = width
            this.height = height
            this.context = context
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GongLueViewHolder {
//            Log.e("测试","check width->>"+parent!!.width + " height->>"+parent!!.height);
            return GongLueViewHolder(
                    View.inflate(context, R.layout.shouye_item_gonglue_view, null)
                    , if (HALF_PARENT === width) parent!!.width / 2 else width
                    , if (HALF_PARENT === height) parent!!.height / 2 else height)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: GongLueViewHolder?, position: Int) {
            list[holder!!.adapterPosition].url?.let { holder.setPic(context, it) }
            list[holder!!.adapterPosition].title?.let { holder.setTitle(it) }
            list[holder!!.adapterPosition].faceUrl?.let { holder.setFacePic(context, it) }
            list[holder!!.adapterPosition].greatNum?.let { holder.setGreatNum(it) }
            list[holder!!.adapterPosition].isGreat?.let { holder.setIsGreat(context, it) }
            list[holder!!.adapterPosition].sign?.let { holder.setSign(it) }
            holder.itemView.setOnClickListener { onItemClickListener!!.onItemClick(position, GONGLUE_EVENT) }
        }

    }

    class GongLueViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View?) : this(itemView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        constructor(itemView: View?, width: Int, height: Int) : super(itemView) {
            var params = MarginLayoutParams(width, height)
            itemView!!.layoutParams = params
        }

        fun setPic(context: Context, url: String) {
            Glide.with(context).load(R.drawable.bg).error(R.drawable.bg).into(itemView.findViewById<ImageView>(R.id.pic_img))
        }

        fun setFacePic(context: Context, url: String) {
            Glide.with(context).load(R.drawable.face).error(R.drawable.face).into(itemView.findViewById<ImageView>(R.id.icon_face))
        }

        fun setTitle(title: String) {
            itemView.findViewById<TextView>(R.id.title_txt).text = title;
        }

        fun setGreatNum(num:Int) {
            itemView.findViewById<TextView>(R.id.zan_txt).text = num.toString();
        }

        fun setIsGreat(context: Context, isGreat:Boolean) {
            Glide.with(context).load(R.drawable.icon_praise).error(R.drawable.icon_praise).into(itemView.findViewById<ImageView>(R.id.zan))
        }

        fun setSign(sign: String) {
            itemView.findViewById<TextView>(R.id.sign_txt).text = sign;
        }
    }

    class GongLueData {

        constructor() {}

        /**
         * @param title 标题
         * @param url 封面配图路径
         * @param faceUrl 头像路径
         * @param greatNum 点赞数
         * @param isGreat 本人是否已经点赞了
         * @param sign 个人签名
         */
        constructor(title: String?, url: String?, faceUrl: String?, greatNum: Int?, isGreat: Boolean?, sign: String?) {
            this.title = title
            this.url = url
            this.faceUrl = faceUrl
            this.greatNum = greatNum
            this.isGreat = isGreat
            this.sign = sign
        }

        var title: String? = null
        var url: String? = null
        var faceUrl: String? = null
        var greatNum: Int? = null
        var isGreat: Boolean? = null
        var sign: String? = null
    }

    inner class FaceAdapter : RecyclerView.Adapter<FaceViewHolder> {

        var context: Context;
        var list: List<FaceData>

        constructor(context: Context, list: List<FaceData>) {
            this.context = context
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FaceViewHolder {
            return FaceViewHolder(View.inflate(context, R.layout.shouye_item_normal_view, null))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: FaceViewHolder?, position: Int) {
            list[holder!!.adapterPosition].url?.let { holder.setPic(context, it) }
            list[holder!!.adapterPosition].title?.let { holder.setTitle(it) }
            holder.itemView.setOnClickListener { onItemClickListener!!.onItemClick(position, FACE_PARENT) }
        }

    }

    class FaceViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View) : super(itemView) {
            var params = MarginLayoutParams(ScreenUtils.dp2px(itemView.context, 50.5f).toInt(), LayoutParams.MATCH_PARENT)
            itemView.layoutParams = params
        }

        fun setPic(context: Context, url: String) {
            Glide.with(context).load(R.drawable.face).error(R.drawable.face).into(itemView.findViewById<ImageView>(R.id.pic_img))
        }

        fun setTitle(title: String) {
            itemView.findViewById<TextView>(R.id.title_txt).text = title;
        }
    }

    class FaceData {

        constructor() {}

        constructor(title: String, url: String) {
            this.title = title
            this.url = url
        }

        var title: String? = null
        var url: String? = null
    }

}