package com.leichui.shortviedeo.activity


import android.view.View
import com.leichui.shortviedeo.adapter.MyViewPagerAdapter2
import com.leichui.shortviedeo.base.BaseActivity
import com.tencent.qcloud.xiaoshipin.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_image_browse.*
import java.util.*


class ImageBrowseActivity : BaseActivity() {

    private var imgs: List<String> = ArrayList<String>()

    private var position: Int = 0
    override fun setLayoutId(): Int {
        return R.layout.activity_image_browse
    }

    override fun startAction() {
        base_title.visibility = View.GONE

        position = intent.getIntExtra("position", 0)

        imgs = intent.getStringArrayListExtra("imgs")
        search_viewpager.setOffscreenPageLimit(2)
        val adapter = MyViewPagerAdapter2(this, imgs)
        search_viewpager.setAdapter(adapter)
        search_viewpager.setCurrentItem(position)
    }


}
