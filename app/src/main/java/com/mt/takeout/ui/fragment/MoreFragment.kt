package com.mt.takeout.ui.fragment

import android.view.View
import android.widget.TextView
import com.mt.takeout.base.BaseFragment

class MoreFragment : BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.text = "更多"
        return tv
    }
}