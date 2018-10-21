package com.example.rizkyekoputra.footballmatchschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.rizkyekoputra.footballmatchschedule.R.color.colorAccent
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment(), MatchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = LastMatchAdapter(events)
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastMatchPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> presenter.getTeamList("4328")
                    "English League Championship" -> presenter.getTeamList("4329")
                    "German Bundesliga" -> presenter.getTeamList("4331")
                    "Italian Serie A" -> presenter.getTeamList("4332")
                    "French Ligue 1" -> presenter.getTeamList("4334")
                    "Spanish La Liga" -> presenter.getTeamList("4335")
                    else -> presenter.getTeamList("4336")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            when (leagueName) {
                "English Premier League" -> presenter.getTeamList("4328")
                "English League Championship" -> presenter.getTeamList("4329")
                "German Bundesliga" -> presenter.getTeamList("4331")
                "Italian Serie A" -> presenter.getTeamList("4332")
                "French Ligue 1" -> presenter.getTeamList("4334")
                "Spanish La Liga" -> presenter.getTeamList("4335")
                else -> presenter.getTeamList("4336")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                spinner = spinner()
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(colorAccent)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listEvent = recyclerView {
                            lparams(width = matchParent, height = wrapContent)

                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
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
}