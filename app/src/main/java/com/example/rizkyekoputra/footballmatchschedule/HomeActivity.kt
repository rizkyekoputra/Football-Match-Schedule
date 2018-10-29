package com.example.rizkyekoputra.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rizkyekoputra.footballmatchschedule.R.id.favorites
import com.example.rizkyekoputra.footballmatchschedule.R.id.matches
import com.example.rizkyekoputra.footballmatchschedule.fragment.FavoriteMatchesFragment
import com.example.rizkyekoputra.footballmatchschedule.fragment.MatchesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches -> {
                    loadMatchesFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matches
        supportActionBar?.elevation = 0F
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchesFragment(), FavoriteMatchesFragment::class.java.simpleName)
                    .commit()
        }
    }
}
