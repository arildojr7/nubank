package com.arildojr.nubank.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arildojr.nubank.ui.fragments.AccountBalanceFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AccountBalanceFragment()
            1 -> AccountBalanceFragment()
            else -> AccountBalanceFragment()
        }
    }

    override fun getCount(): Int {
        return 3 //three fragments
    }
}