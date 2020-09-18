package com.leichui.shortviedeo.holder

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.leichui.shortviedeo.bean.EventPublishBean
import com.tencent.qcloud.xiaoshipin.R
import org.greenrobot.eventbus.EventBus
import java.io.File


class ImageUpHolder(parent: ViewGroup) : BaseViewHolder<String>(parent, R.layout.item_imageup) {
    override fun setData(data: String) {
        super.setData(data)
        val image: SimpleDraweeView = itemView.findViewById(R.id.image)
        val takePhoto: View = itemView.findViewById(R.id.takePhoto)
        val image_rl: RelativeLayout = itemView.findViewById(R.id.image_rl)
        val del: View = itemView.findViewById(R.id.del)

        if(data == "-1"){
            image_rl.visibility = View.GONE
            takePhoto.visibility = View.VISIBLE
        }else{
            image_rl.visibility = View.VISIBLE
            takePhoto.visibility = View.GONE
            image.setImageURI(Uri.fromFile(File(data)))
        }
        takePhoto.setOnClickListener {
            EventBus.getDefault().post(EventPublishBean("-1"))
        }
        del.setOnClickListener {
            EventBus.getDefault().post(EventPublishBean(data))
        }


    }
}
