package com.example.rizkyekoputra.footballmatchschedule

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Event>)
}