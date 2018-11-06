package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentAdapter = ViewPagerAdapter(childFragmentManager).apply {
            populateFragment(FavoriteMatchesFragment.newInstance(), getString(R.string.matches))
            populateFragment(FavoriteTeamsFragment.newInstance(), getString(R.string.favorites))
        }
        viewpager_favorite.adapter = fragmentAdapter

        tabs_favorite.setupWithViewPager(viewpager_favorite)
    }
}