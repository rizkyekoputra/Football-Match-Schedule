package com.example.rizkyekoputra.footballmatchschedule

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(private val view: MatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getLastMatch(league)),
                    EventResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.events)
            }
        }
    }

}