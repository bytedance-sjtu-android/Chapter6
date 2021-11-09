package com.bytedance.tiktok.demo.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bytedance.tiktok.demo.R
import com.bytedance.tiktok.demo.publish.data.FeedData
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    companion object {
        const val BUNDLE_DATA_KEY = "key_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feed = arguments?.getSerializable(BUNDLE_DATA_KEY) as? FeedData?
        feed?.let {
            Glide.with(this)
                .load(it.imageUrl)
                .into(image_view)
        }

    }
}