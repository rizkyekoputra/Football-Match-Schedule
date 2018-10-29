package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.macthes_fragment.*

class MatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.macthes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentAdapter = ViewPagerAdapter(childFragmentManager).apply {
            populateFragment(LastMatchFragment.newInstance(), getString(R.string.last_match))
            populateFragment(NextMatchFragment.newInstance(), getString(R.string.next_match))
        }
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}