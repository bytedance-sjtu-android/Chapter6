package com.bytedance.tiktok.demo.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.demo.R
import kotlinx.android.synthetic.main.fragment_discover.view.*

class DiscoverFragment : Fragment() {

    companion object {
        const val TAG = "Discover"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rv.layoutManager = LinearLayoutManager(view.context)
        view.rv.adapter = object : RecyclerView.Adapter<DiscoverViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
                return DiscoverViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.discover_cell_root, parent, false)
                )
            }

            override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
                holder.bind()
            }

            override fun getItemCount(): Int {
                return 100
            }

        }

    }
}