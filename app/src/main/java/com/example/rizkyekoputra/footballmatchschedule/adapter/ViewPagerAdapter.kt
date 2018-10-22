package com.example.rizkyekoputra.footballmatchschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.rizkyekoputra.footballmatchschedule.fragment.LastMatchFragment
import com.example.rizkyekoputra.footballmatchschedule.fragment.NextMatchFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LastMatchFragment()
            }
            else -> {
                return NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "LAST MATCH"
            else -> {
                return "NEXT MATCH"
            }
        }
    }
}
