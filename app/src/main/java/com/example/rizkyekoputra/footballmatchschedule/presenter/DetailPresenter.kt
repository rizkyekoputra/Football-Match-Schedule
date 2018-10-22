package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.DetailView
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun getHomeTeamBadge(id: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamById(id)),
                    TeamResponse::class.java
            )

            uiThread {
                view.displayHomeTeamBadge(data.teams[0])
            }
        }
    }

    fun getAwayTeamBadge(id: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamById(id)),
                    TeamResponse::class.java
            )

            uiThread {
                view.displayAwayTeamBadge(data.teams[0])
            }
        }
    }
}