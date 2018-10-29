package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.model.Favorite
import com.example.rizkyekoputra.footballmatchschedule.MatchView
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.model.EventResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavoriteMatchesPresenter(private val view: MatchView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson) {

    fun getFavoriteEvent(favorites: List<Favorite>) {
        view.showLoading()
        doAsync {
            var events: MutableList<Event> = mutableListOf()
            for (fav in favorites) {
                val data = gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEventById(fav.matchId)),
                        EventResponse::class.java
                )
                events.add(data.events.first())
            }



            uiThread {
                view.hideLoading()
                view.showTeamList(events)
            }
        }
    }
}