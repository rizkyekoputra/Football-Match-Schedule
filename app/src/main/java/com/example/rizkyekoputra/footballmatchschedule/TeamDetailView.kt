package com.example.rizkyekoputra.footballmatchschedule

import com.example.rizkyekoputra.footballmatchschedule.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}