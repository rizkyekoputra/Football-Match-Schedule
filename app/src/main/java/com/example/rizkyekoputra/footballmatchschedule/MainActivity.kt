package com.example.rizkyekoputra.footballmatchschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import com.example.rizkyekoputra.footballmatchschedule.fragment.LastMatchFragment
import com.example.rizkyekoputra.footballmatchschedule.fragment.NextMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            populateFragment(LastMatchFragment.newInstance(), getString(R.string.last_match))
            populateFragment(NextMatchFragment.newInstance(), getString(R.string.next_match))
        }
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        supportActionBar?.elevation = 0F
    }
}
