package com.example.rizkyekoputra.footballmatchschedule.View

import com.example.rizkyekoputra.footballmatchschedule.model.Team

interface DetailView {
    fun displayHomeTeamBadge(team: Team)
    fun displayAwayTeamBadge(team: Team)
}