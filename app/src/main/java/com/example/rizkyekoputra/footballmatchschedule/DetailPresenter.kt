package com.example.rizkyekoputra.footballmatchschedule

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