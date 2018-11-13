package com.example.rizkyekoputra.footballmatchschedule.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.rizkyekoputra.footballmatchschedule.PlayerDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.PlayerView
import com.example.rizkyekoputra.footballmatchschedule.R
import com.example.rizkyekoputra.footballmatchschedule.TeamDetailActivity
import com.example.rizkyekoputra.footballmatchschedule.Utils.invisible
import com.example.rizkyekoputra.footballmatchschedule.Utils.visible
import com.example.rizkyekoputra.footballmatchschedule.adapter.PlayerAdapter
import com.example.rizkyekoputra.footballmatchschedule.model.Player
import com.example.rizkyekoputra.footballmatchschedule.presenter.PlayerPresenter
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class PlayersFragment : Fragment(), PlayerView {
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var teamId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = PlayerAdapter(players) {
            ctx.startActivity<PlayerDetailActivity>("id" to "${it.playerId}", "name" to "${it.playerName}")
        }
        listPlayer.adapter = adapter

        teamId = arguments?.getString("id").toString()

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                listPlayer = recyclerView {
                    id = R.id.list_player
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams{
                    centerHorizontally()
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

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }
}