package com.example.rizkyekoputra.footballmatchschedule

import com.example.rizkyekoputra.footballmatchschedule.model.Event

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}