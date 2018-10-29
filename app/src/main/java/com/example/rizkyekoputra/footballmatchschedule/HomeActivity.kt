package com.example.rizkyekoputra.footballmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rizkyekoputra.footballmatchschedule.R.string.favorites
import com.example.rizkyekoputra.footballmatchschedule.R.string.matches
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
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
}
