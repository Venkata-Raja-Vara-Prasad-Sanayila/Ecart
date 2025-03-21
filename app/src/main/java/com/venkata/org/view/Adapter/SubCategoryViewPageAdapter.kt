package com.venkata.org.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SubCategoryViewPageAdapter(val fragments: List<Fragment>,
    val fragmentManager: FragmentManager,
    val lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}