package com.example.rizkyekoputra.footballmatchschedule.View

import com.example.rizkyekoputra.footballmatchschedule.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}