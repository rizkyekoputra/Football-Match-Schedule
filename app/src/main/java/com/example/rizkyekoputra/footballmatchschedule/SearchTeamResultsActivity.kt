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
import com.example.rizkyekoputra.footballmatchschedule.View.TeamsView
import com.example.rizkyekoputra.footballmatchschedule.adapter.TeamsAdapter
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.presenter.SearchTeamPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SearchTeamResultsActivity : AppCompatActivity(), TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamsAdapter
    private lateinit var mPresenter: SearchTeamPresenter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query = intent.getStringExtra(SearchManager.QUERY)

        relativeLayout {
            lparams(width = matchParent, height = wrapContent)

            listTeam = recyclerView {
                id = R.id.list_event_search
                lparams(width = matchParent, height = wrapContent)

                layoutManager = LinearLayoutManager(ctx)
            }

            progressBar = progressBar {
            }.lparams {
                centerInParent()
            }
        }

        adapter = TeamsAdapter(teams) {
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}", "name" to "${it.teamName}", "description" to "${it.teamDescription}")
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        mPresenter = SearchTeamPresenter(this, request, gson)
        mPresenter.searchTeamsByName(query)

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

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        (menu?.findItem(R.id.search)?.actionView as SearchView?).apply {
            this?.queryHint = getString(R.string.search_hint_teams)

            this?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mPresenter.searchTeamsByName(newText)
                    return false
                }
            })
        }

        return true
    }
}