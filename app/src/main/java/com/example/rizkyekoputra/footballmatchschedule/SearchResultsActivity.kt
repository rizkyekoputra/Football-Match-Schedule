package com.example.rizkyekoputra.footballmatchschedule

import android.app.SearchManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.View.MatchView
import com.example.rizkyekoputra.footballmatchschedule.adapter.MatchAdapter
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.presenter.SearchMatchPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SearchResultsActivity : AppCompatActivity(), MatchView {

    private lateinit var adapter: MatchAdapter
    private var events : MutableList<Event> = mutableListOf()
    private lateinit var mPresenter: SearchMatchPresenter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val query = intent.getStringExtra(SearchManager.QUERY)

        relativeLayout {
            lparams(width = matchParent, height = wrapContent)

            listEvent = recyclerView {
                id = R.id.list_event_search
                lparams(width = matchParent, height = wrapContent)

                layoutManager = LinearLayoutManager(ctx)
            }

            progressBar = progressBar {
            }.lparams {
                centerInParent()
            }
        }

        adapter = MatchAdapter(events)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        mPresenter = SearchMatchPresenter(this, request, gson)
        mPresenter.searchEventsByName(query)

        supportActionBar?.title = getString(R.string.search_result)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        (menu?.findItem(R.id.search)?.actionView as SearchView?).apply {
            this?.queryHint = getString(R.string.search_hint)

            this?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mPresenter.searchEventsByName(newText)
                    return false
                }
            })
        }

        return true
    }
}
