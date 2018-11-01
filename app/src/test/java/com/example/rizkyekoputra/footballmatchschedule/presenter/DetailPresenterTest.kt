package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.DetailView
import com.example.rizkyekoputra.footballmatchschedule.TestContextProvider
import com.example.rizkyekoputra.footballmatchschedule.model.Team
import com.example.rizkyekoputra.footballmatchschedule.model.TeamResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private
    lateinit var view: DetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailPresenter
    private val team = Team()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getHomeTeamBadge() {
        val teams: MutableList<Team> = mutableListOf()
        teams.add(team)
        val response = TeamResponse(teams)
        val id = "4430"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamById(id)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getHomeTeamBadge(id)

        verify(view).displayHomeTeamBadge(teams[0])
    }

    @Test
    fun getAwayTeamBadge() {
        val teams: MutableList<Team> = mutableListOf()
        teams.add(team)
        val response = TeamResponse(teams)
        val id = "4430"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamById(id)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getAwayTeamBadge(id)

        verify(view).displayAwayTeamBadge(teams[0])
    }
}