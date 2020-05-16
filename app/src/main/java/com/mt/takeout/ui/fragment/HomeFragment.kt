package com.mt.takeout.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mt.takeout.R
import com.mt.takeout.adapter.HomeAdapter
import com.mt.takeout.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_title.*

class HomeFragment : BaseFragment() {
    private var distance = 0
    private var scrollSum = 0

    override fun initView(): View? {
        return View.inflate(activity, R.layout.fragment_home, null)
    }

    override fun initData() {
        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.adapter = HomeAdapter()
    }

    override fun initListener() {
        home_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                distance = item_home_slider.height - home_title_container.height
                scrollSum += dy
                val alpha = if (scrollSum >= distance) 255 else 55 + 200 * scrollSum / distance
                home_title_container.setBackgroundColor(Color.argb(alpha, 0x31, 0x90, 0xE8))
            }
        })
    }
}