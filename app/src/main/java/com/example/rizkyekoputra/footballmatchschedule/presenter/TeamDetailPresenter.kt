package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.TeamDetailView
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        EspressoIdlingResource.increment()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamById(teamId)),
                        TeamResponse::class.java
                )
            }

            view.showTeamDetail(data.await().teams)
            view.hideLoading()
            EspressoIdlingResource.decrement()
        }
    }
}