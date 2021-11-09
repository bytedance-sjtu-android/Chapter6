package com.bytedance.tiktok.demo.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bytedance.tiktok.demo.R
import com.bytedance.tiktok.demo.ui.home.HomeViewModel

class MineFragment : Fragment() {


    companion object {
        const val TAG = "Mine"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        textView.text = "This is mine Fragment"
        return root
    }
}