package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.Utils.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.View.MatchView
import com.example.rizkyekoputra.footballmatchschedule.model.SearchEventResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view: MatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun searchEventsByName(eventName: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.searchEventByEventName(eventName)),
                        SearchEventResponse::class.java
                )
            }

            view.hideLoading()
            view.showMatchList(data.await().event)
            EspressoIdlingResource.decrement()
        }
    }
}