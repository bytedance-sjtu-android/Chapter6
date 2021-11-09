package com.bytedance.tiktok.demo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bytedance.tiktok.demo.ui.discover.DiscoverFragment
import com.bytedance.tiktok.demo.ui.home.HomeFragment
import com.bytedance.tiktok.demo.ui.mine.MineFragment
import com.bytedance.tiktok.demo.ui.notifications.NotificationsFragment

/**
 *  author : neo
 *  time   : 2021/06/15
 *  desc   :
 */
class MainFragmentsFactory {

    fun get(fm: FragmentManager, tag: String) =
        when(tag) {
            HomeFragment.TAG -> fm.findFragmentByTag(HomeFragment.TAG) ?: HomeFragment()
            DiscoverFragment.TAG -> fm.findFragmentByTag(DiscoverFragment.TAG) ?: DiscoverFragment()
            NotificationsFragment.TAG -> fm.findFragmentByTag(NotificationsFragment.TAG) ?: NotificationsFragment()
            MineFragment.TAG -> fm.findFragmentByTag(MineFragment.TAG) ?: MineFragment()
            else -> throw IllegalArgumentException("Illegal tag for fragment found or created.")
        }
}