package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.MatchView
import com.example.rizkyekoputra.footballmatchschedule.TestContextProvider
import com.example.rizkyekoputra.footballmatchschedule.model.Event
import com.example.rizkyekoputra.footballmatchschedule.model.EventResponse
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)
        val league = "English Premiere League"
        val type = "last"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatch(league, type)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getMatchList(league, type)

        verify(view).showLoading()
        verify(view).showTeamList(events)
        verify(view).hideLoading()
    }
}