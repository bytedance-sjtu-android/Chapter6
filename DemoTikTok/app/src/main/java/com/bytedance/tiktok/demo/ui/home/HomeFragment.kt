package com.bytedance.tiktok.demo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bytedance.tiktok.demo.R
import com.bytedance.tiktok.demo.ui.feed.FeedFragment
import com.bytedance.tiktok.demo.ui.notifications.NotificationsViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    companion object {
        const val TAG = "Home"
    }

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.feed.observe(viewLifecycleOwner, Observer {
            view.pager.adapter?.notifyDataSetChanged()
        })
        view.pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return FeedFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(FeedFragment.BUNDLE_DATA_KEY,  homeViewModel.feed.value?.feeds?.get(position))
                    }
                }
            }
            override fun getItemCount(): Int {
                return homeViewModel.feed.value?.feeds?.size ?: 0
            }
        }

    }
}