package com.mt.takeout.ui.fragment

import android.view.View
import android.widget.TextView
import com.mt.takeout.base.BaseFragment

class UserFragment : BaseFragment() {
    override fun initView(): View? {
        val tv = TextView(context)
        tv.text = "个人"
        return tv
    }
}