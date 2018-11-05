package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.rizkyekoputra.footballmatchschedule.model.FavoriteMatch
import com.example.rizkyekoputra.footballmatchschedule.MatchView
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.adapter.MatchAdapter
import com.example.rizkyekoputra.footballmatchschedule.helper.database
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.presenter.FavoriteMatchesPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesFragment : Fragment(), AnkoComponent<Context>, MatchView {
    private var favorites: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: FavoriteMatchesPresenter
    private lateinit var listEvent: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        favorites.clear()
        favorites.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MatchAdapter(favorites)

        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FavoriteMatchesPresenter(this, request, gson)

        showFavorite()
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

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
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteMatch>())
            presenter.getFavoriteEvent(favorites)
        }
    }
}