package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.rizkyekoputra.footballmatchschedule.*
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.adapter.MatchAdapter
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.presenter.MatchPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextMatchFragment : Fragment(), MatchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String
    private val leagueType = "eventsnextleague"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MatchAdapter(events)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                when (leagueName) {
                    getString(R.string.uefa_champions_league) -> presenter.getTeamList(getString(R.string.uefa_champions_league_code), leagueType)
                    getString(R.string.english_premier_league) -> presenter.getTeamList(getString(R.string.english_premier_league_code), leagueType)
                    getString(R.string.english_league_championship) -> presenter.getTeamList(getString(R.string.english_league_championship_code), leagueType)
                    getString(R.string.german_bundesliga) -> presenter.getTeamList(getString(R.string.german_bundesliga_code), leagueType)
                    getString(R.string.italian_serie_a) -> presenter.getTeamList(getString(R.string.italian_serie_a_code), leagueType)
                    getString(R.string.french_ligue_1) -> presenter.getTeamList(getString(R.string.french_ligue_1_code), leagueType)
                    getString(R.string.spanish_la_liga) -> presenter.getTeamList(getString(R.string.spanish_la_liga_code), leagueType)
                    else -> presenter.getTeamList(getString(R.string.english_premier_league_code), leagueType)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            when (leagueName) {
                getString(R.string.uefa_champions_league) -> presenter.getTeamList(getString(R.string.uefa_champions_league_code), leagueType)
                getString(R.string.english_premier_league) -> presenter.getTeamList(getString(R.string.english_premier_league_code), leagueType)
                getString(R.string.english_league_championship) -> presenter.getTeamList(getString(R.string.english_league_championship_code), leagueType)
                getString(R.string.german_bundesliga) -> presenter.getTeamList(getString(R.string.german_bundesliga_code), leagueType)
                getString(R.string.italian_serie_a) -> presenter.getTeamList(getString(R.string.italian_serie_a_code), leagueType)
                getString(R.string.french_ligue_1) -> presenter.getTeamList(getString(R.string.french_ligue_1_code), leagueType)
                getString(R.string.spanish_la_liga) -> presenter.getTeamList(getString(R.string.spanish_la_liga_code), leagueType)
                else -> presenter.getTeamList(getString(R.string.english_premier_league_code), leagueType)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                spinner = spinner()
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(R.color.colorAccent)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listEvent = recyclerView {
                            lparams(width = matchParent, height = wrapContent)

                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerInParent()
                        }
                    }
                }
            }
        }.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }
}
