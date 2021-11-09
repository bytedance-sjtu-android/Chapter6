package com.bytedance.tiktok.demo.ui.discover

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.demo.R
import kotlinx.android.synthetic.main.discover_cell_root.view.*

class DiscoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mAdapter = InnerAdapter()

    init {
        itemView.list.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        itemView.list.adapter = mAdapter
    }

    fun bind() {

    }


}

class InnerAdapter : RecyclerView.Adapter<InnerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val imageView = ImageView(parent.context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = ViewGroup.LayoutParams(300, 500)
        return InnerViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

}

class InnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind() {
        (itemView as ImageView).setImageResource(R.drawable.feed_u)
    }


}