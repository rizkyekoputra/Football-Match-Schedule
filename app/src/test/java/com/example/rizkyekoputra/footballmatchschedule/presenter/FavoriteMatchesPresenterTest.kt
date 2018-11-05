package com.example.rizkyekoputra.footballmatchschedule.presenter

import com.example.rizkyekoputra.footballmatchschedule.MatchView
import com.example.rizkyekoputra.footballmatchschedule.TestContextProvider
import com.example.rizkyekoputra.footballmatchschedule.model.*
import com.example.rizkyekoputra.footballmatchschedule.rest.ApiRepository
import com.example.rizkyekoputra.footballmatchschedule.rest.TheSportDBApi
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FavoriteMatchesPresenterTest {
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: FavoriteMatchesPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoriteMatchesPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getFavoriteEvent() {
        val events: MutableList<Event> = mutableListOf()
        val favorites: MutableList<Favorite> = mutableListOf()
        val response = EventResponse(events)
        val id = "2235"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getEventById(id)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getFavoriteEvent(favorites)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showMatchList(events)
    }
}