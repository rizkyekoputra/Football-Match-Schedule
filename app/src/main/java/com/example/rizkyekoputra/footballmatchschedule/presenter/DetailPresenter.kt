package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.DetailView
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getHomeTeamBadge(id: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamById(id)),
                        TeamResponse::class.java
                )
            }

            view.displayHomeTeamBadge(data.await().teams[0])
        }
    }

    fun getAwayTeamBadge(id: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamById(id)),
                        TeamResponse::class.java
                )
            }

            view.displayAwayTeamBadge(data.await().teams[0])
        }
    }
}