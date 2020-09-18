package com.leichui.shortviedeo.adapter

import android.content.Context
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.leichui.shortviedeo.holder.ImageUpHolder

class ImageUpAdapter(context: Context) : RecyclerArrayAdapter<String>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        return ImageUpHolder(parent)
    }

}