package com.mt.takeout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mt.takeout.base.BaseFragment

class FragmentAdapter(private var list: List<BaseFragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
       return list.size
    }
}