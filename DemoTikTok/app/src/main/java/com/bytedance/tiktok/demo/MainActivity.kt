package com.bytedance.tiktok.demo

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bytedance.tiktok.demo.imageselector.ImageSelectActivity
import com.bytedance.tiktok.demo.publish.PublishEditActivity
import com.bytedance.tiktok.demo.ui.discover.DiscoverFragment
import com.bytedance.tiktok.demo.ui.home.HomeFragment
import com.bytedance.tiktok.demo.ui.home.HomeViewModel
import com.bytedance.tiktok.demo.ui.mine.MineFragment
import com.bytedance.tiktok.demo.ui.notifications.NotificationsFragment


class MainActivity : AppCompatActivity() {

    private val fragmentsFactory by lazy { MainFragmentsFactory() }

    private val homeTab: View by lazy { findViewById<View>(R.id.ll_home) }
    private val discoverTab: View by lazy { findViewById<View>(R.id.ll_discover) }
    private val publishTab: View by lazy { findViewById<View>(R.id.ll_publish) }
    private val notificationTab: View by lazy { findViewById<View>(R.id.ll_message) }
    private val mineTab: View by lazy { findViewById<View>(R.id.ll_me) }


    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragment(HomeFragment.TAG)
        setTabListener()
        homeViewModel.refreshHomeData()
    }

    private fun setTabListener() {
        val fm = supportFragmentManager
        homeTab.setOnClickListener {
            switchFragment(HomeFragment.TAG)
        }
        homeTab.setOnLongClickListener {
            homeViewModel.refreshHomeData()
            true
        }
        discoverTab.setOnClickListener {
            switchFragment(DiscoverFragment.TAG)
        }
        publishTab.setOnClickListener {
            startActivityForResult(Intent(this, PublishEditActivity::class.java), 0x0001)
        }
        notificationTab.setOnClickListener {
            switchFragment(NotificationsFragment.TAG)
        }
        mineTab.setOnClickListener {
            switchFragment(MineFragment.TAG)
        }

        mineTab.setOnLongClickListener {
            val intent = Intent(this, ImageSelectActivity::class.java)
//            val intent = Intent(this, ProviderDemoActivity::class.java)
            startActivity(intent)
            true
        }

    }

    /**
     * 切换添加 or 显示Fragment
     * @param tag
     */
    private fun switchFragment(tag: String) {
        val fragment = fragmentsFactory.get(supportFragmentManager, tag)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //获取当前所有的fragment
        val childFragments = supportFragmentManager.fragments
        //先隐藏所有的fragment
        for (childFragment in childFragments) {
            fragmentTransaction.hide(childFragment)
        }
        //没有的话就添加，有就显示
        if (fragment !in childFragments) {
            //添加
            fragmentTransaction.add(R.id.fl_container, fragment, tag)
        } else {
            fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0x0001) {
            homeViewModel.refreshHomeData()
        }
    }
}