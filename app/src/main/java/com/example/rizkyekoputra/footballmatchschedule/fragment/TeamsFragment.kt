package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.R.array.league
import com.example.rizkyekoputra.footballmatchschedule.R.color.colorAccent
import com.example.rizkyekoputra.footballmatchschedule.SearchTeamResultsActivity
import com.example.rizkyekoputra.footballmatchschedule.TeamDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.View.TeamsView
import com.example.rizkyekoputra.footballmatchschedule.adapter.TeamsAdapter
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.presenter.TeamsPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teams) {
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}", "name" to "${it.teamName}", "description" to "${it.teamDescription}")
        }
        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner {
                id = R.id.spinner_teams
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id = R.id.list_team
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerInParent()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)

        (menu?.findItem(R.id.search)?.actionView as SearchView?).apply {
            this?.queryHint = getString(R.string.search_hint_teams)

            this?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    context?.startActivity<SearchTeamResultsActivity>("query" to query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    return false
                }
            })
        }
    }
}