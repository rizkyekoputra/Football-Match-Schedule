package com.example.rizkyekoputra.footballmatchschedule.View

import com.example.rizkyekoputra.footballmatchschedule.model.Event

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}