package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.Utils.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.View.MatchView
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.model.EventResponse
import com.example.rizkyekoputra.footballmatchschedule.model.FavoriteMatch
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class FavoriteMatchesPresenter(private val view: MatchView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson,
                               private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFavoriteEvent(favoriteMatches: List<FavoriteMatch>) {
        view.showLoading()
        EspressoIdlingResource.increment()
        async(context.main) {
            val events: MutableList<Event> = mutableListOf()
            for (fav in favoriteMatches) {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getEventById(fav.matchId)),
                            EventResponse::class.java
                    )
                }

                events.add(data.await().events.first())
            }

            view.hideLoading()
            view.showMatchList(events)
            EspressoIdlingResource.decrement()
        }
    }
}