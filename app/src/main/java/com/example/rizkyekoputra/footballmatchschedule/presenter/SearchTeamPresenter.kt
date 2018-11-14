package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.Utils.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.View.TeamsView
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter(private val view: TeamsView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun searchTeamsByName(teamName: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.searchTeamByTeamName(teamName)),
                        TeamResponse::class.java
                )
            }

            view.hideLoading()
            view.showTeamList(data.await().teams)
            EspressoIdlingResource.decrement()
        }
    }
}