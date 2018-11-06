package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.CoroutineContextProvider
import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.TeamsView
import com.example.rizkyekoputra.footballmatchschedule.model.FavoriteTeam
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class FavoriteTeamsPresenter(private val view: TeamsView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFavoriteTeam(favoriteTeams: List<FavoriteTeam>) {
        view.showLoading()
        EspressoIdlingResource.increment()
        async(context.main) {
            val teams: MutableList<Team> = mutableListOf()
            for (fav in favoriteTeams) {
                val data = bg {
                    gson.fromJson(apiRepository
                            .doRequest(TheSportDBApi.getTeamById(fav.teamId)),
                            TeamResponse::class.java
                    )
                }

                teams.add(data.await().teams.first())
            }

            view.hideLoading()
            view.showTeamList(teams)
            EspressoIdlingResource.decrement()
        }
    }
}