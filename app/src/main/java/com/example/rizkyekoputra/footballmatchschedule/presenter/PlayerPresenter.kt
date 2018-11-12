package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.PlayerView
import com.example.rizkyekoputra.footballmatchschedule.model.PlayerResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(teamId: String?) {
        view.showLoading()
        EspressoIdlingResource.increment()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayersByTeamId(teamId)),
                        PlayerResponse::class.java
                )
            }

            view.showPlayerList(data.await().player)
            view.hideLoading()
            EspressoIdlingResource.decrement()
        }
    }
}