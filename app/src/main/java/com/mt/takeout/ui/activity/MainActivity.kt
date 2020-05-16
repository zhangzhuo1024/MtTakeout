package com.mt.takeout.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.mt.takeout.R
import com.mt.takeout.adapter.FragmentAdapter
import com.mt.takeout.ui.fragment.HomeFragment
import com.mt.takeout.ui.fragment.MoreFragment
import com.mt.takeout.ui.fragment.OrderFragment
import com.mt.takeout.ui.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fragments = listOf(HomeFragment(), OrderFragment(), UserFragment(), MoreFragment())

    private val mBottom by lazy { main_bottom }

    private val mViewPager by lazy { main_vp }

    private var mChildCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initListener()
        initSelectState(0)
    }

    //获取是否存在NavigationBar
    private fun checkHasNavigationBar(): Boolean {
        var hasNavigationBar = false
        val res = this.resources
        val id = res.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            hasNavigationBar = res.getBoolean(id)
        }
        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties")
            val m = systemPropertiesClass.getMethod("get", String::class.java)
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
            if ("1" == navBarOverride) {
                hasNavigationBar = false
            } else if ("0" == navBarOverride) {
                hasNavigationBar = true
            }
        } catch (e: Exception) {
        }
        return hasNavigationBar
    }

    /**
     * 获取导航栏高度
     */
    private fun getNavBarHeight(): Int {
        val res = this.resources;
        val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        return if (resourceId != 0) res.getDimensionPixelSize(resourceId) else 0
    }

    private fun initSelectState(index: Int) {
        mBottom.getChildAt(0).isSelected = true
    }

    private fun initData() {
        mChildCount = mBottom.childCount
        mViewPager.adapter = FragmentAdapter(fragments, supportFragmentManager)
    }

    private fun initListener() {
        (0 until mChildCount).forEach { index ->
            mBottom.getChildAt(index).setOnClickListener {
                mViewPager.currentItem = index
                changeSelectState(index)
            }
        }
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeSelectState(position)
            }
        })
    }

    private fun changeSelectState(index: Int) {
        (0 until mChildCount).forEach { mBottom.getChildAt(it).isSelected = it == index }
    }
}
