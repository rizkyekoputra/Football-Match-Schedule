package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*
import android.support.v7.app.AppCompatActivity

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.favorites)

        val fragmentAdapter = ViewPagerAdapter(childFragmentManager).apply {
            populateFragment(FavoriteMatchesFragment.newInstance(), getString(R.string.matches))
            populateFragment(FavoriteTeamsFragment.newInstance(), getString(R.string.teams))
        }
        viewpager_favorite.adapter = fragmentAdapter

        tabs_favorite.setupWithViewPager(viewpager_favorite)
    }
}