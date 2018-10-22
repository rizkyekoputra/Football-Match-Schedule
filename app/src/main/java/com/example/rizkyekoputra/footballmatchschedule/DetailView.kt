package com.example.rizkyekoputra.footballmatchschedule

import com.example.rizkyekoputra.footballmatchschedule.model.Team

interface DetailView {
    fun displayHomeTeamBadge(team: Team)
    fun displayAwayTeamBadge(team: Team)
}