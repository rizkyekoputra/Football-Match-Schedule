package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.SearchMatchResultsActivity
import com.example.rizkyekoputra.footballmatchschedule.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.macthes_fragment.*
import org.jetbrains.anko.startActivity

class MatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.macthes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        val fragmentAdapter = ViewPagerAdapter(childFragmentManager).apply {
            populateFragment(NextMatchFragment.newInstance(), getString(R.string.next_match))
            populateFragment(LastMatchFragment.newInstance(), getString(R.string.last_match))
        }
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)

        (menu?.findItem(R.id.search)?.actionView as SearchView?).apply {
            this?.queryHint = getString(R.string.search_hint_matches)

            this?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    context?.startActivity<SearchMatchResultsActivity>("query" to query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    return false
                }
            })
        }
    }
}